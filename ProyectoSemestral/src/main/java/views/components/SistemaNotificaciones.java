package views.components;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Alerta;
import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SistemaNotificaciones {
    private static final int DURACION_NOTIFICACION = 5000; // 5 segundos
    private static final int MAX_NOTIFICACIONES = 3;
    private static final int ALTURA_NOTIFICACION = 100;
    private static final int ANCHO_NOTIFICACION = 300;

    private Stage primaryStage;
    private Queue<Popup> notificacionesActivas;
    private AudioClip sonidoNotificacion;

    public SistemaNotificaciones(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.notificacionesActivas = new ConcurrentLinkedQueue<>();
        cargarSonido();
    }

    private void cargarSonido() {
        try {
            String rutaSonido = getClass().getResource("/sounds/notification.wav").toExternalForm();
            sonidoNotificacion = new AudioClip(rutaSonido);
            sonidoNotificacion.setVolume(0.5);
        } catch (Exception e) {
            System.err.println("No se pudo cargar el sonido de notificación: " + e.getMessage());
        }
    }

    public void mostrarNotificacion(String mensaje, int prioridad) {
        Platform.runLater(() -> {
            if (notificacionesActivas.size() >= MAX_NOTIFICACIONES) {
                Popup primeraNotificacion = notificacionesActivas.poll();
                if (primeraNotificacion != null) {
                    primeraNotificacion.hide();
                }
            }

            Popup popup = crearPopupNotificacion(mensaje, prioridad);
            notificacionesActivas.offer(popup);
            reposicionarNotificaciones();
            reproducirSonido();

            // Temporizador para ocultar la notificación
            PauseTransition delay = new PauseTransition(Duration.millis(DURACION_NOTIFICACION));
            delay.setOnFinished(event -> {
                popup.hide();
                notificacionesActivas.remove(popup);
                reposicionarNotificaciones();
            });
            delay.play();
        });
    }

    private Popup crearPopupNotificacion(String mensaje, int prioridad) {
        Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        VBox contenedor = new VBox(10);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle(getEstiloNotificacion(prioridad));
        contenedor.setPrefSize(ANCHO_NOTIFICACION, ALTURA_NOTIFICACION);

        Label textoNotificacion = new Label(mensaje);
        textoNotificacion.setWrapText(true);
        textoNotificacion.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        contenedor.getChildren().add(textoNotificacion);
        popup.getContent().add(contenedor);

        // Mostrar la notificación
        Scene scene = primaryStage.getScene();
        if (scene != null) {
            popup.show(primaryStage,
                    scene.getWindow().getX() + scene.getWindow().getWidth() - ANCHO_NOTIFICACION - 20,
                    scene.getWindow().getY() + 40);
        }

        return popup;
    }

    private void reposicionarNotificaciones() {
        Scene scene = primaryStage.getScene();
        if (scene == null) return;

        double baseY = scene.getWindow().getY() + 40;
        int index = 0;

        for (Popup popup : notificacionesActivas) {
            if (popup.isShowing()) {
                popup.setX(scene.getWindow().getX() + scene.getWindow().getWidth() - ANCHO_NOTIFICACION - 20);
                popup.setY(baseY + (index * (ALTURA_NOTIFICACION + 10)));
                index++;
            }
        }
    }

    private String getEstiloNotificacion(int prioridad) {
        String colorFondo = switch (prioridad) {
            case 1 -> "#c62828"; // Rojo para urgente
            case 2 -> "#f57c00"; // Naranja para importante
            case 3 -> "#2e7d32"; // Verde para info
            default -> "#424242";
        };

        return String.format("""
            -fx-background-color: %s;
            -fx-background-radius: 5;
            -fx-padding: 10;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 0);
            """, colorFondo);
    }

    private void reproducirSonido() {
        if (sonidoNotificacion != null) {
            sonidoNotificacion.play();
        }
    }

    public void actualizarPosicion() {
        reposicionarNotificaciones();
    }

    public void limpiarNotificaciones() {
        for (Popup popup : notificacionesActivas) {
            popup.hide();
        }
        notificacionesActivas.clear();
    }
}