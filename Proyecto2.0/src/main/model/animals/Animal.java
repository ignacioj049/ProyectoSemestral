package model.animals;

import model.TipoComida;
import model.Zoo;
import model.food.Comida;
import model.habitats.Habitat;
import util.Constants;
import util.ImageManager;
import java.util.List;
import java.awt.*;
import java.util.Random;


public abstract class Animal {
    protected Point posicion;
    protected TipoComida tipoComidaPreferida;
    protected String tipoHabitat;
    protected Image imagen;
    public static final int ANCHO = 64;
    public static final int ALTO = 64;
    protected Habitat habitatActual;
    protected boolean estaComiendo;
    protected Comida comidaObjetivo;
    protected Rectangle bounds;
    protected static final int MAX_HAMBRE = 100;
    protected static final int INCREMENTO_HAMBRE = 1;
    protected int nivelHambre;
    protected boolean tieneHambre;
    protected static final int UMBRAL_HAMBRE = 70;
    protected static final int VELOCIDAD_NORMAL = 1;
    protected static final int VELOCIDAD_HAMBRIENTO = 2;
    protected Point posicionInicial;
    protected int direccion = 1;
    protected static final int RANGO_MOVIMIENTO = 100;

    public Animal(Point posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posición no puede ser null");
        }
        this.posicion = new Point(posicion);
        this.posicionInicial = new Point(posicion);
        this.bounds = new Rectangle(posicion.x, posicion.y, ANCHO, ALTO);
        this.estaComiendo = false;
        this.comidaObjetivo = null;
        this.nivelHambre = 0;
        this.tieneHambre = false;
        inicializarAnimal();
        cargarImagen();
    }
    public void setPosicion(Point posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posición no puede ser null");
        }
        this.posicion = new Point(posicion);
        this.posicionInicial = new Point(posicion);
        System.out.println("Posición del animal actualizada a: " + posicion.x + ", " + posicion.y);
    }
    protected abstract void inicializarAnimal();

    protected void cargarImagen() {
        if (tipoHabitat == null) {
            throw new IllegalStateException("tipoHabitat no ha sido inicializado");
        }
        String nombreClase = getClass().getSimpleName().toLowerCase();
        this.imagen = ImageManager.getInstance().getAnimalImage(tipoHabitat, nombreClase);
    }

    public void mover() {
        if (habitatActual == null) return;

        if (comidaObjetivo != null && !comidaObjetivo.isConsumida()) {
            // Mover hacia la comida
            moverHacia(comidaObjetivo.getPosicion(), VELOCIDAD_HAMBRIENTO);
            if (alcanzaComida()) {
                comer();
            }
        } else {
            // Movimiento horizontal
            moverHorizontal();
        }
    }
    private void moverHorizontal() {
        if (habitatActual == null) return;

        Rectangle limites = habitatActual.getLimites();

        // Mover horizontalmente
        posicion.x += VELOCIDAD_NORMAL * direccion;

        // Verificar límites del hábitat y cambiar dirección
        if (posicion.x >= limites.x + limites.width - ANCHO) {
            posicion.x = limites.x + limites.width - ANCHO;
            direccion = -1; // Cambiar dirección a izquierda
        } else if (posicion.x <= limites.x) {
            posicion.x = limites.x;
            direccion = 1; // Cambiar dirección a derecha
        }
    }
    private void moverHacia(Point objetivo, int velocidad) {
        double dx = objetivo.x - posicion.x;
        double dy = objetivo.y - posicion.y;
        double distancia = Math.sqrt(dx * dx + dy * dy);

        if (distancia > velocidad) {
            posicion.x += (int)((dx / distancia) * velocidad);
            posicion.y += (int)((dy / distancia) * velocidad);
        } else {
            posicion.x = objetivo.x;
            posicion.y = objetivo.y;
        }

        // Asegurar que el animal se mantenga dentro del hábitat
        Rectangle limites = habitatActual.getLimites();
        posicion.x = Math.min(Math.max(posicion.x, limites.x),
                limites.x + limites.width - ANCHO);
        posicion.y = Math.min(Math.max(posicion.y, limites.y),
                limites.y + limites.height - ALTO);
    }
    private void moverAleatorio() {
        Rectangle limites = habitatActual.getLimites();
        Random rand = new Random();

        int dx = rand.nextInt(5) - 2;
        int dy = rand.nextInt(5) - 2;


        int newX = Math.min(Math.max(posicion.x + dx, limites.x),
                limites.x + limites.width - ANCHO);
        int newY = Math.min(Math.max(posicion.y + dy, limites.y),
                limites.y + limites.height - ALTO);

        posicion.x = newX;
        posicion.y = newY;
    }

    private boolean alcanzaComida() {
        if (comidaObjetivo == null) return false;

        Rectangle boundsAnimal = new Rectangle(posicion.x, posicion.y, 50, 50);
        Rectangle boundsComida = new Rectangle(
                comidaObjetivo.getPosicion().x,
                comidaObjetivo.getPosicion().y,
                30, 30
        );

        return boundsAnimal.intersects(boundsComida);
    }
    public void comer() {
        if (comidaObjetivo != null && !comidaObjetivo.isConsumida()) {
            comidaObjetivo.consumir();
            nivelHambre = 0;
            tieneHambre = false;
            Zoo.getInstance().removerAlertaHambre(this);
            comidaObjetivo = null;
        }
    }
    public void buscarComida(List<Comida> comidasDisponibles) {
        if (estaComiendo || comidaObjetivo != null) return;

        Comida comidaMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Comida comida : comidasDisponibles) {
            if (comida.getTipo() == tipoComidaPreferida && !comida.isConsumida()) {
                double distancia = calcularDistancia(comida.getPosicion());
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    comidaMasCercana = comida;
                }
            }
        }

        comidaObjetivo = comidaMasCercana;
    }

    private double calcularDistancia(Point otro) {
        double dx = otro.x - posicion.x;
        double dy = otro.y - posicion.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setHabitat(Habitat habitat) {
        this.habitatActual = habitat;
    }


    public void dibujar(Graphics2D g) {
        // Dibujar el animal
        if (imagen != null) {
            g.drawImage(imagen, posicion.x, posicion.y, ANCHO, ALTO, null);
        }

        // Dibujar ícono de hambre si es necesario
        if (tieneHambre) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("!", posicion.x + ANCHO/2 - 5, posicion.y - 5);
        }
    }
    public void actualizarHambre() {
        if (!tieneHambre) {
            nivelHambre += INCREMENTO_HAMBRE;
            if (nivelHambre >= UMBRAL_HAMBRE) {
                tieneHambre = true;
                Zoo.getInstance().notificarAnimalHambriento(this);
            }
        }
    }
    private void notificarHambre() {
        if (habitatActual != null) {
            Zoo.getInstance().notificarAnimalHambriento(this);
        }
    }
    public String getNombre() {
        return getClass().getSimpleName();
    }
    public Habitat getHabitat() {
        return habitatActual;
    }
    public Comida getComidaObjetivo() {
        return comidaObjetivo;
    }

    public void setComidaObjetivo(Comida comida) {
        this.comidaObjetivo = comida;
    }

    public TipoComida getTipoComidaPreferida() {
        return tipoComidaPreferida;
    }
    public boolean isEstaComiendo() {
        return estaComiendo;
    }

    public void setEstaComiendo(boolean estaComiendo) {
        this.estaComiendo = estaComiendo;
    }

    public Point getPosicion() {
        return new Point(posicion);
    }

    public String getTipoHabitat() {
        return tipoHabitat;
    }

    }
