package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Leon extends Animal {
    public Leon() {
        super("León", TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.SABANA_AFRICANA);
    }
}