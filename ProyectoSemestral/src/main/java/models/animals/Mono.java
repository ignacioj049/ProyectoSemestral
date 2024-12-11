package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Mono extends Animal {
    public Mono(String nombre) {
        super(nombre, TipoAnimal.MONO);
    }


    protected double getPesoInicial() {
        return 45.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.FRUTAS;
    }
}