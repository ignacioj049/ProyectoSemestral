package models.habitats;
import enums.TipoHabitat;
import models.animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private String nombre;
    private String tipo;
    private int capacidad;
    private List<Animal> animales;
    private int comidaCarne;
    private int comidaVegetales;
    private int comidaFrutas;
    private int comidaPescado;

    public Habitat(String nombre, String tipo, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.animales = new ArrayList<>();
        this.comidaCarne = 0;
        this.comidaVegetales = 0;
        this.comidaFrutas = 0;
        this.comidaPescado = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void agregarAnimal(Animal animal) {
        if (animales.size() < capacidad) {
            animales.add(animal);
        } else {
            System.out.println("Capacidad máxima alcanzada para el hábitat: " + nombre);
        }
    }

    public void agregarComida(String tipo, int cantidad) {
        switch (tipo.toLowerCase()) {
            case "carne":
                comidaCarne += cantidad;
                break;
            case "vegetales":
                comidaVegetales += cantidad;
                break;
            case "frutas":
                comidaFrutas += cantidad;
                break;
            case "pescado":
                comidaPescado += cantidad;
                break;
        }
    }

    public void consumirComida(String tipo, int cantidad) {
        switch (tipo.toLowerCase()) {
            case "carne":
                comidaCarne = Math.max(comidaCarne - cantidad, 0);
                break;
            case "vegetales":
                comidaVegetales = Math.max(comidaVegetales - cantidad, 0);
                break;
            case "frutas":
                comidaFrutas = Math.max(comidaFrutas - cantidad, 0);
                break;
            case "pescado":
                comidaPescado = Math.max(comidaPescado - cantidad, 0);
                break;
        }
    }

    public int getComidaCarne() {
        return comidaCarne;
    }

    public int getComidaVegetales() {
        return comidaVegetales;
    }

    public int getComidaFrutas() {
        return comidaFrutas;
    }

    public int getComidaPescado() {
        return comidaPescado;
    }
}