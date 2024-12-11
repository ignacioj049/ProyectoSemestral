package models;

import models.animals.Animal;
import models.habitats.Habitat;
import enums.TipoHabitat;
import java.util.HashMap;
import java.util.Map;

public class Zoo {
    private String nombre;
    private Map<String, Habitat> habitats;
    private double presupuesto;

    public Zoo(String nombre, double presupuestoInicial) {
        this.nombre = nombre;
        this.habitats = new HashMap<>();
        this.presupuesto = presupuestoInicial;
    }

    public void agregarHabitat(Habitat habitat) {
        if (habitats.containsKey(habitat.getId())) {
            throw new IllegalArgumentException("Ya existe un hábitat con el ID: " + habitat.getId());
        }
        habitats.put(habitat.getId(), habitat);
    }

    public void removerHabitat(String habitatId) {
        if (!habitats.containsKey(habitatId)) {
            throw new IllegalArgumentException("No existe un hábitat con el ID: " + habitatId);
        }
        habitats.remove(habitatId);
    }

    public Habitat buscarHabitat(String habitatId) {
        return habitats.get(habitatId);
    }

    public void agregarAnimal(String habitatId, Animal animal) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("No existe el hábitat especificado");
        }
        habitat.agregarAnimal(animal);
    }

    public void removerAnimal(String habitatId, String nombreAnimal) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("No existe el hábitat especificado");
        }
        habitat.removerAnimal(nombreAnimal);
    }

    public void actualizarEstado() {
        for (Habitat habitat : habitats.values()) {
            habitat.actualizarEstado();
        }
    }

    public void alimentarAnimal(String habitatId, String nombreAnimal, double cantidadComida) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("No existe el hábitat especificado");
        }

        Animal animal = habitat.getAnimales().get(nombreAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("No existe el animal especificado");
        }

        animal.alimentar(cantidadComida);
    }

    public void limpiarHabitat(String habitatId) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("No existe el hábitat especificado");
        }
        habitat.limpiar();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public Map<String, Habitat> getHabitats() {
        return new HashMap<>(habitats);
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        if (presupuesto < 0) {
            throw new IllegalArgumentException("El presupuesto no puede ser negativo");
        }
        this.presupuesto = presupuesto;
    }

    public void agregarFondos(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.presupuesto += cantidad;
    }

    public void reducirFondos(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (cantidad > presupuesto) {
            throw new IllegalStateException("No hay suficientes fondos");
        }
        this.presupuesto -= cantidad;
    }

    public boolean tieneHabitatDisponible(TipoHabitat tipo) {
        return habitats.values().stream()
                .anyMatch(h -> h.getTipo() == tipo && h.tieneEspacioDisponible());
    }

    public Habitat obtenerHabitatDisponible(TipoHabitat tipo) {
        return habitats.values().stream()
                .filter(h -> h.getTipo() == tipo && h.tieneEspacioDisponible())
                .findFirst()
                .orElse(null);
    }
}