package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Jaguar extends Animal {
    public Jaguar(String nombre) {
        super(nombre, TipoAnimal.JAGUAR);
    }


    protected double getPesoInicial() {
        return 100.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.CARNE;
    }
}