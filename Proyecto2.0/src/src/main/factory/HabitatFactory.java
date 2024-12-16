package factory;

import model.habitats.*;

import java.awt.*;

public class HabitatFactory {
    public static Habitat createHabitat(String tipo, Point posicion) {
        return switch (tipo) {
            case "Selva" -> new Selva(posicion);
            case "Sabana" -> new Sabana(posicion);
            case "Acuatico" -> new Acuatico(posicion);
            case "Polar" -> new Polar(posicion);
            case "Desertico" -> new Desertico(posicion);
            default -> throw new IllegalArgumentException("Tipo de hábitat no válido: " + tipo);
        };
    }
}