import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PantallaCreacionHabitat {
    private Stage stage;
    private ZooController controller;

    public PantallaCreacionHabitat(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        TextField nombreHabitat = new TextField();
        nombreHabitat.setPromptText("Nombre del Hábitat");

        ComboBox<String> tipoHabitat = new ComboBox<>();
        tipoHabitat.getItems().addAll(
                "Selva Tropical", "Sabana", "Bosque Templado", "Desierto", "Taiga", "Tundra",
                "Arrecife de Coral", "Río", "Pantano", "Océano Abierto", "Glaciares y Hielo Ártico",
                "Montaña Rocosa", "Aviario", "Terrario", "Acuario", "Zona Nocturna", "Zona de Recuperación y Conservación"
        );
        tipoHabitat.setPromptText("Tipo de Hábitat");

        TextField capacidadHabitat = new TextField();
        capacidadHabitat.setPromptText("Capacidad del Hábitat");

        Button btnCrear = new Button("Crear");
        btnCrear.setFont(new Font("Arial", 16));
        btnCrear.setTextFill(Color.WHITE);
        btnCrear.setStyle("-fx-background-color: #4CAF50;");
        btnCrear.setOnAction(event -> {
            String nombre = nombreHabitat.getText();
            String tipo = tipoHabitat.getValue();
            try {
                int capacidad = Integer.parseInt(capacidadHabitat.getText());
                int capacidadMaxima = getCapacidadMaxima(tipo);
                if (nombre != null && !nombre.isEmpty() && tipo != null && capacidad > 0) {
                    if (capacidad <= capacidadMaxima) {
                        controller.crearHabitat(nombre, tipo, capacidad);
                        mostrarAlerta("Hábitat creado: " + nombre + " (" + tipo + ") con capacidad de " + capacidad + " animales");
                    } else {
                        mostrarAlerta("Capacidad excedida. La capacidad máxima para " + tipo + " es " + capacidadMaxima + " animales.");
                    }
                } else {
                    mostrarAlerta("Por favor, completa todos los campos correctamente.");
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Capacidad debe ser un número entero válido.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> {
            PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
            pantallaInicio.mostrar();
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #f0f0f0;");
        layout.getChildren().addAll(nombreHabitat, tipoHabitat, capacidadHabitat, btnCrear, btnVolver);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    private int getCapacidadMaxima(String tipo) {
        switch (tipo) {
            case "Selva Tropical":
                return 20;
            case "Sabana":
                return 15;
            case "Bosque Templado":
                return 12;
            case "Desierto":
                return 8;
            case "Taiga":
                return 10;
            case "Tundra":
                return 6;
            case "Arrecife de Coral":
                return 25;
            case "Río":
                return 10;
            case "Pantano":
                return 12;
            case "Océano Abierto":
                return 15;
            case "Glaciares y Hielo Ártico":
                return 5;
            case "Montaña Rocosa":
                return 8;
            case "Aviario":
                return 20;
            case "Terrario":
                return 5;
            case "Acuario":
                return 15;
            case "Zona Nocturna":
                return 10;
            case "Zona de Recuperación y Conservación":
                return 5;
            default:
                return 0;
        }
    }

    private void mostrarAlerta(String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }
}