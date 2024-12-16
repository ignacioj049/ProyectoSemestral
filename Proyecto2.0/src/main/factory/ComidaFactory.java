package factory;

import model.food.*;

import java.awt.*;

public class ComidaFactory {
    public static Comida createComida(String tipo, Point posicion) {
        return switch (tipo) {
            case "Carne" -> new Carne(posicion);
            case "Fruta" -> new Fruta(posicion);
            case "Vegetal" -> new Vegetal(posicion);
            case "Pescado" -> new Pescado(posicion);
            default -> throw new IllegalArgumentException("Tipo de comida no válido: " + tipo);
        };
    }
}