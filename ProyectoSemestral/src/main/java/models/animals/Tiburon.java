package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Tiburon extends Animal {
    public Tiburon() {
        super("Tiburon" , TipoAnimal.PEZ, TipoComida.CARNE, TipoHabitat.OCEANO_Y_ACUARIOS);
    }
}
