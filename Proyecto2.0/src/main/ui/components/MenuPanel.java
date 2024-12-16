package ui.components;

import ui.DraggableComponent;
import model.TipoElemento;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class MenuPanel extends JPanel {
    private final JPanel habitatsPanel;
    private final JPanel animalesPanel;
    private final JPanel comidaPanel;

    public MenuPanel() {
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        habitatsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        animalesPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        comidaPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        inicializarPaneles();
    }

    private void inicializarPaneles() {
        // Configurar paneles
        habitatsPanel.setBorder(BorderFactory.createTitledBorder("Hábitats"));
        animalesPanel.setBorder(BorderFactory.createTitledBorder("Animales"));
        comidaPanel.setBorder(BorderFactory.createTitledBorder("Comida"));

        // Agregar elementos
        List<String> habitats = Arrays.asList("Selva", "Sabana", "Acuatico", "Polar", "Desertico");
        habitats.forEach(h -> habitatsPanel.add(new DraggableComponent(h, TipoElemento.HABITAT)));

        List<String> animales = Arrays.asList(
                "Tigre", "Mono", "Jaguar", "Leon", "Jirafa", "Cebra", "Elefante",
                "Delfin", "Tiburon", "TortugaMarina", "Pinguino", "OsoPolar", "Foca",
                "Camello", "ZorroDesierto", "AguilaCalva"
        );
        animales.forEach(a -> animalesPanel.add(new DraggableComponent(a, TipoElemento.ANIMAL)));

        List<String> comidas = Arrays.asList("Carne", "Fruta", "Vegetal", "Pescado");
        comidas.forEach(c -> comidaPanel.add(new DraggableComponent(c, TipoElemento.COMIDA)));

        // Agregar paneles al MenuPanel
        add(habitatsPanel);
        add(new JScrollPane(animalesPanel));
        add(comidaPanel);
    }

    public void configurarDragListeners(MouseAdapter dragListener) {
        Arrays.asList(habitatsPanel, animalesPanel, comidaPanel)
                .forEach(panel -> {
                    for (Component comp : panel.getComponents()) {
                        if (comp instanceof DraggableComponent) {
                            comp.addMouseListener(dragListener);
                            comp.addMouseMotionListener(dragListener);
                        }
                    }
                });
    }
}