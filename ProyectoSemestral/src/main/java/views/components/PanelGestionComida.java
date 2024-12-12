package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import enums.TipoComida;
import utils.DataFormats;
import utils.ResourceManager;

public class PanelGestionComida extends VBox {
    private ZooController controller;
    private MapaZooComponent mapaZoo;
    private GridPane gridComidas;

    public PanelGestionComida(ZooController controller, MapaZooComponent mapaZoo) {
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
        Label titulo = new Label("Gestionar Comida");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black;");

        // Header con botón de cerrar
        HBox header = new HBox(titulo, btnCerrar);
        header.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(titulo, Priority.ALWAYS);
        titulo.setAlignment(Pos.CENTER);

        // Descripción
        Label descripcion = new Label("Arrastra la comida al animal para alimentarlo");
        descripcion.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        descripcion.setWrapText(true);

        // Grid de comidas
        gridComidas = new GridPane();
        gridComidas.setHgap(20);
        gridComidas.setVgap(20);
        gridComidas.setAlignment(Pos.CENTER);

        // ScrollPane para el grid
        ScrollPane scrollPane = new ScrollPane(gridComidas);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        crearTarjetasComida();

        getChildren().addAll(header, descripcion, scrollPane);
        setMaxWidth(500);
    }

    private void crearTarjetasComida() {
        int col = 0;
        int row = 0;

        for (TipoComida tipo : TipoComida.values()) {
            VBox tarjeta = crearTarjetaComida(tipo);
            gridComidas.add(tarjeta, col, row);

            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }

    private VBox crearTarjetaComida(TipoComida tipo) {
        VBox tarjeta = new VBox(10);
        tarjeta.setPadding(new Insets(15));
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPrefWidth(200);

        try {
            // Intentar cargar la imagen
            String imagePath = "images/comidas/" + tipo.name().toLowerCase() + ".png";
            Image imagen = ResourceManager.loadImage(imagePath);

            if (imagen != null) {
                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                tarjeta.getChildren().add(imageView);
            } else {
                // Crear un placeholder si no hay imagen
                Rectangle placeholder = new Rectangle(80, 80);
                placeholder.setFill(Color.LIGHTGRAY);
                placeholder.setStroke(Color.GRAY);

                // Agregar la primera letra del tipo como texto
                Label placeholderText = new Label(tipo.getNombre().substring(0, 1));
                placeholderText.setStyle("-fx-font-size: 36px; -fx-text-fill: #666;");

                StackPane placeholderStack = new StackPane(placeholder, placeholderText);
                tarjeta.getChildren().add(placeholderStack);
            }

            // Nombre de la comida
            Label nombreLabel = new Label(tipo.getNombre());
            nombreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;-fx-text-fill: black;");


            // Instrucción
            Label dragLabel = new Label("Arrastra para alimentar");
            dragLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666; -fx-font-style: italic;-fx-text-fill: black;");

            tarjeta.getChildren().addAll(nombreLabel, dragLabel);

        } catch (Exception e) {
            System.err.println("Error al crear tarjeta para " + tipo.getNombre() + ": " + e.getMessage());
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
            content.put(DataFormats.COMIDA_FORMAT, tipo);
            db.setContent(content);

            // Crear imagen de vista previa para el drag
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            db.setDragView(tarjeta.snapshot(sp, null), event.getX(), event.getY());

            event.consume();
        });

        return tarjeta;
    }
}