package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Camello extends Animal {
    public Camello(String nombre) {
        super(nombre, TipoAnimal.CAMELLO);
    }


    protected double getPesoInicial() {
        return 600.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.VEGETALES;
    }
}