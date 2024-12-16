package model.animals.desertico;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Camello extends Animal {
    public Camello(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Desertico";
        this.tipoComidaPreferida = TipoComida.VEGETAL;
    }
}

