package models.food;

import enums.TipoComida;

public abstract class Comida {
    private String nombre;
    private TipoComida tipo;

    public Comida(String nombre, TipoComida tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoComida getTipo() {
        return tipo;
    }
}
