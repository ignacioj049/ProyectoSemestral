package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Pinguino extends Animal {
    public Pinguino() {
        super("Pingüino" , TipoAnimal.AVE, TipoComida.PESCADO, TipoHabitat.REGION_ARTICA);
    }
}
