package models.food;
import enums.TipoComida;
import models.food.Comida;

public class Carne extends Comida {
    public Carne() {
        super("Carne", TipoComida.CARNE);
    }
}