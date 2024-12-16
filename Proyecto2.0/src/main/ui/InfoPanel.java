package ui;

import model.Zoo;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel estadisticasLabel;
    private final Zoo zoo;

    public InfoPanel() {
        zoo = Zoo.getInstance();
        setPreferredSize(new Dimension(200, 100));
        setBorder(BorderFactory.createTitledBorder("Información"));

        setLayout(new BorderLayout());
        estadisticasLabel = new JLabel();
        actualizarEstadisticas();
        add(estadisticasLabel, BorderLayout.CENTER);
    }

    public void actualizarEstadisticas() {
        StringBuilder stats = new StringBuilder("<html>");
        stats.append("Total Hábitats: ").append(zoo.getHabitats().size()).append("<br>");
        stats.append("Total Animales: ").append(zoo.getTotalAnimales()).append("<br>");
        stats.append("Total Comida: ").append(zoo.getTotalComida()).append("<br>");
        stats.append("</html>");

        estadisticasLabel.setText(stats.toString());
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        estadisticasLabel = new JLabel();
        actualizarEstadisticas();
        add(estadisticasLabel, BorderLayout.CENTER);
    }
}