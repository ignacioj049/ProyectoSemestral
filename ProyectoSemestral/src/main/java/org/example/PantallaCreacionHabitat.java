import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        TextField tipoHabitat = new TextField();
        tipoHabitat.setPromptText("Tipo de Hábitat");

        Button btnCrear = new Button("Crear");
        btnCrear.setFont(new Font("Arial", 16));
        btnCrear.setTextFill(Color.WHITE);
        btnCrear.setStyle("-fx-background-color: #4CAF50;");
        btnCrear.setOnAction(event -> {
            String nombre = nombreHabitat.getText();
            String tipo = tipoHabitat.getText();
            controller.crearHabitat(nombre, tipo);
            System.out.println("Hábitat creado: " + nombre);

            // Animación de escala
            ScaleTransition st = new ScaleTransition(Duration.millis(200), btnCrear);
            st.setByX(1.2);
            st.setByY(1.2);
            st.setAutoReverse(true);
            st.setCycleCount(2);
            st.play();
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #f0f0f0;");
        layout.getChildren().addAll(nombreHabitat, tipoHabitat, btnCrear);

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}