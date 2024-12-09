package models.animals;
import enums.TipoHabitat;
import enums.TipoAnimal;
import enums.TipoComida;

public class Rinoceronte extends Animal {
    public Rinoceronte() {
        super("Rinoceronte" , TipoAnimal.MAMIFERO, TipoComida.VEGETALES, TipoHabitat.SABANA_AFRICANA);
    }
}
