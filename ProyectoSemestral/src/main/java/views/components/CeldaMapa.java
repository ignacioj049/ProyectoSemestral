package views.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.habitats.Habitat;
import enums.TipoAnimal;
import enums.TipoHabitat;
import utils.ResourceManager;

import java.util.ArrayList;
import java.util.List;

public class CeldaMapa extends VBox {
    private final int fila;
    private final int columna;
    private String habitatId;
    private List<String> animales;
    private Label infoLabel;
    private ImageView habitatImage;

    public CeldaMapa(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.animales = new ArrayList<>();

        setAlignment(Pos.CENTER);
        setSpacing(5);

        habitatImage = new ImageView();
        habitatImage.setFitWidth(100);
        habitatImage.setFitHeight(100);
        habitatImage.setPreserveRatio(true);

        getChildren().addAll(habitatImage);
    }

    public void setHabitat(String id, TipoHabitat tipo) {
        this.habitatId = id;

        // Cargar imagen del hábitat
        String imagePath = "images/habitats/" + tipo.name().toLowerCase() + ".png";
        Image imagen = ResourceManager.loadImage(imagePath);
        if (imagen != null) {
            habitatImage.setImage(imagen);
        }

        actualizarInfo();
    }

    public void agregarAnimal(String nombre, TipoAnimal tipo) {
        animales.add(nombre);
        actualizarInfo();
    }

    public void actualizarEstado(Habitat habitat) {
        if (habitat != null) {
            animales = new ArrayList<>(habitat.getAnimales().keySet());
            actualizarInfo();
        }
    }

    private void actualizarInfo() {
        if (habitatId != null) {
            StringBuilder info = new StringBuilder();
            info.append("Hábitat: ").append(habitatId).append("\n");
            info.append("Animales: ").append(animales.size());
            infoLabel.setText(info.toString());
        }
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getHabitatId() {
        return habitatId;
    }
}