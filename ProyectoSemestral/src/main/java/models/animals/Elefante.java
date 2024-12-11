package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Elefante extends Animal {
    public Elefante(String nombre) {
        super(nombre, TipoAnimal.ELEFANTE);
    }


    protected double getPesoInicial() {
        return 4000.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.VEGETALES;
    }
}