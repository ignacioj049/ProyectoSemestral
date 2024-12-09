package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Serpiente extends Animal {
    public Serpiente() {
        super("Serpiente" , TipoAnimal.REPTIL, TipoComida.CARNE, TipoHabitat.DESIERTO);
    }
}
