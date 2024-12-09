package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Aguila extends Animal {
    public Aguila() {
        super("Águila", TipoAnimal.AVE, TipoComida.CARNE, TipoHabitat.BOSQUES_TEMPLADOS);
    }
}