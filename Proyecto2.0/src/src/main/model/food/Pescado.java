package model.food;

import model.TipoComida;

import java.awt.*;

public class Pescado extends Comida {
    public Pescado(Point posicion) {
        super(posicion, "pescado");
        this.tipo = TipoComida.PESCADO;
    }
}