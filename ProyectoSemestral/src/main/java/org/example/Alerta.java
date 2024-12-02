import javafx.animation.FadeTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class Alerta {
    private String mensaje;
    private int prioridad;

    public Alerta(String mensaje, int prioridad) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    public void mostrarAlerta() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        // Animación de desvanecimiento para la alerta
        FadeTransition ft = new FadeTransition(Duration.millis(500), alert.getDialogPane());
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        alert.showAndWait();
    }

    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}