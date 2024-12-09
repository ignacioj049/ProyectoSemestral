package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Elefante extends Animal {
    public Elefante() {
        super("Elefante" , TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.SABANA_AFRICANA);
    }
}
