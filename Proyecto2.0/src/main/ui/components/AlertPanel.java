package ui.components;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class AlertPanel extends JPanel {
    private final JTextArea alertArea;
    private final Queue<String> alertas;
    private static final int MAX_ALERTAS = 5;
    private static final int PANEL_WIDTH = 900;
    public AlertPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Alertas"));
        setPreferredSize(new Dimension(PANEL_WIDTH, 100));

        alertas = new LinkedList<>();
        alertArea = new JTextArea(4, 40);
        alertArea.setEditable(false);
        alertArea.setBackground(new Color(255, 255, 200));

        JScrollPane scrollPane = new JScrollPane(alertArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarAlerta(String mensaje) {
        alertas.offer(mensaje);
        if (alertas.size() > MAX_ALERTAS) {
            alertas.poll();
        }
        actualizarTexto();
    }

    private void actualizarTexto() {
        StringBuilder texto = new StringBuilder();
        for (String alerta : alertas) {
            texto.append(alerta).append("\n");
        }
        alertArea.setText(texto.toString());
    }
}