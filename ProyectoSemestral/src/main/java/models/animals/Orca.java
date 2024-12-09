package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Orca extends Animal {
    public Orca() {
        super("Orca" , TipoAnimal.MAMIFERO_MARINO, TipoComida.PESCADO, TipoHabitat.OCEANO_Y_ACUARIOS);
    }
}
