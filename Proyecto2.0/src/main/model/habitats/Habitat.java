package model.habitats;

import model.animals.Animal;
import util.Constants;
import util.ImageManager;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.util.Random;

public abstract class Habitat {
    protected Point posicion;
    protected Dimension tamano;
    protected String tipo;
    protected Image imagen;
    protected List<String> animalesPermitidos;

    public Habitat(Point posicion, String tipo) {
        this.posicion = posicion;
        this.tamano = new Dimension(300, 300); // Tamaño fijo que coincide con la cuadrícula
        this.tipo = tipo;
        this.animalesPermitidos = new ArrayList<>();
        cargarImagen();
    }
    public Rectangle getLimites() {
        return new Rectangle(posicion.x, posicion.y, tamano.width, tamano.height);
    }
    private void cargarImagen() {
        this.imagen = ImageManager.getInstance().getHabitatImage(tipo);
    }

    public boolean contienePunto(Point p) {
        return new Rectangle(posicion.x, posicion.y, tamano.width, tamano.height).contains(p);
    }

    public boolean puedeAlbergar(Animal animal) {
        if (animal == null || animal.getTipoHabitat() == null) {
            return false;
        }
        return animal.getTipoHabitat().equalsIgnoreCase(this.tipo);
    }

    public void dibujar(Graphics2D g) {
        if (imagen != null) {
            g.drawImage(imagen, posicion.x, posicion.y, tamano.width, tamano.height, null);
            // Dibujar borde
            g.setColor(new Color(0, 0, 0, 100));
            g.drawRect(posicion.x, posicion.y, tamano.width, tamano.height);
            // Dibujar nombre
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(tipo, posicion.x + 10, posicion.y + 25);
        }
    }

    public Point getPosicion() {
        return new Point(posicion);
    }
    public Point getPosicionAleatoria() {
        Random rand = new Random();
        int x = posicion.x + rand.nextInt(tamano.width - 50);
        int y = posicion.y + rand.nextInt(tamano.height - 50);
        return new Point(x, y);
    }

    public String getTipo() {
        return tipo;
    }
    public Dimension getTamano() {
        return new Dimension(tamano);
    }

}
