package model.animals.sabana;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Leon extends Animal {
    public Leon(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Sabana";
        this.tipoComidaPreferida = TipoComida.CARNE;
    }
}

