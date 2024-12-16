import ui.PantallaInicio;
import ui.ZooSimulator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            PantallaInicio pantallaInicio = new PantallaInicio();
            pantallaInicio.setVisible(true);
        });
    }
}