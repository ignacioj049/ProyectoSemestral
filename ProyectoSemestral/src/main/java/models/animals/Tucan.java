package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Tucan extends Animal {
    public Tucan() {
        super("Tucán" , TipoAnimal.AVE, TipoComida.FRUTAS, TipoHabitat.JUNGLA_TROPICAL);
    }
}
