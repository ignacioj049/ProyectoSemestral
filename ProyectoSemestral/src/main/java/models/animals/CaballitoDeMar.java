package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class CaballitoDeMar extends Animal {
    public CaballitoDeMar() {
        super("Caballito de Mar", TipoAnimal.PEZ, TipoComida.PESCADO, TipoHabitat.OCEANO_Y_ACUARIOS);
    }
}