package model;

import model.animals.Animal;
import model.food.Comida;
import model.habitats.Habitat;
import observer.Observer;
import ui.ZooSimulator;
import ui.components.AlertPanel;
import ui.components.ZooPanel;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static model.animals.Animal.ALTO;
import static model.animals.Animal.ANCHO;

public class Zoo {
    private static Zoo instance;
    private final List<Habitat> habitats;
    private final Map<Habitat, List<Animal>> animalesPorHabitat;
    private final Map<Habitat, List<Comida>> comidaPorHabitat;
    private final List<Observer> observers;
    private static final int MAX_HABITATS = 6;
    private AlertPanel alertPanel;
    private final Set<String> alertasHambre;
    private ZooPanel zooPanel;
    private Zoo() {
        habitats = new ArrayList<>();
        animalesPorHabitat = new HashMap<>();
        comidaPorHabitat = new HashMap<>();
        observers = new ArrayList<>();
        alertasHambre = new HashSet<>();

    }

    public static Zoo getInstance() {
        if (instance == null) {
            instance = new Zoo();
        }
        return instance;
    }
    public boolean puedeAgregarHabitat() {
        return habitats.size() < MAX_HABITATS;
    }

    public boolean addHabitat(Habitat habitat) {
        if (!puedeAgregarHabitat()) {
            System.out.println("No se pueden agregar más hábitats");
            return false;
        }

        if (hayHabitatEnPosicion(habitat.getPosicion())) {
            System.out.println("Ya existe un hábitat en esta posición");
            return false;
        }

        habitats.add(habitat);
        animalesPorHabitat.put(habitat, new ArrayList<>());
        comidaPorHabitat.put(habitat, new ArrayList<>());
        System.out.println("Hábitat agregado en posición: " + habitat.getPosicion());
        notifyObservers();
        return true;
    }

    public boolean addAnimal(Animal animal, Habitat habitat) {
        if (habitat.puedeAlbergar(animal)) {
            List<Animal> animales = animalesPorHabitat.get(habitat);
            if (animales == null) {
                animales = new ArrayList<>();
                animalesPorHabitat.put(habitat, animales);
            }

            // Usar la posición actual del animal
            Point posicion = animal.getPosicion();
            Rectangle limites = habitat.getLimites();

            int x = Math.min(Math.max(posicion.x, limites.x), limites.x + limites.width - ANCHO);
            int y = Math.min(Math.max(posicion.y, limites.y), limites.y + limites.height - ALTO);

            Point posicionAjustada = new Point(x, y);
            animal.setPosicion(posicionAjustada);
            animal.setHabitat(habitat);
            animales.add(animal);

            System.out.println("Posición original: " + posicion.x + ", " + posicion.y);
            System.out.println("Posición ajustada: " + x + ", " + y);
            System.out.println("Límites del hábitat: " + limites);
            notifyObservers();
            return true;
        }
        return false;
    }
    public boolean hayHabitatEnPosicion(Point punto) {
        // Convertir el punto a los límites del hábitat
        Rectangle nuevaArea = new Rectangle(punto.x, punto.y, 300, 300);

        for (Habitat habitat : habitats) {
            Rectangle habitatArea = new Rectangle(
                    habitat.getPosicion().x,
                    habitat.getPosicion().y,
                    habitat.getTamano().width,
                    habitat.getTamano().height
            );

            if (habitatArea.intersects(nuevaArea)) {
                System.out.println("Hábitat encontrado en posición: " + punto);
                return true;
            }
        }

        System.out.println("No hay hábitat en posición: " + punto);
        return false;
    }

    public void addComida(Comida comida, Habitat habitat) {
        comidaPorHabitat.get(habitat).add(comida);
        notifyObservers();
    }

    public void distribuirComida(Habitat habitat) {
        List<Animal> animales = animalesPorHabitat.get(habitat);
        List<Comida> comidas = comidaPorHabitat.get(habitat);

        if (animales == null || comidas == null) return;

        // Filtrar comida no consumida
        List<Comida> comidaDisponible = comidas.stream()
                .filter(c -> !c.isConsumida())
                .collect(Collectors.toList());

        // Asignar comida a los animales que no estén comiendo
        for (Animal animal : animales) {
            if (animal.getComidaObjetivo() == null && !comidaDisponible.isEmpty()) {
                Comida comida = encontrarComidaAdecuada(animal, comidaDisponible);
                if (comida != null) {
                    animal.setComidaObjetivo(comida);
                    comidaDisponible.remove(comida);
                }
            }
        }
    }

    private Comida encontrarComidaAdecuada(Animal animal, List<Comida> comidas) {
        return comidas.stream()
                .filter(c -> !c.isConsumida())
                .filter(c -> c.getTipo() == animal.getTipoComidaPreferida())
                .findFirst()
                .orElse(null);
    }

    public void verificarComida() {
        for (Map.Entry<Habitat, List<Animal>> entry : animalesPorHabitat.entrySet()) {
            Habitat habitat = entry.getKey();
            List<Animal> animales = entry.getValue();
            List<Comida> comidas = comidaPorHabitat.get(habitat);

            if (comidas == null) continue;

            long comidaDisponible = comidas.stream()
                    .filter(c -> !c.isConsumida())
                    .count();

            if (comidaDisponible < animales.size()) {
                notifyFaltaComida(habitat);
            }
        }
    }

    private void notifyFaltaComida(Habitat habitat) {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void actualizarEstado() {
        // Actualizar cada animal en cada hábitat
        for (Map.Entry<Habitat, List<Animal>> entry : animalesPorHabitat.entrySet()) {
            Habitat habitat = entry.getKey();
            List<Animal> animales = entry.getValue();
            List<Comida> comidas = comidaPorHabitat.get(habitat);

            for (Animal animal : animales) {
                animal.buscarComida(comidas);
                animal.mover();
            }
        }
        for (List<Animal> animales : animalesPorHabitat.values()) {
            for (Animal animal : animales) {
                animal.actualizarHambre();
            }
        }
        // Limpiar comida consumida
        for (List<Comida> comidas : comidaPorHabitat.values()) {
            comidas.removeIf(Comida::isConsumida);
        }

        notifyObservers();
    }

    private void actualizarAnimales() {
        for (List<Animal> animales : animalesPorHabitat.values()) {
            for (Animal animal : animales) {
                moverAnimalAleatoriamente(animal);
            }
        }
    }

    private void moverAnimalAleatoriamente(Animal animal) {
        Random rand = new Random();
        Point posicion = animal.getPosicion();
        int dx = rand.nextInt(21) - 10;
        int dy = rand.nextInt(21) - 10;

        animal.setPosicion(new Point(
                Math.max(0, Math.min(posicion.x + dx, 800)),
                Math.max(0, Math.min(posicion.y + dy, 600))
        ));
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public void setAlertPanel(AlertPanel alertPanel) {
        this.alertPanel = alertPanel;
    }

    // Getters
    public List<Habitat> getHabitats() {
        return new ArrayList<>(habitats);
    }

    public Map<Habitat, List<Animal>> getAnimalesPorHabitat() {
        return new HashMap<>(animalesPorHabitat);
    }

    public Map<Habitat, List<Comida>> getComidaPorHabitat() {
        return new HashMap<>(comidaPorHabitat);
    }

    public Habitat getHabitatEnPunto(Point punto) {
        for (Habitat habitat : habitats) {
            Rectangle bounds = habitat.getLimites();
            if (bounds.contains(punto)) {
                return habitat;
            }
        }
        return null;
    }
    public int getTotalAnimales() {
        int total = 0;
        for (List<Animal> animales : animalesPorHabitat.values()) {
            total += animales.size();
        }
        return total;
    }
    public int getTotalComida() {
        int total = 0;
        for (List<Comida> comidas : comidaPorHabitat.values()) {
            total += comidas.stream().filter(c -> !c.isConsumida()).count();
        }
        return total;
    }
    public List<Animal> getTodosLosAnimales() {
        List<Animal> todosLosAnimales = new ArrayList<>();
        for (List<Animal> animales : animalesPorHabitat.values()) {
            todosLosAnimales.addAll(animales);
        }
        return todosLosAnimales;
    }

    public void setZooPanel(ZooPanel zooPanel) {
        this.zooPanel = zooPanel;
    }
    public void notificarAnimalHambriento(Animal animal) {
        String mensaje = "¡" + animal.getNombre() + " en hábitat " +
                animal.getHabitat().getTipo() + " tiene hambre!";
        alertasHambre.add(mensaje);
        if (zooPanel != null) {
            zooPanel.actualizarAlertas(alertasHambre);
        }
        notifyObservers();
    }
    public void removerAlertaHambre(Animal animal) {
        String mensaje = "¡" + animal.getNombre() + " en hábitat " +
                animal.getHabitat().getTipo() + " tiene hambre!";
        alertasHambre.remove(mensaje);
        if (zooPanel != null) {
            zooPanel.actualizarAlertas(alertasHambre);
        }
        notifyObservers();
    }
}
