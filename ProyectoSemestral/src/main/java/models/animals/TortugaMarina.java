package models.animals;

import enums.TipoAnimal;
import enums.TipoComida;

public class TortugaMarina extends Animal {
    public TortugaMarina(String nombre) {
        super(nombre, TipoAnimal.TORTUGA_MARINA);
    }


    protected double getPesoInicial() {
        return 150.0;
    }


    public TipoComida getTipoComidaPreferida() {
        return TipoComida.VEGETALES;
    }
}