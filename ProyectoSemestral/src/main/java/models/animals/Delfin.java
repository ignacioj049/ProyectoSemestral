package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Delfin extends Animal {
    public Delfin(String nombre) {
        super(nombre, TipoAnimal.DELFIN);
    }


    protected double getPesoInicial() {
        return 250.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.PESCADO;
    }
}