package model.animals.desertico;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class ZorroDesierto extends Animal {
    public ZorroDesierto(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Desertico";
        this.tipoComidaPreferida = TipoComida.CARNE;
    }
}