package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.animals.Animal;
import models.habitats.Habitat;

public class PanelEstadoAnimales extends VBox {
    private ZooController controller;
    private VBox contenedorEstados;

    public PanelEstadoAnimales(ZooController controller) {
        this.controller = controller;
        inicializarComponentes();
        actualizarEstados();
    }

    private void inicializarComponentes() {
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: white;");

        Label titulo = new Label("Estado de los Animales");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        contenedorEstados = new VBox(10);
        ScrollPane scroll = new ScrollPane(contenedorEstados);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent;");

        getChildren().addAll(titulo, scroll);
    }

    public void actualizarEstados() {
        contenedorEstados.getChildren().clear();

        for (Habitat habitat : controller.getHabitats().values()) {
            VBox habitatBox = crearHabitatBox(habitat);
            contenedorEstados.getChildren().add(habitatBox);
        }
    }

    private VBox crearHabitatBox(Habitat habitat) {
        VBox box = new VBox(5);
        box.setStyle(
                "-fx-background-color: #f8f9fa;" +
                        "-fx-padding: 10;" +
                        "-fx-border-color: #dee2e6;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
        );

        // Información del hábitat
        Label habitatInfo = new Label(String.format(
                "Hábitat: %s (%d/%d animales) - Limpieza: %.0f%%",
                habitat.getId(),
                habitat.getAnimales().size(),
                habitat.getCapacidad(),
                habitat.getNivelLimpieza()
        ));
        habitatInfo.setStyle("-fx-font-weight: bold;");

        // Lista de animales
        VBox animalesBox = new VBox(5);
        for (Animal animal : habitat.getAnimales().values()) {
            HBox animalBox = crearAnimalBox(animal);
            animalesBox.getChildren().add(animalBox);
        }

        box.getChildren().addAll(habitatInfo, animalesBox);
        return box;
    }

    private HBox crearAnimalBox(Animal animal) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(5));
        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #e9ecef;" +
                        "-fx-border-radius: 3;" +
                        "-fx-background-radius: 3;"
        );

        // Información básica del animal
        Label nombreLabel = new Label(animal.getNombre());
        nombreLabel.setMinWidth(100);

        // Barras de estado
        ProgressBar saludBar = crearBarraEstado(animal.getNivelSalud(), "Salud");
        ProgressBar hambreBar = crearBarraEstado(animal.getNivelHambre(), "Hambre");
        ProgressBar felicidadBar = crearBarraEstado(animal.getNivelFelicidad(), "Felicidad");

        box.getChildren().addAll(
                nombreLabel,
                new VBox(2, new Label("Salud"), saludBar),
                new VBox(2, new Label("Hambre"), hambreBar),
                new VBox(2, new Label("Felicidad"), felicidadBar)
        );

        return box;
    }

    private ProgressBar crearBarraEstado(double valor, String tipo) {
        ProgressBar barra = new ProgressBar(valor / 100);
        barra.setMinWidth(80);
        barra.setMaxWidth(80);
        barra.setStyle(getEstiloBarraEstado(valor, tipo));
        return barra;
    }

    private String getEstiloBarraEstado(double valor, String tipo) {
        String color;
        if (valor < 30) {
            color = "#dc3545"; // Rojo
        } else if (valor < 70) {
            color = "#ffc107"; // Amarillo
        } else {
            color = "#28a745"; // Verde
        }

        return String.format(
                "-fx-accent: %s;" +
                        "-fx-control-inner-background: #f8f9fa;",
                color
        );
    }
}