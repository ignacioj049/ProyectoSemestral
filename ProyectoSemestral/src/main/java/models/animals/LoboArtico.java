package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class LoboArtico extends Animal {
    public LoboArtico() {
        super("Lobo Ártico" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.REGION_ARTICA);
    }
}
