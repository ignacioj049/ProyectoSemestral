import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nombreAnimal, especieAnimal, edadAnimal, nombreHabitat, btnAgregar);

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}