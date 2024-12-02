import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PantallaAlertas {
    private Stage stage;
    private ZooController controller;

    public PantallaAlertas(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        VBox layout = new VBox(10);

        for (Alerta alerta : controller.getAlertas()) {
            HBox alertaBox = new HBox(10);

            Label colorLabel = new Label();
            switch (alerta.getPrioridad()) {
                case 1:
                    colorLabel.setStyle("-fx-background-color: red; -fx-min-width: 20px; -fx-min-height: 20px;");
                    break;
                case 2:
                    colorLabel.setStyle("-fx-background-color: yellow; -fx-min-width: 20px; -fx-min-height: 20px;");
                    break;
                default:
                    colorLabel.setStyle("-fx-background-color: gray; -fx-min-width: 20px; -fx-min-height: 20px;");
            }

            Label mensajeLabel = new Label(alerta.getMensaje());

            Button btnSolucionar = new Button("Solucionar");
            btnSolucionar.setOnAction(event -> controller.solucionarAlerta(alerta));

            alertaBox.getChildren().addAll(colorLabel, mensajeLabel, btnSolucionar);
            layout.getChildren().add(alertaBox);
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
