package views;

import controllers.ZooController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import models.habitats.Habitat;
import utils.ResourceManager;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class ZooSimulator extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear el controlador
            ZooController controller = new ZooController();

            // Intentar cargar el video de fondo
            String videoPath = "videos/background.mp4";
            Media media = null;
            String mediaPath = ResourceManager.getResourcePath(videoPath);

            if (mediaPath != null) {
                media = new Media(mediaPath);
                System.out.println("Video cargado exitosamente: " + mediaPath);
            } else {
                System.err.println("No se pudo cargar el video de fondo. Continuando sin video...");
            }

            // Crear la pantalla principal
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(controller);

            // Crear y configurar la vista del zoo
            PantallaVerZoo pantallaZoo = new PantallaVerZoo();

            // Obtener los hábitats del controlador y agregarlos a la vista
            Map<String, Habitat> habitats = controller.getHabitats();
            int fila = 0;
            int columna = 0;
            int maxColumnasPerFila = 4; // Puedes ajustar este valor según necesites

            for (Habitat habitat : habitats.values()) {
                pantallaZoo.agregarHabitat(habitat, fila, columna);
                columna++;
                if (columna >= maxColumnasPerFila) {
                    columna = 0;
                    fila++;
                }
            }

            // Agregar la vista del zoo a la pantalla principal
            // Asumiendo que PantallaPrincipal tiene un método para esto
            pantallaPrincipal.setContenidoCentral(pantallaZoo);

            // Configurar el video de fondo si se cargó correctamente
            if (media != null) {
                pantallaPrincipal.setBackgroundVideo(media);
            }

            // Configurar y mostrar la ventana principal
            Scene scene = new Scene(pantallaPrincipal, 1280, 720);

            // Configurar la ventana principal
            primaryStage.setTitle("Zoo Simulator");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(1280);
            primaryStage.setMinHeight(720);

            // Centrar la ventana en la pantalla
            primaryStage.centerOnScreen();

            // Mostrar la ventana
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}