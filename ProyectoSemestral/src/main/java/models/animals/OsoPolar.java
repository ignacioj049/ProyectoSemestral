package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class OsoPolar extends Animal {
    public OsoPolar() {
        super("Oso Polar" , TipoAnimal.MAMIFERO, TipoComida.PESCADO, TipoHabitat.REGION_ARTICA);
    }
}
