package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Pinguino extends Animal {
    public Pinguino(String nombre) {
        super(nombre, TipoAnimal.PINGUINO);
    }


    protected double getPesoInicial() {
        return 30.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.PESCADO;
    }
}