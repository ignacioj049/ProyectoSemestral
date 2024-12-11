package models.animals;

import enums.TipoAnimal;
import models.habitats.Habitat;

public class Animal {
    private String nombre;
    private TipoAnimal tipo;
    private double nivelSalud;
    private double nivelHambre;
    private double nivelFelicidad;
    private Habitat habitat;

    public Animal(String nombre, TipoAnimal tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivelSalud = 100.0;
        this.nivelHambre = 100.0;
        this.nivelFelicidad = 100.0;
    }

    public void actualizarEstado() {
        // Reducir niveles con el tiempo
        nivelHambre = Math.max(0, nivelHambre - 2);  // El hambre baja más rápido
        nivelFelicidad = Math.max(0, nivelFelicidad - 1);

        // La salud se ve afectada si el animal está hambriento o infeliz
        if (nivelHambre < 30 || nivelFelicidad < 30) {
            nivelSalud = Math.max(0, nivelSalud - 1);
        }

        // Si el animal está en un hábitat inadecuado, afecta su felicidad y salud
        if (habitat != null && habitat.getTipo() != tipo.getHabitatPreferido()) {
            nivelFelicidad = Math.max(0, nivelFelicidad - 2);
            nivelSalud = Math.max(0, nivelSalud - 0.5);
        }
    }

    public void alimentar(double cantidad) {
        nivelHambre = Math.min(100, nivelHambre + (cantidad * 20));
        if (nivelHambre > 80) {
            nivelFelicidad = Math.min(100, nivelFelicidad + 10);
        }
    }

    public void curar() {
        nivelSalud = 100.0;
        nivelFelicidad = Math.min(100, nivelFelicidad + 20);
    }

    public void jugar() {
        nivelFelicidad = Math.min(100, nivelFelicidad + 30);
        nivelHambre = Math.max(0, nivelHambre - 10);
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public TipoAnimal getTipo() {
        return tipo;
    }

    public double getNivelSalud() {
        return nivelSalud;
    }

    public double getNivelHambre() {
        return nivelHambre;
    }

    public double getNivelFelicidad() {
        return nivelFelicidad;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public boolean necesitaAtencion() {
        return nivelSalud < 50 || nivelHambre < 30 || nivelFelicidad < 30;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Salud: %.0f%%, Hambre: %.0f%%, Felicidad: %.0f%%",
                nombre, tipo.getNombre(), nivelSalud, nivelHambre, nivelFelicidad);
    }
}