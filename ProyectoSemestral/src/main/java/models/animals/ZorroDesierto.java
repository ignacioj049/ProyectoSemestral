package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class ZorroDesierto extends Animal {
    public ZorroDesierto(String nombre) {
        super(nombre, TipoAnimal.ZORRO_DESIERTO);
    }


    protected double getPesoInicial() {
        return 25.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.CARNE;
    }
}