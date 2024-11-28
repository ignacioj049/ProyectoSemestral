import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private String nombre;
    private String tipo;
    private List<Animal> animales;

    public Habitat(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.animales = new ArrayList<>();
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
    }

    // Getters y Setters
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

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }
}