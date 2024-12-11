package controllers;

import models.habitats.Habitat;
import models.animals.Animal;
import enums.TipoAnimal;
import enums.TipoHabitat;

import java.util.HashMap;
import java.util.Map;

public class ZooController {
    private Map<String, Habitat> habitats;
    private double presupuesto;

    public ZooController() {
        this.habitats = new HashMap<>();
        this.presupuesto = 10000.0; // Presupuesto inicial
    }

    public void crearHabitat(String id, TipoHabitat tipo, int capacidad) {
        if (habitats.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un hábitat con ese ID");
        }
        habitats.put(id, new Habitat(id, tipo, capacidad));
    }

    public Habitat obtenerHabitat(String id) {
        return habitats.get(id);
    }

    public void crearAnimal(String nombre, TipoAnimal tipo, String habitatId) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("No existe el hábitat especificado");
        }

        if (tipo.getHabitatPreferido() != habitat.getTipo()) {
            throw new IllegalArgumentException("Este tipo de animal no puede vivir en este hábitat");
        }

        Animal nuevoAnimal = new Animal(nombre, tipo);
        habitat.agregarAnimal(nuevoAnimal);
    }

    public Map<String, Habitat> getHabitats() {
        return new HashMap<>(habitats);
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void actualizarEstadoGeneral() {
        for (Habitat habitat : habitats.values()) {
            habitat.actualizarEstado();
        }
    }
    // ... métodos existentes ...

    public void agregarFondos(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        presupuesto += cantidad;
    }

    public void limpiarHabitat(String habitatId) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("Hábitat no encontrado");
        }

        double costoLimpieza = 100.0;
        if (presupuesto < costoLimpieza) {
            throw new IllegalStateException("No hay suficiente presupuesto");
        }

        presupuesto -= costoLimpieza;
        habitat.limpiar();
    }

    public void alimentarAnimal(String habitatId, String nombreAnimal, double cantidadComida) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("Hábitat no encontrado");
        }

        Animal animal = habitat.getAnimales().get(nombreAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("Animal no encontrado");
        }

        double costoComida = cantidadComida * 50.0; // 50 por kilo
        if (presupuesto < costoComida) {
            throw new IllegalStateException("No hay suficiente presupuesto");
        }

        presupuesto -= costoComida;
        animal.alimentar(cantidadComida);
    }

    public void atenderAnimal(String habitatId, String nombreAnimal) {
        Habitat habitat = habitats.get(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("Hábitat no encontrado");
        }

        Animal animal = habitat.getAnimales().get(nombreAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("Animal no encontrado");
        }

        double costoAtencion = 200.0;
        if (presupuesto < costoAtencion) {
            throw new IllegalStateException("No hay suficiente presupuesto");
        }

        presupuesto -= costoAtencion;
        animal.curar();
    }
}