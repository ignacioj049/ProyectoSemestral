package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Foca extends Animal {
    public Foca(String nombre) {
        super(nombre, TipoAnimal.FOCA);
    }


    protected double getPesoInicial() {
        return 300.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.PESCADO;
    }
}