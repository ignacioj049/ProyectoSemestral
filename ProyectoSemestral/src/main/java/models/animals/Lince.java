package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Lince extends Animal {
    public Lince() {
        super("Lince" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.BOSQUES_TEMPLADOS);
    }
}
