package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class OsoPolar extends Animal {
    public OsoPolar(String nombre) {
        super(nombre, TipoAnimal.OSO_POLAR);
    }


    protected double getPesoInicial() {
        return 450.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.PESCADO;
    }
}