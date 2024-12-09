package views;
import controllers.ZooController;
import models.habitats.Habitat;

import models.animals.Animal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class PantallaVerZoo {
    private Stage stage;
    private ZooController controller;

    public PantallaVerZoo(Stage stage, ZooController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void mostrar() {
        FlowPane layout = new FlowPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        for (Habitat habitat : controller.getZoo().getHabitats()) {
            Rectangle shape = new Rectangle(200, 100);
            shape.setFill(getColorForHabitat(habitat.getTipo()));

            Label nameLabel = new Label(habitat.getNombre());
            nameLabel.setFont(new Font("Arial", 14));
            nameLabel.setTextFill(Color.WHITE);

            Button btnVer = new Button("Ver");
            btnVer.setFont(new Font("Arial", 12));
            btnVer.setStyle("-fx-background-color: #4CAF50;");

            btnVer.setOnAction(event -> {
                mostrarInformacionHabitat(habitat);
            });

            StackPane habitatPane = new StackPane();
            habitatPane.getChildren().addAll(shape, nameLabel, btnVer);
            StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);
            StackPane.setAlignment(btnVer, Pos.CENTER);
            layout.getChildren().add(habitatPane);
        }

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 16));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #f44336;");
        btnVolver.setOnAction(event -> {
            PantallaInicio pantallaInicio = new PantallaInicio(stage, controller);
            pantallaInicio.mostrar();
        });

        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(scrollPane, btnVolver);

        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private Rectangle createHabitatShape() {
        return new Rectangle(200, 100);
    }

    private Color getColorForHabitat(String tipo) {
        switch (tipo) {
            case "Selva Tropical":
                return Color.web("#2E8B57");
            case "Sabana":
                return Color.web("#D2B48C");
            case "Bosque Templado":
                return Color.web("#6B8E23");
            case "Desierto":
                return Color.web("#C19A6B");
            case "Región Ártica":
                return Color.web("#D3D3D3");
            case "Océano y Acuarios":
                return Color.web("#40E0D0");
            default:
                return Color.GRAY;
        }
    }

    private void mostrarInformacionHabitat(Habitat habitat) {
        Stage infoStage = new Stage();
        VBox infoLayout = new VBox(10);
        infoLayout.setAlignment(Pos.CENTER);
        infoLayout.setStyle("-fx-background-color: #f0f0f0;");

        Label infoLabel = new Label();
        infoLabel.setFont(new Font("Arial", 14));
        infoLabel.setTextFill(Color.BLACK);

        StringBuilder info = new StringBuilder();
        info.append("Nombre del Hábitat: ").append(habitat.getNombre()).append("\n");
        info.append("Tipo de Hábitat: ").append(habitat.getTipo()).append("\n");
        info.append("Cantidad de Animales: ").append(habitat.getAnimales().size()).append("\n");
        info.append("Animales:\n");
        for (Animal animal : habitat.getAnimales()) {
            info.append(" - ").append(animal.getNombre()).append(" (").append(animal.getTipo()).append(")\n");
        }

        infoLabel.setText(info.toString());

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(new Font("Arial", 14));
        btnCerrar.setStyle("-fx-background-color: #f44336;");
        btnCerrar.setOnAction(event -> infoStage.close());

        infoLayout.getChildren().addAll(infoLabel, btnCerrar);

        Scene infoScene = new Scene(infoLayout, 400, 300);
        infoStage.setScene(infoScene);
        infoStage.setTitle("Información del Hábitat");
        infoStage.show();
    }
}
