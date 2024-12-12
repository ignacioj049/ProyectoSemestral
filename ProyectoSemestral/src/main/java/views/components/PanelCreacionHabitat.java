package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import enums.TipoHabitat;
import utils.DataFormats;
import utils.ResourceManager;

public class PanelCreacionHabitat extends VBox {
    private ZooController controller;
    private MapaZooComponent mapaZoo;
    private GridPane gridHabitats;

    public PanelCreacionHabitat(ZooController controller, MapaZooComponent mapaZoo) {
        this.controller = controller;
        this.mapaZoo = mapaZoo;
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Botón de cerrar
        Button btnCerrar = new Button("✕");
        btnCerrar.setStyle("-fx-background-color: transparent; -fx-text-fill: #666; -fx-font-size: 18px;");
        btnCerrar.setOnAction(e -> setVisible(false));

        // Título
        Label titulo = new Label("Crear Hábitat");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;-fx-text-fill: black;");

        // Header con botón de cerrar
        HBox header = new HBox(titulo, btnCerrar);
        header.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(titulo, Priority.ALWAYS);
        titulo.setAlignment(Pos.CENTER);

        // Descripción
        Label descripcion = new Label("Arrastra un tipo de hábitat al mapa para crearlo");
        descripcion.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;-fx-text-fill: black;");
        descripcion.setWrapText(true);

        // Grid de hábitats
        gridHabitats = new GridPane();
        gridHabitats.setHgap(20);
        gridHabitats.setVgap(20);
        gridHabitats.setAlignment(Pos.CENTER);

        // ScrollPane para el grid
        ScrollPane scrollPane = new ScrollPane(gridHabitats);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        crearTarjetasHabitat();

        getChildren().addAll(header, descripcion, scrollPane);
        setMaxWidth(500);
    }

    private void crearTarjetasHabitat() {
        int col = 0;
        int row = 0;

        for (TipoHabitat tipo : TipoHabitat.values()) {
            VBox tarjeta = crearTarjetaHabitat(tipo);
            gridHabitats.add(tarjeta, col, row);

            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }

    private VBox crearTarjetaHabitat(TipoHabitat tipo) {
        VBox tarjeta = new VBox(10);
        tarjeta.setPadding(new Insets(15));
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPrefWidth(200);

        try {
            // Cargar imagen
            String imagePath = "images/habitats/" + tipo.name().toLowerCase() + ".png";
            Image imagen = ResourceManager.loadImage(imagePath);

            if (imagen != null) {
                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                tarjeta.getChildren().add(imageView);
            } else {
                // Crear placeholder si no se encuentra la imagen
                Rectangle placeholder = new Rectangle(100, 100);
                placeholder.setFill(Color.LIGHTGRAY);
                placeholder.setStroke(Color.GRAY);
                tarjeta.getChildren().add(placeholder);

                // Agregar texto al placeholder
                Label placeholderText = new Label(tipo.getNombre().substring(0, 1));
                placeholderText.setStyle("-fx-font-size: 36px; -fx-text-fill: #666;-fx-text-fill: black;");
                tarjeta.getChildren().add(placeholderText);
            }

            // Nombre del hábitat
            Label nombreLabel = new Label(tipo.getNombre());
            nombreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;-fx-text-fill: black;");

            // Descripción
            Label descLabel = new Label(tipo.getDescripcion());
            descLabel.setWrapText(true);
            descLabel.setStyle("-fx-text-alignment: center;-fx-text-fill: black;");

            // Información adicional
            Label infoLabel = new Label("Arrastra para crear");
            infoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666; -fx-font-style: italic;-fx-text-fill: black;");

            tarjeta.getChildren().addAll(nombreLabel, descLabel, infoLabel);

        } catch (Exception e) {
            System.err.println("Error al crear tarjeta para " + tipo.getNombre() + ": " + e.getMessage());
            e.printStackTrace();
        }

        // Estilo de la tarjeta
        tarjeta.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ddd;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);"
        );

        // Efectos hover
        tarjeta.setOnMouseEntered(e ->
                tarjeta.setStyle(
                        "-fx-background-color: #f8f8f8;" +
                                "-fx-border-color: #ccc;" +
                                "-fx-border-radius: 10;" +
                                "-fx-background-radius: 10;" +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);"
                )
        );

        tarjeta.setOnMouseExited(e ->
                tarjeta.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: #ddd;" +
                                "-fx-border-radius: 10;" +
                                "-fx-background-radius: 10;" +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);"
                )
        );

        // Configurar drag and drop
        tarjeta.setOnDragDetected(event -> {
            Dragboard db = tarjeta.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.put(DataFormats.HABITAT_FORMAT, tipo);
            db.setContent(content);

            // Crear imagen de vista previa para el drag
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            db.setDragView(tarjeta.snapshot(sp, null), event.getX(), event.getY());

            event.consume();
        });

        return tarjeta;
    }

    public void solicitarDatosHabitat(TipoHabitat tipo, double x, double y) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Nuevo Hábitat");
        dialog.setHeaderText("Crear nuevo hábitat de tipo " + tipo.getNombre());

        // Campos del formulario
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del hábitat");

        Spinner<Integer> capacidadSpinner = new Spinner<>(1, 10, 5);
        capacidadSpinner.setEditable(true);
        capacidadSpinner.setPrefWidth(100);

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(new Label("Capacidad:"), 0, 1);
        grid.add(capacidadSpinner, 1, 1);

        // Agregar botones
        ButtonType crearButtonType = new ButtonType("Crear", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(crearButtonType, ButtonType.CANCEL);

        // Habilitar/deshabilitar botón crear según validación
        Node crearButton = dialog.getDialogPane().lookupButton(crearButtonType);
        crearButton.setDisable(true);

        // Validar entrada
        nombreField.textProperty().addListener((observable, oldValue, newValue) -> {
            crearButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Convertir resultado
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == crearButtonType) {
                return nombreField.getText();
            }
            return null;
        });

        // Manejar resultado
        dialog.showAndWait().ifPresent(nombre -> {
            try {
                if (nombre.trim().isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío");
                }
                controller.crearHabitat(nombre.trim(), tipo, capacidadSpinner.getValue());
                mapaZoo.refrescarMapa();
            } catch (Exception e) {
                mostrarError(e.getMessage());
            }
        });
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}