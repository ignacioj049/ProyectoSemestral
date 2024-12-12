package models.food;

import enums.TipoComida;

public class Comida {
    private TipoComida tipo;
    private double cantidad;

    public Comida(TipoComida tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public TipoComida getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
