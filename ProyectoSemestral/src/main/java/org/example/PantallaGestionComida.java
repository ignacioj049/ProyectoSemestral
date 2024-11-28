import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        TextField tipoComida = new TextField();
        tipoComida.setPromptText("Tipo de Comida");

        TextField cantidadComida = new TextField();
        cantidadComida.setPromptText("Cantidad de Comida");

        Button btnAgregar = new Button("Agregar Comida");
        btnAgregar.setFont(new Font("Arial", 16));
        btnAgregar.setTextFill(Color.WHITE);
        btnAgregar.setStyle("-fx-background-color: #4CAF50;");
        btnAgregar.setOnAction(event -> {
            try {
                String tipo = tipoComida.getText();
                int cantidad = Integer.parseInt(cantidadComida.getText());
                Comida comida = new Comida(tipo, cantidad);
                System.out.println("Comida agregada: " + tipo);

                // Animación de desvanecimiento
                FadeTransition ft = new FadeTransition(Duration.millis(500), btnAgregar);
                ft.setFromValue(1.0);
                ft.setToValue(0.3);
                ft.setAutoReverse(true);
                ft.setCycleCount(2);
                ft.play();

                // Animación de escala para la comida agregada
                ScaleTransition st = new ScaleTransition(Duration.millis(500), tipoComida);
                st.setByX(1.5);
                st.setByY(1.5);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese una cantidad válida.");
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
        layout.getChildren().addAll(tipoComida, cantidadComida, btnAgregar, btnVolver);
        layout.setTranslateX(20); // Alinear al lado izquierdo

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}