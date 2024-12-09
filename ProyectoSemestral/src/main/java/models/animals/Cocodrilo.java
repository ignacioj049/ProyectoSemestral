package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Cocodrilo extends Animal {
    public Cocodrilo() {
        super("Cocodrilo" , TipoAnimal.REPTIL, TipoComida.CARNE, TipoHabitat.JUNGLA_TROPICAL);
    }
}