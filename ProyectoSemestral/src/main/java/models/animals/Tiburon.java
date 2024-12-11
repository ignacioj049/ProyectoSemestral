package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Tiburon extends Animal {
    public Tiburon(String nombre) {
        super(nombre, TipoAnimal.TIBURON);
    }


    protected double getPesoInicial() {
        return 900.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.PESCADO;
    }
}