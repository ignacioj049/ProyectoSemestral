package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.habitats.Habitat;
import models.animals.Animal;
import enums.TipoAnimal;
import enums.TipoHabitat;
import javafx.scene.input.*;
import utils.DataFormats;
import java.util.HashMap;
import java.util.Map;

public class MapaZooComponent extends GridPane {
    private ZooController controller;
    private Map<String, CeldaMapa> celdasOcupadas;
    private static final int FILAS = 4;
    private static final int COLUMNAS = 4;

    public MapaZooComponent(ZooController controller) {
        this.controller = controller;
        this.celdasOcupadas = new HashMap<>();
        inicializarMapa();
    }

    private void inicializarMapa() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));

        // Crear la cuadrícula de celdas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                CeldaMapa celda = new CeldaMapa(i, j);
                add(celda, j, i);
                configurarCelda(celda);
            }
        }

        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
    }

    private void configurarCelda(CeldaMapa celda) {
        // Configurar el tamaño
        celda.setPrefSize(180, 180);
        celda.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        // Estilo visual
        celda.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.5);" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;"
        );

        // Configurar drag and drop para hábitats
        celda.setOnDragOver(event -> {
            if (event.getDragboard().hasContent(DataFormats.HABITAT_FORMAT) ||
                    event.getDragboard().hasContent(DataFormats.ANIMAL_FORMAT)) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        celda.setOnDragEntered(event -> {
            if (event.getDragboard().hasContent(DataFormats.HABITAT_FORMAT) ||
                    event.getDragboard().hasContent(DataFormats.ANIMAL_FORMAT)) {
                celda.setStyle(
                        "-fx-background-color: rgba(200, 255, 200, 0.5);" +
                                "-fx-border-color: #4CAF50;" +
                                "-fx-border-width: 2;" +
                                "-fx-border-radius: 5;"
                );
            }
            event.consume();
        });

        celda.setOnDragExited(event -> {
            celda.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.5);" +
                            "-fx-border-color: #cccccc;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 5;"
            );
            event.consume();
        });

        celda.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasContent(DataFormats.HABITAT_FORMAT)) {
                TipoHabitat tipo = (TipoHabitat) db.getContent(DataFormats.HABITAT_FORMAT);
                success = manejarDropHabitat(celda, tipo);
            } else if (db.hasContent(DataFormats.ANIMAL_FORMAT)) {
                TipoAnimal tipo = (TipoAnimal) db.getContent(DataFormats.ANIMAL_FORMAT);
                success = manejarDropAnimal(celda, tipo);
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    private boolean manejarDropHabitat(CeldaMapa celda, TipoHabitat tipo) {
        try {
            String id = String.format("habitat_%d_%d", celda.getFila(), celda.getColumna());
            controller.crearHabitat(id, tipo, 5); // Capacidad por defecto
            celdasOcupadas.put(id, celda);
            celda.setHabitat(id, tipo);
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear hábitat: " + e.getMessage());
            return false;
        }
    }

    private boolean manejarDropAnimal(CeldaMapa celda, TipoAnimal tipo) {
        try {
            String habitatId = celda.getHabitatId();
            if (habitatId == null) {
                System.err.println("No hay hábitat en esta celda");
                return false;
            }

            String nombreAnimal = tipo.name().toLowerCase() + "_" + System.currentTimeMillis();
            controller.crearAnimal(nombreAnimal, tipo, habitatId);
            celda.agregarAnimal(nombreAnimal, tipo);
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear animal: " + e.getMessage());
            return false;
        }
    }

    public void refrescarMapa() {
        // Actualizar el estado visual de todas las celdas
        celdasOcupadas.forEach((habitatId, celda) -> {
            Habitat habitat = controller.obtenerHabitat(habitatId);
            if (habitat != null) {
                celda.actualizarEstado(habitat);
            }
        });
    }
}