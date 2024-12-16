package model.habitats;

import model.animals.Animal;

import java.awt.*;
import java.util.Arrays;

public class Sabana extends Habitat {
    public Sabana(Point posicion) {
        super(posicion, "Sabana");
        animalesPermitidos.addAll(Arrays.asList("Leon", "Jirafa", "Cebra", "Elefante"));
    }

    @Override
    public boolean puedeAlbergar(Animal animal) {
        return animalesPermitidos.contains(animal.getClass().getSimpleName());
    }
}