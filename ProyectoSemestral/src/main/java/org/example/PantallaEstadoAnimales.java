import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PantallaEstadoAnimales {
    private Stage stage;
    private ZooController controller;

    public PantallaEstadoAnimales(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        VBox layout = new VBox(10);

        for (Habitat habitat : controller.getZoo().getHabitats()) {
            Label habitatLabel = new Label("Hábitat: " + habitat.getNombre());
            habitatLabel.setOnMouseEntered(event -> {
                StringBuilder info = new StringBuilder("Hábitat: " + habitat.getNombre() + "\nTipo: " + habitat.getTipo() + "\nAnimales:\n");
                for (Animal animal : habitat.getAnimales()) {
                    String estadoColor;
                    switch (animal.getEstadoSalud().toLowerCase()) {
                        case "saludable":
                            estadoColor = "verde";
                            break;
                        case "enfermo":
                            estadoColor = "amarillo";
                            break;
                        case "crítico":
                            estadoColor = "rojo";
                            break;
                        default:
                            estadoColor = "gris";
                    }
                    info.append(" - ").append(animal.getNombre()).append(" (").append(animal.getEspecie()).append(") - Estado: ").append(animal.getEstadoSalud()).append("\n");
                }
                habitatLabel.setText(info.toString());

                ScaleTransition st = new ScaleTransition(Duration.millis(200), habitatLabel);
                st.setByX(1.2);
                st.setByY(1.2);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
            });
            habitatLabel.setOnMouseExited(event -> habitatLabel.setText("Hábitat: " + habitat.getNombre()));
            layout.getChildren().add(habitatLabel);
        }

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> {
            PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
            pantallaInicio.mostrar();
        });

        layout.getChildren().add(btnVolver);

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}