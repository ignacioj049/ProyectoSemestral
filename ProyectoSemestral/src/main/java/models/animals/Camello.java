package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Camello extends Animal {
    public Camello() {
        super("Camello", TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.DESIERTO);
    }
}