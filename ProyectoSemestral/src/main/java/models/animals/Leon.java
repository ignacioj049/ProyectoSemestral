package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Leon extends Animal {
    public Leon(String nombre) {
        super(nombre, TipoAnimal.LEON);
    }


    protected double getPesoInicial() {
        return 150.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.CARNE;
    }
}