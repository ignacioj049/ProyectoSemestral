package models.habitats;

import models.animals.Animal;
import enums.TipoHabitat;

import java.util.HashMap;
import java.util.Map;

public class Habitat {
    private String id;
    private TipoHabitat tipo;
    private int capacidad;
    private Map<String, Animal> animales;
    private double nivelLimpieza;

    public Habitat(String id, TipoHabitat tipo, int capacidad) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.animales = new HashMap<>();
        this.nivelLimpieza = 100.0;
    }

    public void agregarAnimal(Animal animal) {
        if (animales.size() >= capacidad) {
            throw new IllegalStateException("El hábitat está lleno");
        }
        animales.put(animal.getNombre(), animal);
        animal.setHabitat(this);
    }

    public void removerAnimal(String nombre) {
        Animal animal = animales.remove(nombre);
        if (animal != null) {
            animal.setHabitat(null);
        }
    }

    public void actualizarEstado() {
        // Reducir nivel de limpieza basado en la cantidad de animales
        nivelLimpieza = Math.max(0, nivelLimpieza - (animales.size() * 0.5));

        // Actualizar estado de los animales
        for (Animal animal : animales.values()) {
            animal.actualizarEstado();
        }
    }

    public void limpiar() {
        nivelLimpieza = 100.0;
        // Aumentar felicidad de los animales cuando se limpia el hábitat
        for (Animal animal : animales.values()) {
            animal.jugar();
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public TipoHabitat getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Map<String, Animal> getAnimales() {
        return new HashMap<>(animales);
    }

    public double getNivelLimpieza() {
        return nivelLimpieza;
    }

    public boolean necesitaLimpieza() {
        return nivelLimpieza < 50;
    }

    public boolean tieneEspacioDisponible() {
        return animales.size() < capacidad;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Limpieza: %.0f%%, Animales: %d/%d",
                id, tipo.getNombre(), nivelLimpieza, animales.size(), capacidad);
    }
}