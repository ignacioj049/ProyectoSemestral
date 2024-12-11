package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Tigre extends Animal {
    public Tigre(String nombre) {
        super(nombre, TipoAnimal.TIGRE);
    }


    protected double getPesoInicial() {
        return 140.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.CARNE;
    }
}