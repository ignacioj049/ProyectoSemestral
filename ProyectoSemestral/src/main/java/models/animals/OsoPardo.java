package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class OsoPardo extends Animal {
    public OsoPardo() {
        super("Oso Pardo" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.BOSQUES_TEMPLADOS);
    }
}
