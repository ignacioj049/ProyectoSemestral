package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Lechuza extends Animal {
    public Lechuza() {
        super("Lechuza" , TipoAnimal.AVE, TipoComida.CARNE, TipoHabitat.BOSQUES_TEMPLADOS);
    }
}
