package ui;

import model.Zoo;
import model.animals.Animal;
import model.habitats.Habitat;
import model.food.Comida;
import factory.*;
import ui.components.*;
import ui.dialogs.CantidadComidaDialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GestorEventos {
    private final ZooPanel zooPanel;
    private final Zoo zoo;
    private Component componenteArrastrado;
    private Point puntoInicial;

    public GestorEventos(ZooPanel zooPanel) {
        this.zooPanel = zooPanel;
        this.zoo = Zoo.getInstance();
    }


    public MouseAdapter getDragListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() instanceof DraggableComponent) {
                    componenteArrastrado = (Component) e.getSource();
                    puntoInicial = e.getPoint();
                    ((DraggableComponent) componenteArrastrado).setDragging(true);
                    zooPanel.setShowGrid(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (componenteArrastrado != null) {
                    Point screenPoint = e.getLocationOnScreen();
                    Point panelPoint = new Point(screenPoint);
                    SwingUtilities.convertPointFromScreen(panelPoint, zooPanel);

                    procesarSoltado(panelPoint);

                    ((DraggableComponent) componenteArrastrado).setDragging(false);
                    componenteArrastrado = null;
                    zooPanel.setShowGrid(false);
                }
            }
        };
    }
    private void procesarSoltado(Point punto) {
        if (!(componenteArrastrado instanceof DraggableComponent)) return;

        DraggableComponent comp = (DraggableComponent) componenteArrastrado;
        switch (comp.getTipo()) {
            case HABITAT -> colocarHabitat(comp.getNombre(), punto);
            case ANIMAL -> colocarAnimal(comp.getNombre(), punto);
            case COMIDA -> colocarComida(comp.getNombre(), punto);
        }
    }

    private void colocarHabitat(String nombre, Point punto) {
        Point puntoAjustado = zooPanel.snapToGrid(punto);

        if (!zooPanel.esUbicacionValida(puntoAjustado)) {
            mostrarError("Ubicación no válida para el hábitat");
            return;
        }

        if (zoo.hayHabitatEnPosicion(puntoAjustado)) {
            mostrarError("Ya existe un hábitat en esta posición");
            return;
        }

        if (!zoo.puedeAgregarHabitat()) {
            mostrarError("No se pueden agregar más hábitats. Máximo permitido: 6");
            return;
        }

        try {
            System.out.println("Intentando colocar hábitat en: " + puntoAjustado);
            Habitat habitat = HabitatFactory.createHabitat(nombre, puntoAjustado);
            if (!zoo.addHabitat(habitat)) {
                mostrarError("No se pudo agregar el hábitat");
            }
        } catch (Exception e) {
            mostrarError("Error al crear el hábitat: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void colocarAnimal(String nombre, Point punto) {
        Habitat habitat = zoo.getHabitatEnPunto(punto);
        if (habitat == null) {
            mostrarError("Debes colocar el animal en un hábitat");
            return;
        }

        try {
            System.out.println("Intentando colocar animal en: " + punto.x + ", " + punto.y);
            Animal animal = AnimalFactory.createAnimal(nombre, new Point(punto));
            if (animal == null) {
                mostrarError("Error creando el animal");
                return;
            }

            if (!zoo.addAnimal(animal, habitat)) {
                mostrarError("Este animal no puede vivir en este hábitat");
            }
        } catch (Exception e) {
            mostrarError("Error al crear el animal: " + e.getMessage());
        }
    }
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(zooPanel,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void colocarComida(String nombre, Point punto) {
        Habitat habitat = zoo.getHabitatEnPunto(punto);
        if (habitat == null) {
            mostrarError("Debes colocar la comida en un hábitat");
            return;
        }

        CantidadComidaDialog dialog = new CantidadComidaDialog((JFrame) SwingUtilities.getWindowAncestor(zooPanel));
        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            int cantidad = dialog.getCantidad();
            for (int i = 0; i < cantidad; i++) {
                try {
                    Point posicionAleatoria = habitat.getPosicionAleatoria();
                    Comida comida = ComidaFactory.createComida(nombre, posicionAleatoria);
                    zoo.addComida(comida, habitat);
                } catch (Exception e) {
                    mostrarError("Error al crear la comida: " + e.getMessage());
                }
            }
            zoo.distribuirComida(habitat);
        }
    }
}