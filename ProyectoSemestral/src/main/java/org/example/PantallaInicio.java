import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class PantallaInicio {
    private Stage stage;
    private ZooController controller;

    public PantallaInicio(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
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
        layout.setStyle("-fx-background-color: #f0f0f0;");
        layout.getChildren().addAll(btnCrearHabitat, btnGestionAnimales, btnGestionComida, btnEstadoAnimales);

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
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