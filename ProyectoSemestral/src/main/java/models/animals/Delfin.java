package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Delfin extends Animal {
    public Delfin() {
        super("Delfín" , TipoAnimal.MAMIFERO_MARINO, TipoComida.PESCADO, TipoHabitat.OCEANO_Y_ACUARIOS);
    }
}
