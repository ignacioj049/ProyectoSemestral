import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class ZooSimulator extends Application {
    private ZooController controller;

    @Override
    public void start(Stage primaryStage) {
        Zoo zoo = new Zoo();
        controller = new ZooController(zoo);

        primaryStage.setTitle("Simulador de Zoo");

        // Añadir música de fondo
        String musicFile = "src/main/resources/music/background.mp3"; // Ruta al archivo de música
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        Button btnInicio = new Button("Inicio");
        btnInicio.setFont(new Font("Arial", 20));
        btnInicio.setTextFill(Color.WHITE);
        btnInicio.setStyle("-fx-background-color: #4CAF50;");
        btnInicio.setOnAction(event -> mostrarPantallaInicio(primaryStage));

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f0f0f0;");
        root.getChildren().add(btnInicio);
        Scene scene = new Scene(root, 996, 755); // Establecer el tamaño inicial de la ventana
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarPantallaInicio(Stage stage) {
        PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
        pantallaInicio.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}