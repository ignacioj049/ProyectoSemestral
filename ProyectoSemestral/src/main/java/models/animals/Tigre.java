package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Tigre extends Animal {
    public Tigre() {
        super("Tigre" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.JUNGLA_TROPICAL);
    }
}
