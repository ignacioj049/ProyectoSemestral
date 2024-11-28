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

    // Getters y Setters
    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }
}