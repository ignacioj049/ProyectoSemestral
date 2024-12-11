package views.components;

import controllers.ZooController;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public abstract class PanelDinamico extends VBox {
    protected ZooController controller;
    protected MapaZooComponent mapaZoo;

    public PanelDinamico(ZooController controller, MapaZooComponent mapaZoo) {
        this.controller = controller;
        this.mapaZoo = mapaZoo;
        this.setSpacing(10);
        this.setStyle("-fx-padding: 15; -fx-background-color: rgba(255, 255, 255, 0.9);");
    }

    protected Label crearTitulo(String texto) {
        Label titulo = new Label(texto);
        titulo.setFont(new Font("Arial", 20));
        titulo.setStyle("-fx-font-weight: bold;");
        return titulo;
    }

    public abstract void actualizar();
}