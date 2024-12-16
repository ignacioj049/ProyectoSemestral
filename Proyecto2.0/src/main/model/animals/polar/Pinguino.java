package model.animals.polar;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Pinguino extends Animal {
    public Pinguino(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Polar";
        this.tipoComidaPreferida = TipoComida.PESCADO;
    }
}