package model.food;

import model.TipoComida;

import java.awt.*;

public class Vegetal extends Comida {
    public Vegetal(Point posicion) {
        super(posicion, "vegetal");
        this.tipo = TipoComida.VEGETAL;
    }
}