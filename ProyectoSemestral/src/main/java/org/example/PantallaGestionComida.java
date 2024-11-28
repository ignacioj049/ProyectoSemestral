import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(tipoComida, cantidadComida, btnAgregar);

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}