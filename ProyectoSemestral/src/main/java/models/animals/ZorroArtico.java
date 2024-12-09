package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class ZorroArtico extends Animal {
    public ZorroArtico() {
        super("Zorro Ártico" , TipoAnimal.MAMIFERO, TipoComida.CARNE, TipoHabitat.REGION_ARTICA);
    }
}
