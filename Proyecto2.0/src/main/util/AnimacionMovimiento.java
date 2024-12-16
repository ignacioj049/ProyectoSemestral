package util;

import model.animals.Animal;

import java.awt.*;


public class AnimacionMovimiento {
    private final Point inicio;
    private final Point fin;
    private final Animal animal;
    private float progreso;
    private static final float VELOCIDAD = 0.05f;

    public AnimacionMovimiento(Point inicio, Point fin, Animal animal) {
        this.inicio = new Point(inicio);
        this.fin = new Point(fin);
        this.animal = animal;
        this.progreso = 0;
    }

    public boolean actualizar() {
        progreso += VELOCIDAD;
        if (progreso >= 1) {
            animal.setPosicion(fin);
            return true;
        }

        Point posicionActual = new Point(
                (int) (inicio.x + (fin.x - inicio.x) * progreso),
                (int) (inicio.y + (fin.y - inicio.y) * progreso)
        );
        animal.setPosicion(posicionActual);
        return false;
    }

    public void dibujar(Graphics2D g) {
        animal.dibujar(g);
    }
}
