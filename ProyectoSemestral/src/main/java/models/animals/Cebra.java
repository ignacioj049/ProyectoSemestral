package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class Cebra extends Animal {
    public Cebra(String nombre) {
        super(nombre, TipoAnimal.CEBRA);
    }


    protected double getPesoInicial() {
        return 350.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.VEGETALES;
    }
}