package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Fennec extends Animal {
    public Fennec() {
        super("Fennec" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.DESIERTO);
    }
}
