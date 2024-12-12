package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.animals.Animal;
import models.habitats.Habitat;

import java.util.ArrayList;
import java.util.List;

public class PanelAlertas extends VBox {
    private ZooController controller;
    private VBox contenedorAlertas;

    public PanelAlertas(ZooController controller) {
        this.controller = controller;
        inicializarComponentes();
        actualizarAlertas();
    }

    private void inicializarComponentes() {
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: white;");

        Label titulo = new Label("Alertas del Zoo");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill: black;");

        contenedorAlertas = new VBox(10);
        ScrollPane scroll = new ScrollPane(contenedorAlertas);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent;");

        getChildren().addAll(titulo, scroll);
    }

    public void actualizarAlertas() {
        contenedorAlertas.getChildren().clear();
        List<VBox> alertas = new ArrayList<>();


        // Revisar estado de hábitats
        for (Habitat habitat : controller.getHabitats().values()) {
            // Alerta de capacidad
            if (!habitat.tieneEspacioDisponible()) {
                alertas.add(crearAlertaCapacidad(habitat));
            }

            // Revisar estado de animales
            for (Animal animal : habitat.getAnimales().values()) {
                if (animal.getNivelHambre() < 30) {
                    alertas.add(crearAlertaHambre(habitat, animal));
                }
                if (animal.getNivelSalud() < 50) {
                    alertas.add(crearAlertaSalud(habitat, animal));
                }
            }
        }

        if (alertas.isEmpty()) {
            Label noAlertas = new Label("No hay alertas pendientes");
            noAlertas.setStyle("-fx-text-fill: #28a745;");
            contenedorAlertas.getChildren().add(noAlertas);
        } else {
            contenedorAlertas.getChildren().addAll(alertas);
        }
    }


    private VBox crearAlertaCapacidad(Habitat habitat) {
        return crearBaseAlerta("¡Hábitat Lleno!",
                String.format("El hábitat %s está al máximo de su capacidad (%d/%d)",
                        habitat.getId(), habitat.getAnimales().size(), habitat.getCapacidad()));
    }

    private VBox crearAlertaHambre(Habitat habitat, Animal animal) {
        VBox alerta = crearBaseAlerta("¡Animal Hambriento!",
                String.format("%s en %s necesita comida (Nivel: %.0f%%)",
                        animal.getNombre(), habitat.getId(), animal.getNivelHambre()));

        Button btnAlimentar = new Button("Alimentar");
        btnAlimentar.setOnAction(e -> alimentarAnimal(habitat.getId(), animal.getNombre()));

        alerta.getChildren().add(btnAlimentar);
        return alerta;
    }

    private VBox crearAlertaSalud(Habitat habitat, Animal animal) {
        VBox alerta = crearBaseAlerta("¡Animal Enfermo!",
                String.format("%s en %s necesita atención médica (Salud: %.0f%%)",
                        animal.getNombre(), habitat.getId(), animal.getNivelSalud()));

        Button btnAtender = new Button("Atender");
        btnAtender.setOnAction(e -> atenderAnimal(habitat.getId(), animal.getNombre()));

        alerta.getChildren().add(btnAtender);
        return alerta;
    }

    private VBox crearBaseAlerta(String titulo, String mensaje) {
        VBox alerta = new VBox(5);
        alerta.setPadding(new Insets(10));
        alerta.setStyle(
                "-fx-background-color: #fff3cd;" +
                        "-fx-border-color: #ffeeba;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
        );

        Label tituloLabel = new Label(titulo);
        tituloLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #856404;");

        Label mensajeLabel = new Label(mensaje);
        mensajeLabel.setStyle("-fx-text-fill: #856404;");

        alerta.getChildren().addAll(tituloLabel, mensajeLabel);
        return alerta;
    }



    private void alimentarAnimal(String habitatId, String nombreAnimal) {
        try {
            controller.alimentarAnimal(habitatId, nombreAnimal, 1.0);
            actualizarAlertas();
        } catch (Exception e) {
            mostrarError("Error al alimentar animal", e.getMessage());
        }
    }

    private void atenderAnimal(String habitatId, String nombreAnimal) {
        try {
            controller.atenderAnimal(habitatId, nombreAnimal);
            actualizarAlertas();
        } catch (Exception e) {
            mostrarError("Error al atender animal", e.getMessage());
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}