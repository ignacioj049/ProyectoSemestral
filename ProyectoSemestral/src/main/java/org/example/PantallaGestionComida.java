import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Alert;

public class PantallaGestionComida {
    private Stage stage;
    private ZooController controller;

    public PantallaGestionComida(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label lblSeleccionaHabitat = new Label("Selecciona un Hábitat:");
        lblSeleccionaHabitat.setFont(new Font("Arial", 16));

        ComboBox<String> habitatComboBox = new ComboBox<>();
        for (Habitat habitat : controller.getZoo().getHabitats()) {
            habitatComboBox.getItems().add(habitat.getNombre() + " (" + habitat.getTipo() + ")");
        }
        habitatComboBox.setPromptText("Selecciona el Hábitat");

        Label lblCantidadComida = new Label("Cantidad de Comida:");
        lblCantidadComida.setFont(new Font("Arial", 16));

        Label lblCantidadActual = new Label("0 kg");
        lblCantidadActual.setFont(new Font("Arial", 16));
        lblCantidadActual.setTextFill(Color.BLUE);

        habitatComboBox.setOnAction(event -> {
            String habitatInfo = habitatComboBox.getValue();
            if (habitatInfo != null) {
                String[] habitatParts = habitatInfo.split(" \\(");
                String nombreHabitat = habitatParts[0];
                Habitat habitat = controller.getZoo().buscarHabitatPorNombre(nombreHabitat);
                if (habitat != null) {
                    lblCantidadActual.setText(habitat.getCantidadComida() + " kg");
                }
            }
        });

        Label lblTipoComida = new Label("Tipo de Comida:");
        lblTipoComida.setFont(new Font("Arial", 16));

        ComboBox<String> tipoComidaComboBox = new ComboBox<>();
        tipoComidaComboBox.getItems().addAll("Carne", "Vegetales", "Fruta");
        tipoComidaComboBox.setPromptText("Selecciona el Tipo de Comida");

        TextField cantidadComidaField = new TextField();
        cantidadComidaField.setPromptText("Cantidad de Comida (kg)");

        Button btnAgregar = new Button("Agregar Comida");
        btnAgregar.setFont(new Font("Arial", 16));
        btnAgregar.setTextFill(Color.WHITE);
        btnAgregar.setStyle("-fx-background-color: #4CAF50;");
        btnAgregar.setOnAction(event -> {
            try {
                String habitatInfo = habitatComboBox.getValue();
                String tipoComida = tipoComidaComboBox.getValue();
                int cantidad = Integer.parseInt(cantidadComidaField.getText());
                if (habitatInfo != null && tipoComida != null && cantidad > 0) {
                    String[] habitatParts = habitatInfo.split(" \\(");
                    String nombreHabitat = habitatParts[0];
                    Habitat habitat = controller.getZoo().buscarHabitatPorNombre(nombreHabitat);
                    if (habitat != null) {
                        habitat.agregarComida(tipoComida, cantidad);
                        lblCantidadActual.setText(habitat.getCantidadComida() + " kg");
                        mostrarAlerta("Comida agregada exitosamente.");
                    } else {
                        mostrarAlerta("Hábitat no encontrado.");
                    }
                } else {
                    mostrarAlerta("Por favor, completa todos los campos correctamente.");
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Cantidad debe ser un número entero válido.");
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

        layout.getChildren().addAll(lblSeleccionaHabitat, habitatComboBox, lblCantidadComida, lblCantidadActual, lblTipoComida, tipoComidaComboBox, cantidadComidaField, btnAgregar, btnVolver);
        Scene scene = new Scene(layout, 800, 600);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
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