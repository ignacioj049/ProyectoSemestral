package model.habitats;

import model.animals.Animal;

import java.awt.*;
import java.util.Arrays;

public class Desertico extends Habitat {
    public Desertico(Point posicion) {
        super(posicion, "Desertico");
        animalesPermitidos.addAll(Arrays.asList("Camello", "ZorroDesierto", "AguilaCalva"));
    }

    @Override
    public boolean puedeAlbergar(Animal animal) {
        return animalesPermitidos.contains(animal.getClass().getSimpleName());
    }
}
