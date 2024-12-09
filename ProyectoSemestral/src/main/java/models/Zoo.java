package models;
import models.habitats.Habitat;
import models.animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private static Zoo instance;
    private List<Habitat> habitats;

    public Zoo() {
        habitats = new ArrayList<>();
    }

    public static Zoo getInstance() {
        if (instance == null) {
            instance = new Zoo();
        }
        return instance;
    }

    public void agregarHabitat(Habitat habitat) {
        habitats.add(habitat);
    }

    public Habitat buscarHabitatPorNombre(String nombre) {
        for (Habitat habitat : habitats) {
            if (habitat.getNombre().equals(nombre)) {
                return habitat;
            }
        }
        return null;
    }

    public Animal buscarAnimalPorNombre(String nombre, Habitat habitat) {
        for (Animal animal : habitat.getAnimales()) {
            if (animal.getNombre().equals(nombre)) {
                return animal;
            }
        }
        return null;
    }

    public void moverAnimal(Animal animal, Habitat origen, Habitat destino) {
        if (origen.getAnimales().remove(animal)) {
            destino.agregarAnimal(animal);
        }
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }
}