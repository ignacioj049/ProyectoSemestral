import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        ComboBox<String> tipoHabitat = new ComboBox<>();
        tipoHabitat.getItems().addAll(
                "Selva Tropical", "Sabana", "Bosque Templado", "Desierto", "Taiga", "Tundra",
                "Arrecife de Coral", "Río", "Pantano", "Océano Abierto", "Glaciares y Hielo Ártico",
                "Montaña Rocosa", "Aviario", "Terrario", "Zona Nocturna"
        );
        tipoHabitat.setPromptText("Tipo de Hábitat");

        Button btnCrear = new Button("Crear");
        btnCrear.setFont(new Font("Arial", 16));
        btnCrear.setTextFill(Color.WHITE);
        btnCrear.setStyle("-fx-background-color: #4CAF50;");
        btnCrear.setOnAction(event -> {
            String tipo = tipoHabitat.getValue();
            if (tipo != null) {
                controller.crearHabitat(tipo, tipo);
                System.out.println("Hábitat creado: " + tipo);

                // Animación de escala
                ScaleTransition st = new ScaleTransition(Duration.millis(200), btnCrear);
                st.setByX(1.2);
                st.setByY(1.2);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
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
        layout.getChildren().addAll(tipoHabitat, btnCrear, btnVolver);
        layout.setTranslateX(20); // Alinear al lado izquierdo

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}