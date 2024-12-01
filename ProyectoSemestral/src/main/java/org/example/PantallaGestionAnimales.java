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
                    case "Selva Tropical":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil", "Anfibio", "Invertebrado");
                        break;
                    case "Sabana":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil", "Invertebrado");
                        break;
                    case "Bosque Templado":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil", "Invertebrado");
                        break;
                    case "Desierto":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil", "Invertebrado");
                        break;
                    case "Taiga":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil");
                        break;
                    case "Tundra":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Invertebrado");
                        break;
                    case "Arrecife de Coral":
                        tipoAnimal.getItems().addAll("Pez", "Invertebrado");
                        break;
                    case "Río":
                        tipoAnimal.getItems().addAll("Pez", "Reptil", "Anfibio");
                        break;
                    case "Pantano":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Reptil", "Anfibio", "Invertebrado");
                        break;
                    case "Océano Abierto":
                        tipoAnimal.getItems().addAll("Mamífero", "Pez", "Invertebrado");
                        break;
                    case "Glaciares y Hielo Ártico":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave", "Invertebrado");
                        break;
                    case "Montaña Rocosa":
                        tipoAnimal.getItems().addAll("Mamífero", "Ave");
                        break;
                    case "Aviario":
                        tipoAnimal.getItems().addAll("Ave");
                        break;
                    case "Terrario":
                        tipoAnimal.getItems().addAll("Reptil", "Anfibio");
                        break;
                    case "Acuario":
                        tipoAnimal.getItems().addAll("Pez", "Invertebrado");
                        break;
                    case "Zona Nocturna":
                        tipoAnimal.getItems().addAll("Mamífero", "Reptil", "Invertebrado");
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
                    case "Selva Tropical":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Jaguar", "Mono aullador", "Perezoso", "Tapir");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Guacamayo", "Tucán", "Águila arpía");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Boa constrictora", "Anaconda", "Camaleón");
                        else if (tipo.equals("Anfibio")) especieAnimal.getItems().addAll("Rana dardo venenosa");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Mariposa morpho", "Hormiga cortadora de hojas");
                        break;
                    case "Sabana":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("León", "Elefante africano", "Jirafa", "Guepardo", "Cebra");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Avestruz", "Secretary bird");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Cocodrilo del Nilo", "Víbora bufadora");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Escarabajo pelotero");
                        break;
                    case "Bosque Templado":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Oso pardo", "Lobo gris", "Ciervo", "Zorro rojo");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Búho real", "Pájaro carpintero");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Serpiente rata negra");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Escarabajo ciervo");
                        break;
                    case "Desierto":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Camello", "Fenec", "Ratón canguro");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Águila calva", "Buitre");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Serpiente cascabel", "Lagarto cornudo");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Escorpión", "Cucaracha del desierto");
                        break;
                    case "Taiga":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Alce", "Lince", "Zorro ártico");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Búho nival", "Ganso salvaje");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Culebra de collar");
                        break;
                    case "Tundra":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Oso polar", "Reno", "Lobo ártico");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Perdiz nival", "Charrán ártico");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Mosquito ártico");
                        break;
                    case "Arrecife de Coral":
                        if (tipo.equals("Pez")) especieAnimal.getItems().addAll("Pez payaso", "Tiburón de arrecife", "Mero");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Coral", "Estrella de mar", "Pulpo");
                        break;
                    case "Río":
                        if (tipo.equals("Pez")) especieAnimal.getItems().addAll("Bagre", "Piraña", "Trucha");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Tortuga de río", "Caimán");
                        else if (tipo.equals("Anfibio")) especieAnimal.getItems().addAll("Rana toro");
                        break;
                    case "Pantano":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Nutria", "Castor");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Garza real", "Ibis");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Cocodrilo americano", "Tortuga mordedora");
                        else if (tipo.equals("Anfibio")) especieAnimal.getItems().addAll("Salamandra gigante");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Libélula", "Caracol acuático");
                        break;
                    case "Océano Abierto":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Delfín", "Ballena jorobada", "Orca");
                        else if (tipo.equals("Pez")) especieAnimal.getItems().addAll("Tiburón blanco", "Pez espada");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Medusa", "Calamar gigante");
                        break;
                    case "Glaciares y Hielo Ártico":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Oso polar", "Foca", "Morsa");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Pingüino emperador");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Krill");
                        break;
                    case "Montaña Rocosa":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Cabra montés", "Puma");
                        else if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Cóndor", "Águila real");
                        break;
                    case "Aviario":
                        if (tipo.equals("Ave")) especieAnimal.getItems().addAll("Guacamayo", "Tucán", "Loro gris", "Halcón peregrino");
                        break;
                    case "Terrario":
                        if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Iguana", "Gecko", "Pitón real");
                        else if (tipo.equals("Anfibio")) especieAnimal.getItems().addAll("Salamandra tigre");
                        break;
                    case "Acuario":
                        if (tipo.equals("Pez")) especieAnimal.getItems().addAll("Tiburón gato", "Manta raya", "Pez león");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Caballito de mar", "Erizo de mar");
                        break;
                    case "Zona Nocturna":
                        if (tipo.equals("Mamífero")) especieAnimal.getItems().addAll("Murciélago", "Aye-aye");
                        else if (tipo.equals("Reptil")) especieAnimal.getItems().addAll("Serpiente nocturna");
                        else if (tipo.equals("Invertebrado")) especieAnimal.getItems().addAll("Escorpión fluorescente");
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
                        Animal animal = new Animal(nombre, especie, 0, "Años"); // Edad y unidad de edad predeterminadas
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
                listaAnimales.getItems().add(animal.getNombre() + " (" + animal.getEspecie() + ") - " + habitat.getNombre() + " (" + habitat.getTipo() + ")");
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
                Habitat habitatOrigen = controller.getZoo().buscarHabitatPorNombre(nombreHabitatOrigen);
                Habitat habitatDestinoObj = controller.getZoo().buscarHabitatPorNombre(nombreHabitatDestino);
                if (habitatDestinoObj != null && habitatOrigen != null) {
                    Animal animal = controller.getZoo().buscarAnimalPorNombre(nombreAnimal, habitatOrigen);
                    if (animal != null) {
                        if (esHabitatAdecuado(habitatDestinoObj, animal.getEspecie())) {
                            if (habitatDestinoObj.getAnimales().size() < habitatDestinoObj.getCapacidad()) {
                                controller.moverAnimal(nombreAnimal, nombreHabitatOrigen, nombreHabitatDestino);
                                mostrarAlerta("Animal movido exitosamente.");
                            } else {
                                mostrarAlerta("El hábitat destino está lleno.");
                            }
                        } else {
                            mostrarAlerta("El hábitat destino no es adecuado para el animal.");
                        }
                    } else {
                        mostrarAlerta("Animal no encontrado en el hábitat de origen.");
                    }
                } else {
                    mostrarAlerta("Hábitat de origen o destino no encontrado.");
                }
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

    private boolean esHabitatAdecuado(Habitat habitat, String tipoAnimal) {
        switch (habitat.getTipo()) {
            case "Selva Tropical":
                return tipoAnimal.equals("Jaguar") || tipoAnimal.equals("Mono aullador") || tipoAnimal.equals("Perezoso") || tipoAnimal.equals("Tapir") ||
                        tipoAnimal.equals("Guacamayo") || tipoAnimal.equals("Tucán") || tipoAnimal.equals("Águila arpía") ||
                        tipoAnimal.equals("Boa constrictora") || tipoAnimal.equals("Anaconda") || tipoAnimal.equals("Camaleón") ||
                        tipoAnimal.equals("Rana dardo venenosa") ||
                        tipoAnimal.equals("Mariposa morpho") || tipoAnimal.equals("Hormiga cortadora de hojas");
            case "Sabana":
                return tipoAnimal.equals("León") || tipoAnimal.equals("Elefante africano") || tipoAnimal.equals("Jirafa") || tipoAnimal.equals("Guepardo") || tipoAnimal.equals("Cebra") ||
                        tipoAnimal.equals("Avestruz") || tipoAnimal.equals("Secretary bird") ||
                        tipoAnimal.equals("Cocodrilo del Nilo") || tipoAnimal.equals("Víbora bufadora") ||
                        tipoAnimal.equals("Escarabajo pelotero");
            case "Bosque Templado":
                return tipoAnimal.equals("Oso pardo") || tipoAnimal.equals("Lobo gris") || tipoAnimal.equals("Ciervo") || tipoAnimal.equals("Zorro rojo") ||
                        tipoAnimal.equals("Búho real") || tipoAnimal.equals("Pájaro carpintero") ||
                        tipoAnimal.equals("Serpiente rata negra") ||
                        tipoAnimal.equals("Escarabajo ciervo");
            case "Desierto":
                return tipoAnimal.equals("Camello") || tipoAnimal.equals("Fenec") || tipoAnimal.equals("Ratón canguro") ||
                        tipoAnimal.equals("Águila calva") || tipoAnimal.equals("Buitre") ||
                        tipoAnimal.equals("Serpiente cascabel") || tipoAnimal.equals("Lagarto cornudo") ||
                        tipoAnimal.equals("Escorpión") || tipoAnimal.equals("Cucaracha del desierto");
            case "Taiga":
                return tipoAnimal.equals("Alce") || tipoAnimal.equals("Lince") || tipoAnimal.equals("Zorro ártico") ||
                        tipoAnimal.equals("Búho nival") || tipoAnimal.equals("Ganso salvaje") ||
                        tipoAnimal.equals("Culebra de collar");
            case "Tundra":
                return tipoAnimal.equals("Oso polar") || tipoAnimal.equals("Reno") || tipoAnimal.equals("Lobo ártico") ||
                        tipoAnimal.equals("Perdiz nival") || tipoAnimal.equals("Charrán ártico") ||
                        tipoAnimal.equals("Mosquito ártico");
            case "Arrecife de Coral":
                return tipoAnimal.equals("Pez payaso") || tipoAnimal.equals("Tiburón de arrecife") || tipoAnimal.equals("Mero") ||
                        tipoAnimal.equals("Coral") || tipoAnimal.equals("Estrella de mar") || tipoAnimal.equals("Pulpo");
            case "Río":
                return tipoAnimal.equals("Bagre") || tipoAnimal.equals("Piraña") || tipoAnimal.equals("Trucha") ||
                        tipoAnimal.equals("Tortuga de río") || tipoAnimal.equals("Caimán") ||
                        tipoAnimal.equals("Rana toro");
            case "Pantano":
                return tipoAnimal.equals("Nutria") || tipoAnimal.equals("Castor") ||
                        tipoAnimal.equals("Garza real") || tipoAnimal.equals("Ibis") ||
                        tipoAnimal.equals("Cocodrilo americano") || tipoAnimal.equals("Tortuga mordedora") ||
                        tipoAnimal.equals("Salamandra gigante") ||
                        tipoAnimal.equals("Libélula") || tipoAnimal.equals("Caracol acuático");
            case "Océano Abierto":
                return tipoAnimal.equals("Delfín") || tipoAnimal.equals("Ballena jorobada") || tipoAnimal.equals("Orca") ||
                        tipoAnimal.equals("Tiburón blanco") || tipoAnimal.equals("Pez espada") ||
                        tipoAnimal.equals("Medusa") || tipoAnimal.equals("Calamar gigante");
            case "Glaciares y Hielo Ártico":
                return tipoAnimal.equals("Oso polar") || tipoAnimal.equals("Foca") || tipoAnimal.equals("Morsa") ||
                        tipoAnimal.equals("Pingüino emperador") ||
                        tipoAnimal.equals("Krill");
            case "Montaña Rocosa":
                return tipoAnimal.equals("Cabra montés") || tipoAnimal.equals("Puma") ||
                        tipoAnimal.equals("Cóndor") || tipoAnimal.equals("Águila real");
            case "Aviario":
                return tipoAnimal.equals("Guacamayo") || tipoAnimal.equals("Tucán") || tipoAnimal.equals("Loro gris") || tipoAnimal.equals("Halcón peregrino");
            case "Terrario":
                return tipoAnimal.equals("Iguana") || tipoAnimal.equals("Gecko") || tipoAnimal.equals("Pitón real") ||
                        tipoAnimal.equals("Salamandra tigre");
            case "Acuario":
                return tipoAnimal.equals("Tiburón gato") || tipoAnimal.equals("Manta raya") || tipoAnimal.equals("Pez león") ||
                        tipoAnimal.equals("Caballito de mar") || tipoAnimal.equals("Erizo de mar");
            case "Zona Nocturna":
                return tipoAnimal.equals("Murciélago") || tipoAnimal.equals("Aye-aye") ||
                        tipoAnimal.equals("Serpiente nocturna") ||
                        tipoAnimal.equals("Escorpión fluorescente");
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
}