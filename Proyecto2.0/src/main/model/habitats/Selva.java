package model.habitats;

import model.animals.Animal;

import java.awt.*;
import java.util.Arrays;

public class Selva extends Habitat {
    public Selva(Point posicion) {
        super(posicion, "Selva");
        animalesPermitidos.addAll(Arrays.asList("Tigre", "Mono", "Jaguar"));
    }

    @Override
    public boolean puedeAlbergar(Animal animal) {
        return animalesPermitidos.contains(animal.getClass().getSimpleName());
    }
}