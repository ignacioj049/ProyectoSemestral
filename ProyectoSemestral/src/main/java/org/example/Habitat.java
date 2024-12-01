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

    public Habitat(String nombre, String tipo, int capacidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.animales = new ArrayList<>();
        this.comidaCarne = 0;
        this.comidaVegetales = 0;
        this.comidaFrutas = 0;
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
        }
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public List<Animal> getAnimales() { return animales; }
    public void setAnimales(List<Animal> animales) { this.animales = animales; }
    public int getComidaCarne() { return comidaCarne; }
    public int getComidaVegetales() { return comidaVegetales; }
    public int getComidaFrutas() { return comidaFrutas; }
}