package models.food;
import enums.TipoComida;
import models.food.Comida;

public class Vegetales extends Comida {
    public Vegetales() {
        super("Vegetales", TipoComida.VEGETALES);
    }
}