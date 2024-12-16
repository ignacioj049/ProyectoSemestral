package ui;

import model.TipoElemento;
import util.Constants;
import util.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DraggableComponent extends JButton {
    private boolean isDragging;
    private final String nombre;
    private final TipoElemento tipo;

    public DraggableComponent(String nombre, TipoElemento tipo) {
        super(nombre);
        this.nombre = nombre;
        this.tipo = tipo;
        this.isDragging = false;
        configurarComponente();
    }

    private void configurarComponente() {
        setPreferredSize(new Dimension(150, 40));
        setBackground(getTipoColor());
        setBorderPainted(true);
        setFocusPainted(false);
        cargarIcono();
    }

    private void cargarIcono() {
        Image img = null;
        ImageManager imageManager = ImageManager.getInstance();

        switch (tipo) {
            case HABITAT:
                img = imageManager.getHabitatImage(nombre);
                break;
            case ANIMAL:
                String habitat = getHabitatAnimal();
                img = imageManager.getAnimalImage(habitat, nombre);
                break;
            case COMIDA:
                img = imageManager.getFoodImage(nombre);
                break;
        }

        if (img != null) {
            Image scaled = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaled));
        }
    }

    private Color getTipoColor() {
        return switch (tipo) {
            case HABITAT -> new Color(200, 230, 200);
            case ANIMAL -> new Color(230, 200, 200);
            case COMIDA -> new Color(200, 200, 230);
        };
    }

    private String getHabitatAnimal() {
        return switch (nombre) {
            case "Tigre", "Mono", "Jaguar" -> "selva";
            case "Leon", "Jirafa", "Cebra", "Elefante" -> "sabana";
            case "Delfin", "Tiburon", "TortugaMarina" -> "acuatico";
            case "Pinguino", "OsoPolar", "Foca" -> "polar";
            case "Camello", "ZorroDesierto", "AguilaCalva" -> "desertico";
            default -> "";
        };
    }

    public void setDragging(boolean dragging) {
        this.isDragging = dragging;
        setBackground(isDragging ? getBackground().brighter() : getTipoColor());
    }

    public String getNombre() {
        return nombre;
    }

    public TipoElemento getTipo() {
        return tipo;
    }
}

