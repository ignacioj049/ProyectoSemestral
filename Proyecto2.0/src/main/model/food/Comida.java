package model.food;

import model.TipoComida;
import util.Constants;
import util.ImageManager;

import java.awt.*;

public abstract class Comida {
    protected Point posicion;
    protected TipoComida tipo;
    protected boolean consumida;
    protected Image imagen;

    public Comida(Point posicion, String nombreImagen) {
        this.posicion = posicion;
        this.consumida = false;
        cargarImagen(nombreImagen);
    }

    private void cargarImagen(String nombreComida) {
        this.imagen = ImageManager.getInstance().getFoodImage(nombreComida);
    }

    public void dibujar(Graphics2D g) {
        if (!consumida && imagen != null) {
            g.drawImage(imagen, posicion.x, posicion.y, Constants.FOOD_SIZE, Constants.FOOD_SIZE, null);
        }
    }

    public TipoComida getTipo() {
        return tipo;
    }

    public void consumir() {
        this.consumida = true;
    }
    public boolean isConsumida() {
        return consumida;
    }
    public Point getPosicion() {
        return new Point(posicion);
    }

}