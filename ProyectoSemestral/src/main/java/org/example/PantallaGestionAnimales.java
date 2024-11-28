import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PantallaGestionAnimales {
    private Stage stage;
    private ZooController controller;

    public PantallaGestionAnimales(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        TextField nombreAnimal = new TextField();
        nombreAnimal.setPromptText("Nombre del Animal");

        TextField especieAnimal = new TextField();
        especieAnimal.setPromptText("Especie del Animal");

        TextField edadAnimal = new TextField();
        edadAnimal.setPromptText("Edad del Animal");

        TextField nombreHabitat = new TextField();
        nombreHabitat.setPromptText("Nombre del Hábitat");

        Button btnAgregar = new Button("Agregar Animal");
        btnAgregar.setFont(new Font("Arial", 16));
        btnAgregar.setTextFill(Color.WHITE);
        btnAgregar.setStyle("-fx-background-color: #4CAF50;");
        btnAgregar.setOnAction(event -> {
            try {
                String nombre = nombreAnimal.getText();
                String especie = especieAnimal.getText();
                int edad = Integer.parseInt(edadAnimal.getText());
                String habitat = nombreHabitat.getText();
                Animal animal = new Animal(nombre, especie, edad);
                controller.agregarAnimalAHabitat(habitat, animal);
                System.out.println("Animal agregado: " + nombre);

                // Animación de traslación
                TranslateTransition tt = new TranslateTransition(Duration.millis(500), btnAgregar);
                tt.setByX(100);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();

                // Animación de escala para el animal agregado
                ScaleTransition st = new ScaleTransition(Duration.millis(500), nombreAnimal);
                st.setByX(1.5);
                st.setByY(1.5);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese una edad válida.");
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
        layout.getChildren().addAll(nombreAnimal, especieAnimal, edadAnimal, nombreHabitat, btnAgregar, btnVolver);
        layout.setTranslateX(20); // Alinear al lado izquierdo

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}