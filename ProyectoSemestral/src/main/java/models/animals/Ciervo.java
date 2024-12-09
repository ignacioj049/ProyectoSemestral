package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Ciervo extends Animal {
    public Ciervo() {
        super("Ciervo", TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.BOSQUES_TEMPLADOS);
    }
}