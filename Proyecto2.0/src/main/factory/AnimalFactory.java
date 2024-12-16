package factory;

import model.animals.Animal;
import model.animals.selva.*;
import model.animals.sabana.*;
import model.animals.acuatico.*;
import model.animals.polar.*;
import model.animals.desertico.*;

import java.awt.*;

public class AnimalFactory {
    public static Animal createAnimal(String tipo, Point posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posición no puede ser null");
        }

        try {
            return switch (tipo) {
                case "Tigre" -> new Tigre(posicion);
                case "Leon" -> new Leon(posicion);
                case "Mono" -> new Mono(posicion);
                case "Jaguar" -> new Jaguar(posicion);
                case "Jirafa" -> new Jirafa(posicion);
                case "Cebra" -> new Cebra(posicion);
                case "Elefante" -> new Elefante(posicion);
                case "Delfin" -> new Delfin(posicion);
                case "Tiburon" -> new Tiburon(posicion);
                case "TortugaMarina" -> new TortugaMarina(posicion);
                case "Pinguino" -> new Pinguino(posicion);
                case "OsoPolar" -> new OsoPolar(posicion);
                case "Foca" -> new Foca(posicion);
                case "Camello" -> new Camello(posicion);
                case "ZorroDesierto" -> new ZorroDesierto(posicion);
                case "AguilaCalva" -> new AguilaCalva(posicion);
                default -> throw new IllegalArgumentException("Tipo de animal no válido: " + tipo);
            };
        } catch (Exception e) {
            throw new RuntimeException("Error creando el animal: " + e.getMessage());
        }
    }
}