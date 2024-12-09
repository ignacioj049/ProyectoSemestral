package models.animals;
import models.animals.Animal;

public class EstadoAnimal {
    private Animal animal;
    private String estadoSalud;
    private String estadoAnimo;

    public EstadoAnimal(Animal animal, String estadoSalud, String estadoAnimo) {
        this.animal = animal;
        this.estadoSalud = estadoSalud;
        this.estadoAnimo = estadoAnimo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getEstadoAnimo() {
        return estadoAnimo;
    }

    public void setEstadoAnimo(String estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }
}
