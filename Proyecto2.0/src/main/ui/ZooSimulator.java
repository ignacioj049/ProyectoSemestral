package ui;

import factory.AnimalFactory;
import factory.ComidaFactory;
import factory.HabitatFactory;
import model.Zoo;
import model.animals.Animal;
import model.food.Comida;
import model.habitats.Habitat;
import ui.components.AlertPanel;
import ui.components.MenuPanel;
import ui.components.ZooPanel;
import util.Config;
import observer.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZooSimulator extends JFrame implements Observer {
    private ZooPanel zooPanel;
    private MenuPanel menuPanel;
    private InfoPanel infoPanel;
    private AlertPanel alertPanel;
    private Timer timer;

    public ZooSimulator() {
        setTitle("Simulador de Zoo");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
        Zoo.getInstance().addObserver(this);
        iniciarTimer();
    }

    private void inicializarComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        menuPanel = new MenuPanel();
        zooPanel = new ZooPanel();
        infoPanel = new InfoPanel();
        alertPanel = new AlertPanel();
        Zoo.getInstance().setAlertPanel(alertPanel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(menuPanel, BorderLayout.CENTER);
        rightPanel.add(infoPanel, BorderLayout.SOUTH);
        add(alertPanel, BorderLayout.SOUTH);
        mainPanel.add(zooPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        GestorEventos gestorEventos = new GestorEventos(zooPanel);
        menuPanel.configurarDragListeners(gestorEventos.getDragListener());

        setContentPane(mainPanel);
    }

    private void iniciarTimer() {
        timer = new Timer(300, e -> {
            Zoo.getInstance().actualizarEstado();
            repaint();
        });
        timer.start();
    }


    public void mostrarAlertaComida(Habitat habitat) {
        JOptionPane.showMessageDialog(this,
                "¡Falta comida en el hábitat " + habitat.getTipo() + "!",
                "Alerta de Comida",
                JOptionPane.WARNING_MESSAGE);
    }


    public void update() {
        repaint();
        infoPanel.actualizarEstadisticas();
    }
}
