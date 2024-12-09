package views;
import controllers.ZooController;
import models.animals.Animal;
import models.habitats.Habitat;
import enums.TipoHabitat;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Button btnAgregarAnimales = new Button("Agregar Animales");
        btnAgregarAnimales.setFont(new Font("Arial", 16));
        btnAgregarAnimales.setTextFill(Color.WHITE);
        btnAgregarAnimales.setStyle("-fx-background-color: #4CAF50;");
        btnAgregarAnimales.setOnAction(event -> mostrarAgregarAnimales());

        Button btnMoverAnimales = new Button("Mover Animales");
        btnMoverAnimales.setFont(new Font("Arial", 16));
        btnMoverAnimales.setTextFill(Color.WHITE);
        btnMoverAnimales.setStyle("-fx-background-color: #4CAF50;");
        btnMoverAnimales.setOnAction(event -> mostrarMoverAnimales());

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> {
            PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
            pantallaInicio.mostrar();
        });

        layout.getChildren().addAll(btnAgregarAnimales, btnMoverAnimales, btnVolver);

        Scene scene = new Scene(layout, 800, 600);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    private void mostrarAgregarAnimales() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        ComboBox<String> habitatAnimal = new ComboBox<>();
        for (Habitat habitat : controller.getZoo().getHabitats()) {
            habitatAnimal.getItems().add(habitat.getNombre() + " (" + habitat.getTipo() + ")");
        }
        habitatAnimal.setPromptText("Selecciona el Hábitat");

        ComboBox<String> tipoAnimal = new ComboBox<>();
        tipoAnimal.setPromptText("Tipo de Animal");

        ComboBox<String> especieAnimal = new ComboBox<>();
        especieAnimal.setPromptText("Especie de Animal");

        habitatAnimal.setOnAction(event -> {
            tipoAnimal.getItems().clear();
            especieAnimal.getItems().clear();
            String habitatInfo = habitatAnimal.getValue();
            if (habitatInfo != null) {
                String[] habitatParts = habitatInfo.split(" \\(");
                String tipoHabitat = habitatParts[1].replace(")", "");
                switch (tipoHabitat) {
                    case "Sabana Africana":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave");
                        break;
                    case "Jungla Tropical":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil");
                        break;
                    case "Región Ártica":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave");
                        break;
                    case "Desierto":
                        tipoAnimal.getItems().addAll("Mamífero", "Reptil");
                        break;
                    case "Océano y Acuarios":
                        tipoAnimal.getItems().addAll("Mamífero Marino", "Pez");
                        break;
                    case "Bosques Templados":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave");
                        break;
                }
            }
        });

        tipoAnimal.setOnAction(event -> {
            especieAnimal.getItems().clear();
            String tipo = tipoAnimal.getValue();
            String habitatInfo = habitatAnimal.getValue();
            if (habitatInfo != null) {
                String[] habitatParts = habitatInfo.split(" \\(");
                String tipoHabitat = habitatParts[1].replace(")", "");
                switch (tipoHabitat) {
                    case "Sabana Africana":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("León", "Elefante", "Jirafa", "Cebra", "Rinoceronte");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Avestruz");
                        break;
                    case "Jungla Tropical":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Tigre");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll( "Tucán");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Cocodrilo");
                        break;
                    case "Región Ártica":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Oso polar", "Zorro ártico", "Lobo ártico");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Pingüino");
                        break;
                    case "Desierto":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Camello", "Fennec");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Serpiente");
                        break;
                    case "Océano y Acuarios":
                        if (tipo.equals("Mamífero Marino")) especieAnimal.getItems().addAll("Delfín", "Orca");
                        else if (tipo.equals("Pez")) especieAnimal.getItems().addAll("Tiburón", "Caballito de mar", "Pez payaso");
                        break;
                    case "Bosques Templados":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Lince", "Oso pardo", "Ciervo");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Águila", "Lechuza");
                        break;
                }
            }
        });

        TextField nombreAnimal = new TextField();
        nombreAnimal.setPromptText("Nombre del Animal");

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setFont(new Font("Arial", 16));
        btnGuardar.setTextFill(Color.WHITE);
        btnGuardar.setStyle("-fx-background-color: #4CAF50;");
        btnGuardar.setOnAction(event -> {
            String nombre = nombreAnimal.getText();
            String tipo = tipoAnimal.getValue();
            String especie = especieAnimal.getValue();
            String habitatInfo = habitatAnimal.getValue();
            if (nombre != null && !nombre.isEmpty() && tipo != null && especie != null && habitatInfo != null) {
                String[] habitatParts = habitatInfo.split(" \\(");
                String nombreHabitat = habitatParts[0];
                Habitat habitat = controller.getZoo().buscarHabitatPorNombre(nombreHabitat);
                if (habitat != null) {
                    if (habitat.getAnimales().size() < habitat.getCapacidad()) {
                        // Añadir el tipo de comida preferida por el animal
                        String tipoComida = determinarTipoComida(especie);
                        Animal animal = new Animal(nombre, especie, 0, "Años", tipoComida); // Edad y unidad de edad predeterminadas
                        controller.agregarAnimalAHabitat(nombreHabitat, animal);
                        mostrarAlerta("Animal agregado exitosamente.");
                    } else {
                        mostrarAlerta("El hábitat está lleno.");
                    }
                } else {
                    mostrarAlerta("Hábitat no encontrado.");
                }
            } else {
                mostrarAlerta("Por favor, completa todos los campos.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> mostrar());

        layout.getChildren().addAll(habitatAnimal, tipoAnimal, especieAnimal, nombreAnimal, btnGuardar, btnVolver);

        Scene scene = new Scene(layout, 800, 600);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    private void mostrarMoverAnimales() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        ListView<String> listaAnimales = new ListView<>();
        for (Habitat habitat : controller.getZoo().getHabitats()) {
            for (Animal animal : habitat.getAnimales()) {
                listaAnimales.getItems().add(animal.getNombre() + " (" + ") - " + habitat.getNombre() + " (" + habitat.getTipo() + ")");
            }
        }

        ComboBox<String> habitatDestino = new ComboBox<>();
        for (Habitat habitat : controller.getZoo().getHabitats()) {
            habitatDestino.getItems().add(habitat.getNombre() + " (" + habitat.getTipo() + ")");
        }
        habitatDestino.setPromptText("Hábitat Destino");

        Button btnMover = new Button("Mover Animal");
        btnMover.setFont(new Font("Arial", 16));
        btnMover.setTextFill(Color.WHITE);
        btnMover.setStyle("-fx-background-color: #4CAF50;");
        btnMover.setOnAction(event -> {
            String animalInfo = listaAnimales.getSelectionModel().getSelectedItem();
            String habitatDestinoInfo = habitatDestino.getValue();
            if (animalInfo != null && habitatDestinoInfo != null) {
                String[] animalParts = animalInfo.split(" - ");
                String nombreAnimal = animalParts[0].split(" \\(")[0];
                String nombreHabitatOrigen = animalParts[1].split(" \\(")[0];
                String nombreHabitatDestino = habitatDestinoInfo.split(" \\(")[0];
                controller.moverAnimal(nombreAnimal, nombreHabitatOrigen, nombreHabitatDestino);
            } else {
                mostrarAlerta("Por favor, selecciona un animal y un hábitat destino.");
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> mostrar());

        layout.getChildren().addAll(new Label("Animales"), listaAnimales, new Label("Hábitat Destino"), habitatDestino, btnMover, btnVolver);

        Scene scene = new Scene(layout, 800, 600);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    private boolean esHabitatAdecuado(String habitat, String tipoAnimal) {
        switch (habitat) {
            case "Selva Tropical":
                return tipoAnimal.equals("Tigre") || tipoAnimal.equals("Guacamayo") || tipoAnimal.equals("Tucán") || tipoAnimal.equals("Cocodrilo");
            case "Sabana Africana":
                return tipoAnimal.equals("León") || tipoAnimal.equals("Elefante") || tipoAnimal.equals("Jirafa") || tipoAnimal.equals("Cebra") ||
                        tipoAnimal.equals("Rinoceronte") || tipoAnimal.equals("Avestruz");
            case "Bosque Templado":
                return tipoAnimal.equals("Lince") || tipoAnimal.equals("Oso pardo") || tipoAnimal.equals("Ciervo") || tipoAnimal.equals("Águila") ||
                        tipoAnimal.equals("Lechuza");
            case "Desierto":
                return tipoAnimal.equals("Camello") || tipoAnimal.equals("Fennec") || tipoAnimal.equals("Serpiente");
            case "Región Ártica":
                return tipoAnimal.equals("Oso polar") || tipoAnimal.equals("Zorro ártico") || tipoAnimal.equals("Lobo ártico") || tipoAnimal.equals("Pingüino");
            case "Océano y Acuarios":
                return tipoAnimal.equals("Delfín") || tipoAnimal.equals("Orca") || tipoAnimal.equals("Tiburón") || tipoAnimal.equals("Caballito de mar") ||
                        tipoAnimal.equals("Pez payaso");
            default:
                return false;
        }
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

    private String determinarTipoComida(String especie) {
        switch (especie.toLowerCase()) {
            case "león":
            case "tigre":
                return "carne";
            case "elefante":
            case "jirafa":
            case "cebra":
                return "vegetales";
            case "orangután":
            case "perezoso":
            case "tapir":
                return "frutas";
            default:
                return "vegetales"; // Valor predeterminado
        }
    }
}
