import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

        for (Habitat habitat : controller.getZoo().getHabitats()) {
            Button btnHabitat = new Button(habitat.getNombre());
            btnHabitat.setFont(new Font("Arial", 16));
            btnHabitat.setTextFill(Color.WHITE);
            btnHabitat.setStyle("-fx-background-color: #4CAF50;");
            btnHabitat.setOnAction(event -> mostrarGestionComidaHabitat(habitat));
            layout.getChildren().add(btnHabitat);
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
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    private void mostrarGestionComidaHabitat(Habitat habitat) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label lblComida = new Label("Comida en " + habitat.getNombre() + ":");
        lblComida.setFont(new Font("Arial", 16));

        Label lblCarne = new Label("Carne: " + habitat.getComidaCarne() + " kg");
        Label lblVegetales = new Label("Vegetales: " + habitat.getComidaVegetales() + " kg");
        Label lblFrutas = new Label("Frutas: " + habitat.getComidaFrutas() + " kg");

        TextField tipoComida = new TextField();
        tipoComida.setPromptText("Tipo de Comida (carne, vegetales, frutas)");

        TextField cantidadComida = new TextField();
        cantidadComida.setPromptText("Cantidad de Comida (kg)");

        Button btnAgregar = new Button("Agregar Comida");
        btnAgregar.setFont(new Font("Arial", 16));
        btnAgregar.setTextFill(Color.WHITE);
        btnAgregar.setStyle("-fx-background-color: #4CAF50;");
        btnAgregar.setOnAction(event -> {
            try {
                String tipo = tipoComida.getText();
                int cantidad = Integer.parseInt(cantidadComida.getText());
                controller.agregarComidaAHabitat(habitat.getNombre(), tipo, cantidad);
                lblCarne.setText("Carne: " + habitat.getComidaCarne() + " kg");
                lblVegetales.setText("Vegetales: " + habitat.getComidaVegetales() + " kg");
                lblFrutas.setText("Frutas: " + habitat.getComidaFrutas() + " kg");
            } catch (NumberFormatException e) {
                mostrarAlerta("Por favor, ingrese una cantidad válida.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> mostrar());

        layout.getChildren().addAll(lblComida, lblCarne, lblVegetales, lblFrutas, tipoComida, cantidadComida, btnAgregar, btnVolver);

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