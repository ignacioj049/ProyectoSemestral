package views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import models.habitats.Habitat;

public class PantallaVerZoo extends GridPane {

    public PantallaVerZoo() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        // Hacer que las columnas y filas se expandan proporcionalmente
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setFillWidth(true);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);

        // Agregar las restricciones para 4 columnas
        for (int i = 0; i < 4; i++) {
            this.getColumnConstraints().add(columnConstraints);
        }

        // Agregar las restricciones para 3 filas
        for (int i = 0; i < 3; i++) {
            this.getRowConstraints().add(rowConstraints);
        }
    }

    public void agregarHabitat(Habitat habitat, int fila, int columna) {
        this.add(crearVistaHabitat(habitat), columna, fila);
    }

    private Node crearVistaHabitat(Habitat habitat) {
        ImageView imagenHabitat = new ImageView();

        // Cargar la imagen según el tipo de hábitat
        String rutaImagen = "/images/habitats/" + habitat.getTipo().toString().toLowerCase() + ".png";
        try {
            Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            imagenHabitat.setImage(imagen);

            // Hacer que la imagen se ajuste al contenedor
            imagenHabitat.setFitWidth(300);  // Ajusta este valor según necesites
            imagenHabitat.setFitHeight(300); // Ajusta este valor según necesites
            imagenHabitat.setPreserveRatio(true); // Mantener la proporción de la imagen

            // Hacer que la imagen se estire para llenar el espacio
            imagenHabitat.fitWidthProperty().bind(widthProperty().divide(4).subtract(20)); // 4 columnas
            imagenHabitat.fitHeightProperty().bind(heightProperty().divide(3).subtract(20)); // 3 filas

        } catch (Exception e) {
            System.err.println("Error al cargar la imagen del hábitat: " + rutaImagen);
            e.printStackTrace();
        }

        // Crear el contenedor para el hábitat
        StackPane contenedorHabitat = new StackPane(imagenHabitat);
        contenedorHabitat.setPadding(new Insets(10));

        // Hacer que el contenedor se expanda para llenar el espacio disponible
        contenedorHabitat.setMaxWidth(Double.MAX_VALUE);
        contenedorHabitat.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillWidth(contenedorHabitat, true);
        GridPane.setFillHeight(contenedorHabitat, true);

        // Agregar efectos visuales
        contenedorHabitat.setStyle("-fx-background-color: transparent;");

        return contenedorHabitat;
    }}