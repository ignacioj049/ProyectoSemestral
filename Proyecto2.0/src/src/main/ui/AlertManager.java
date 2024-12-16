package ui;

import javax.swing.*;
import java.awt.*;

public class AlertManager {
    public static void mostrarError(Component parentComponent, String mensaje) {
        JOptionPane.showMessageDialog(
                parentComponent,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void mostrarAlerta(Component parentComponent, String mensaje) {
        JOptionPane.showMessageDialog(
                parentComponent,
                mensaje,
                "Alerta",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void mostrarInfo(Component parentComponent, String mensaje) {
        JOptionPane.showMessageDialog(
                parentComponent,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
