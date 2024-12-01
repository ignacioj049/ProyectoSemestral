import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private String nombre;
    private String tipo;
    private int capacidad;
    private List<Animal> animales;
    private int cantidadComida; // Nueva variable para almacenar la cantidad de comida

    public Habitat(String nombre, String tipo, int capacidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.animales = new ArrayList<>();
        this.cantidadComida = 0; // Inicializar la cantidad de comida a 0
    }

    public void agregarAnimal(Animal animal) {
        if (animales.size() < capacidad) {
            animales.add(animal);
        } else {
            System.out.println("Capacidad máxima alcanzada para el hábitat: " + nombre);
        }
    }

    public void agregarComida(String tipo, int cantidad) {
        // Aquí puedes añadir lógica para manejar diferentes tipos de comida si es necesario
        this.cantidadComida += cantidad;
    }

    public int getCantidadComida() {
        return cantidadComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }
}