package model.habitats;

import model.animals.Animal;

import java.awt.*;
import java.util.Arrays;

public class Acuatico extends Habitat {
    public Acuatico(Point posicion) {
        super(posicion, "Acuatico");
        animalesPermitidos.addAll(Arrays.asList("Delfin", "Tiburon", "TortugaMarina"));
    }

    @Override
    public boolean puedeAlbergar(Animal animal) {
        return animalesPermitidos.contains(animal.getClass().getSimpleName());
    }
}