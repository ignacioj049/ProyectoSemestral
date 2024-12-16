package model.food;

import model.TipoComida;

import java.awt.*;

public class Carne extends Comida {
    public Carne(Point posicion) {
        super(posicion, "carne");
        this.tipo = TipoComida.CARNE;
    }
}
