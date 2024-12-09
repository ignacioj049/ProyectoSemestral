package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Avestruz extends Animal {
    public Avestruz() {
        super("Avestruz", TipoAnimal.AVE, TipoComida.VEGETALES, TipoHabitat.SABANA_AFRICANA);
    }
}