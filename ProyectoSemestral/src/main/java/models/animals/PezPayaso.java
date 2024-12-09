package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class PezPayaso extends Animal {
    public PezPayaso() {
        super("Pez Payazo" , TipoAnimal.PEZ, TipoComida.PESCADO, TipoHabitat.OCEANO_Y_ACUARIOS);
    }
}
