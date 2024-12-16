package model.animals.acuatico;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class Tiburon extends Animal {
    public Tiburon(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Acuatico";
        this.tipoComidaPreferida = TipoComida.PESCADO;
    }
}
