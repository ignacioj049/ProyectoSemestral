package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Jirafa extends Animal {
    public Jirafa(String nombre) {
        super(nombre, TipoAnimal.JIRAFA);
    }


    protected double getPesoInicial() {
        return 1200.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.VEGETALES;
    }
}