package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Jirafa extends Animal {
    public Jirafa() {
        super("Jirafa" , TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.SABANA_AFRICANA);
    }
}
