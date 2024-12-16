package model.food;

import model.TipoComida;

import java.awt.*;

public class Fruta extends Comida {
    public Fruta(Point posicion) {
        super(posicion, "fruta");
        this.tipo = TipoComida.FRUTA;
    }
}