package models.food;
import enums.TipoComida;
import models.food.Comida;

public class Pescado extends Comida {
    public Pescado() {
        super("Pescado", TipoComida.PESCADO);
    }
}