package views;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import views.components.*;

public class PantallaPrincipal extends BorderPane {
    private ZooController controller;
    private MediaView mediaView;
    private MapaZooComponent mapaZoo;
    private PanelLateralDinamico panelLateral;

    public PantallaPrincipal(ZooController controller) {
        this.controller = controller;
        inicializarComponentes();
        aplicarEstilos();
    }

    private void inicializarComponentes() {
        // Configurar el contenedor principal del mapa (lado izquierdo)
        StackPane contenedorMapa = new StackPane();
        contenedorMapa.setStyle("-fx-background-color: #1a1a1a;"); // Fondo oscuro para el video

        // Configurar MediaView para el video de fondo
        mediaView = new MediaView();
        mediaView.setFitWidth(800);
        mediaView.setFitHeight(720);
        mediaView.setPreserveRatio(false);

        // Crear y configurar el mapa
        mapaZoo = new MapaZooComponent(controller);
        mapaZoo.setPrefWidth(800);
        mapaZoo.setPrefHeight(720);
        mapaZoo.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);"); // Fondo semi-transparente

        // Agregar video y mapa al contenedor
        contenedorMapa.getChildren().addAll(mediaView, mapaZoo);

        // Crear panel lateral dinámico
        panelLateral = new PanelLateralDinamico(controller, mapaZoo);
        panelLateral.setPrefWidth(480);

        // Configurar el layout principal
        setLeft(contenedorMapa);
        setRight(panelLateral);

        // Agregar padding general
        setPadding(new Insets(10));

        // Agregar bordes y sombras
        setBorder(new Border(new BorderStroke(
                Color.rgb(200, 200, 200),
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                BorderWidths.DEFAULT
        )));
    }

    private void aplicarEstilos() {
        setStyle("-fx-background-color: white;");

        // Agregar efecto de sombra al contenedor del mapa
        mediaView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");

        // Estilo para el panel lateral
        panelLateral.setStyle(
                "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, -5, 0);"
        );
    }

    public void setBackgroundVideo(Media media) {
        if (media != null && mediaView != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setMute(true);
            mediaPlayer.setVolume(0);
            mediaPlayer.play();

            // Ajustar el tamaño del video cuando cambie el tamaño de la ventana
            mediaView.fitWidthProperty().bind(mapaZoo.widthProperty());
            mediaView.fitHeightProperty().bind(mapaZoo.heightProperty());
        }
    }
    public void setContenidoCentral(Node contenido) {
        // Asegurarse de que el mediaView esté en el fondo
        if (mediaView != null) {
            mediaView.toBack();
        }

        // Establecer el nuevo contenido en el centro
        setCenter(contenido);

        // Aplicar estilos si es necesario
        contenido.setStyle("-fx-background-color: transparent;");
    }
}