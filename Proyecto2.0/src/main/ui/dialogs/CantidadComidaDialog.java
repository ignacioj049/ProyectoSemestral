package ui.dialogs;

import javax.swing.*;
import java.awt.*;

public class CantidadComidaDialog extends JDialog {
    private int cantidad = 0;
    private boolean confirmado = false;

    public CantidadComidaDialog(JFrame parent) {
        super(parent, "Cantidad de Comida", true);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        JButton confirmar = new JButton("Confirmar");
        JButton cancelar = new JButton("Cancelar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Cantidad de comida:"), gbc);

        gbc.gridx = 1;
        panel.add(spinner, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(confirmar);
        buttonPanel.add(cancelar);

        confirmar.addActionListener(e -> {
            cantidad = (Integer) spinner.getValue();
            confirmado = true;
            dispose();
        });

        cancelar.addActionListener(e -> dispose());

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}