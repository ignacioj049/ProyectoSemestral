import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        ComboBox<String> tipoHabitat = new ComboBox<>();
        tipoHabitat.getItems().addAll(
                "Selva Tropical", "Sabana", "Bosque Templado", "Desierto", "Taiga", "Tundra",
                "Arrecife de Coral", "Río", "Pantano", "Océano Abierto", "Glaciares y Hielo Ártico",
                "Montaña Rocosa", "Aviario", "Terrario", "Zona Nocturna"
        );
        tipoHabitat.setPromptText("Tipo de Hábitat");

        ComboBox<String> tipoAnimal = new ComboBox<>();
        tipoAnimal.setPromptText("Tipo de Animal");

        ComboBox<String> especieAnimal = new ComboBox<>();
        especieAnimal.setPromptText("Especie del Animal");

        tipoHabitat.setOnAction(event -> {
            String habitat = tipoHabitat.getValue();
            tipoAnimal.getItems().clear();
            especieAnimal.getItems().clear();
            if (habitat != null) {
                switch (habitat) {
                    case "Selva Tropical":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Reptiles", "Anfibios", "Invertebrados");
                        break;
                    case "Sabana":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Reptiles");
                        break;
                    case "Bosque Templado":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Reptiles");
                        break;
                    case "Desierto":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Reptiles", "Invertebrados");
                        break;
                    case "Taiga":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Reptiles");
                        break;
                    case "Tundra":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves", "Peces");
                        break;
                    case "Arrecife de Coral":
                        tipoAnimal.getItems().addAll("Peces", "Reptiles", "Invertebrados");
                        break;
                    case "Río":
                        tipoAnimal.getItems().addAll("Mamíferos", "Reptiles", "Peces", "Invertebrados");
                        break;
                    case "Pantano":
                        tipoAnimal.getItems().addAll("Mamíferos", "Reptiles", "Aves", "Anfibios", "Invertebrados");
                        break;
                    case "Océano Abierto":
                        tipoAnimal.getItems().addAll("Mamíferos", "Peces", "Invertebrados");
                        break;
                    case "Glaciares y Hielo Ártico":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves");
                        break;
                    case "Montaña Rocosa":
                        tipoAnimal.getItems().addAll("Mamíferos", "Aves");
                        break;
                    case "Aviario":
                        tipoAnimal.getItems().addAll("Aves");
                        break;
                    case "Terrario":
                        tipoAnimal.getItems().addAll("Reptiles", "Invertebrados");
                        break;
                    case "Zona Nocturna":
                        tipoAnimal.getItems().addAll("Mamíferos", "Reptiles");
                        break;
                }
            }
        });

        tipoAnimal.setOnAction(event -> {
            String animal = tipoAnimal.getValue();
            especieAnimal.getItems().clear();
            if (animal != null) {
                switch (animal) {
                    case "Mamíferos":
                        especieAnimal.getItems().addAll("Monos", "Jaguar", "Perezoso", "Tapir", "Armadillo gigante");
                        break;
                    case "Aves":
                        especieAnimal.getItems().addAll("Tucán", "Guacamayo", "Águila harpía", "Colibrí");
                        break;
                    case "Reptiles":
                        especieAnimal.getItems().addAll("Boa constrictor", "Caimán", "Iguana");
                        break;
                    case "Anfibios":
                        especieAnimal.getItems().addAll("Ranas dardo venenoso");
                        break;
                    case "Invertebrados":
                        especieAnimal.getItems().addAll("Mariposa Morpho azul", "Escarabajos gigantes");
                        break;
                    case "Peces":
                        especieAnimal.getItems().addAll("Pez payaso", "Pez cirujano azul", "Tiburón de arrecife");
                        break;
                }
            }
        });

        TextField nombreAnimal = new TextField();
        nombreAnimal.setPromptText("Nombre del Animal");

        TextField edadAnimal = new TextField();
        edadAnimal.setPromptText("Edad del Animal");

        ComboBox<String> unidadEdad = new ComboBox<>();
        unidadEdad.getItems().addAll("Días", "Meses", "Años");
        unidadEdad.setPromptText("Unidad de Edad");

        Button btnAgregar = new Button("Agregar Animal");
        btnAgregar.setFont(new Font("Arial", 16));
        btnAgregar.setTextFill(Color.WHITE);
        btnAgregar.setStyle("-fx-background-color: #4CAF50;");
        btnAgregar.setOnAction(event -> {
            try {
                String nombre = nombreAnimal.getText();
                String especie = especieAnimal.getValue();
                int edad = Integer.parseInt(edadAnimal.getText());
                String unidad = unidadEdad.getValue();
                String habitat = tipoHabitat.getValue();
                Animal animal = new Animal(nombre, especie, edad, unidad);
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
        layout.getChildren().addAll(tipoHabitat, tipoAnimal, especieAnimal, nombreAnimal, edadAnimal, unidadEdad, btnAgregar, btnVolver);
        layout.setTranslateX(20); // Alinear al lado izquierdo

        Scene scene = new Scene(layout, 800, 600); // Aumentar el tamaño de la ventana
        stage.setScene(scene);
        stage.show();
    }
}