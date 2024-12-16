package model.habitats;

import model.animals.Animal;

import java.awt.*;
import java.util.Arrays;

public class Polar extends Habitat {
    public Polar(Point posicion) {
        super(posicion, "Polar");
        animalesPermitidos.addAll(Arrays.asList("Pinguino", "OsoPolar", "Foca"));
    }

    @Override
    public boolean puedeAlbergar(Animal animal) {
        return animalesPermitidos.contains(animal.getClass().getSimpleName());
    }
}