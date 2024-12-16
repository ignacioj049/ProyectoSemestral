package model.animals.selva;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Jaguar extends Animal {
    public Jaguar(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Selva";
        this.tipoComidaPreferida = TipoComida.CARNE;
    }
}