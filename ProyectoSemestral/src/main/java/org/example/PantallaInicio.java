import javafx.animation.ScaleTransition;
import javafx.geometry.Pos; // Asegúrate de incluir esta línea
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text; // Importar la clase Text
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

public class PantallaInicio {
    private Stage stage;
    private ZooController controller;

    public PantallaInicio(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        String videoFile = "src/main/resources/videos/background.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(false); // No mantener la relación de aspecto
        mediaView.fitWidthProperty().bind(stage.widthProperty());
        mediaView.fitHeightProperty().bind(stage.heightProperty());

        Text titulo = new Text("Bienvenidos al Zoo");
        titulo.setFont(new Font("Arial", 24));
        titulo.setFill(Color.WHITE);

        Button btnCrearHabitat = new Button("Crear Hábitat");
        btnCrearHabitat.setFont(new Font("Arial", 16));
        btnCrearHabitat.setTextFill(Color.WHITE);
        btnCrearHabitat.setStyle("-fx-background-color: #4CAF50;");
        btnCrearHabitat.setOnAction(event -> {
            mostrarPantallaCreacionHabitat();
            animarBoton(btnCrearHabitat);
        });

        Button btnGestionAnimales = new Button("Gestionar Animales");
        btnGestionAnimales.setFont(new Font("Arial", 16));
        btnGestionAnimales.setTextFill(Color.WHITE);
        btnGestionAnimales.setStyle("-fx-background-color: #4CAF50;");
        btnGestionAnimales.setOnAction(event -> {
            mostrarPantallaGestionAnimales();
            animarBoton(btnGestionAnimales);
        });

        Button btnGestionComida = new Button("Gestionar Comida");
        btnGestionComida.setFont(new Font("Arial", 16));
        btnGestionComida.setTextFill(Color.WHITE);
        btnGestionComida.setStyle("-fx-background-color: #4CAF50;");
        btnGestionComida.setOnAction(event -> {
            mostrarPantallaGestionComida();
            animarBoton(btnGestionComida);
        });

        Button btnEstadoAnimales = new Button("Estado de Animales");
        btnEstadoAnimales.setFont(new Font("Arial", 16));
        btnEstadoAnimales.setTextFill(Color.WHITE);
        btnEstadoAnimales.setStyle("-fx-background-color: #4CAF50;");
        btnEstadoAnimales.setOnAction(event -> {
            mostrarPantallaEstadoAnimales();
            animarBoton(btnEstadoAnimales);
        });

        ImageView imgCrearHabitat = new ImageView(new Image("file:src/main/resources/images/habitat.png"));
        imgCrearHabitat.setFitHeight(50);
        imgCrearHabitat.setFitWidth(50);
        btnCrearHabitat.setGraphic(imgCrearHabitat);

        ImageView imgGestionAnimales = new ImageView(new Image("file:src/main/resources/images/animals.png"));
        imgGestionAnimales.setFitHeight(50);
        imgGestionAnimales.setFitWidth(50);
        btnGestionAnimales.setGraphic(imgGestionAnimales);

        ImageView imgGestionComida = new ImageView(new Image("file:src/main/resources/images/food.png"));
        imgGestionComida.setFitHeight(50);
        imgGestionComida.setFitWidth(50);
        btnGestionComida.setGraphic(imgGestionComida);

        ImageView imgEstadoAnimales = new ImageView(new Image("file:src/main/resources/images/status.png"));
        imgEstadoAnimales.setFitHeight(50);
        imgEstadoAnimales.setFitWidth(50);
        btnEstadoAnimales.setGraphic(imgEstadoAnimales);

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: transparent;");
        layout.getChildren().addAll(titulo, btnCrearHabitat, btnGestionAnimales, btnGestionComida, btnEstadoAnimales);
        layout.setAlignment(Pos.CENTER); // Centrar los botones y el texto

        StackPane root = new StackPane();
        root.getChildren().addAll(mediaView, layout);

        Scene scene = new Scene(root, 996, 755); // Establecer el tamaño inicial de la ventana
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarPantallaCreacionHabitat() {
        PantallaCreacionHabitat pantallaCreacionHabitat = new PantallaCreacionHabitat(stage, controller);
        pantallaCreacionHabitat.mostrar();
    }

    private void mostrarPantallaGestionAnimales() {
        PantallaGestionAnimales pantallaGestionAnimales = new PantallaGestionAnimales(stage, controller);
        pantallaGestionAnimales.mostrar();
    }

    private void mostrarPantallaGestionComida() {
        PantallaGestionComida pantallaGestionComida = new PantallaGestionComida(stage, controller);
        pantallaGestionComida.mostrar();
    }

    private void mostrarPantallaEstadoAnimales() {
        PantallaEstadoAnimales pantallaEstadoAnimales = new PantallaEstadoAnimales(stage, controller);
        pantallaEstadoAnimales.mostrar();
    }

    private void animarBoton(Button button) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
        st.setByX(1.1);
        st.setByY(1.1);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }
}