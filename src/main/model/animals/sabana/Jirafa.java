package model.animals.sabana;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Jirafa extends Animal {
    public Jirafa(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Sabana";
        this.tipoComidaPreferida = TipoComida.VEGETAL;
    }
}