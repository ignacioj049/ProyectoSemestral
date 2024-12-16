package model.animals.selva;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Mono extends Animal {
    public Mono(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Selva";
        this.tipoComidaPreferida = TipoComida.FRUTA;
    }
}
