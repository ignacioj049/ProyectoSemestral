package model.animals.acuatico;

import model.animals.Animal;
import model.TipoComida;

import java.awt.*;

public class TortugaMarina extends Animal {
    public TortugaMarina(Point posicion) {
        super(posicion);
    }

    @Override
    protected void inicializarAnimal() {
        this.tipoHabitat = "Acuatico";
        this.tipoComidaPreferida = TipoComida.VEGETAL;
    }
}