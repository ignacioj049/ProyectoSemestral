package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class AguilaCalva extends Animal {
    public AguilaCalva(String nombre) {
        super(nombre, TipoAnimal.AGUILA_CALVA);
    }

    protected double getPesoInicial() {
        return 6.0;
    }

    public TipoComida getTipoComidaPreferida() {
        return TipoComida.CARNE;
    }
}