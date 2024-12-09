package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Cebra extends Animal {
    public Cebra() {
        super("Cebra", TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.SABANA_AFRICANA);
    }
}