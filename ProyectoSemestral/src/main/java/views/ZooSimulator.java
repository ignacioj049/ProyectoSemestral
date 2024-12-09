package views;

import models.Zoo;
import controllers.ZooController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Pos;
import java.io.File;

public class ZooSimulator extends Application {
    private ZooController controller;

    @Override
    public void start(Stage primaryStage) {
        Zoo zoo = new Zoo();
        controller = new ZooController(zoo);
        primaryStage.setTitle("Simulador de Zoo");

        // Añadir música de fondo
        String musicFile = "src/resources/music/background.mp3"; // Asegúrate de que esta ruta sea correcta
        File musicFilePath = new File(musicFile);
        if (!musicFilePath.exists()) {
            System.err.println("El archivo de música no se encuentra en la ruta especificada: " + musicFilePath.getAbsolutePath());
            return;
        }
        System.out.println("Ruta del archivo de música: " + musicFilePath.getAbsolutePath());
        Media sound = new Media(musicFilePath.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        // Crear el título
        Text titulo = new Text("Simulador de Zoo");
        titulo.setFont(new Font("Arial", 30));
        titulo.setFill(Color.WHITE);

        // Crear el botón de inicio
        Button btnInicio = new Button("Inicio");
        btnInicio.setFont(new Font("Arial", 20));
        btnInicio.setTextFill(Color.WHITE);
        btnInicio.setStyle("-fx-background-color: #4CAF50;");
        btnInicio.setOnAction(event -> mostrarPantallaInicio(primaryStage));

        // Crear el botón de instrucciones
        Button btnInstrucciones = new Button("Instrucciones");
        btnInstrucciones.setFont(new Font("Arial", 20));
        btnInstrucciones.setTextFill(Color.WHITE);
        btnInstrucciones.setStyle("-fx-background-color: #4CAF50;");
        btnInstrucciones.setOnAction(event -> mostrarPantallaInstrucciones(primaryStage));

        // Crear el fondo
        String imagePath = "src/resources/images/background.jpg";
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.err.println("El archivo de imagen no se encuentra en la ruta especificada: " + imageFile.getAbsolutePath());
            return;
        }
        System.out.println("Ruta del archivo de imagen: " + imageFile.getAbsolutePath());
        ImageView fondo = new ImageView(new Image(imageFile.toURI().toString()));
        fondo.setFitWidth(996);
        fondo.setFitHeight(755);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titulo, btnInicio, btnInstrucciones);

        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, layout);

        Scene scene = new Scene(root, 996, 755);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarPantallaInicio(Stage stage) {
        PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
        pantallaInicio.mostrar();
    }

    private void mostrarPantallaInstrucciones(Stage stage) {
        PantallaInstrucciones pantallaInstrucciones = new PantallaInstrucciones(stage);
        pantallaInstrucciones.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





