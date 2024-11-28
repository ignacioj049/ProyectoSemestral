import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Habitat> habitats;

    public Zoo() {
        this.habitats = new ArrayList<>();
    }

    public void agregarHabitat(Habitat habitat) {
        habitats.add(habitat);
    }

    public void moverAnimal(Animal animal, Habitat origen, Habitat destino) {
        if (origen.getAnimales().remove(animal)) {
            destino.agregarAnimal(animal);
        }
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

    // Getters y Setters
    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }
}