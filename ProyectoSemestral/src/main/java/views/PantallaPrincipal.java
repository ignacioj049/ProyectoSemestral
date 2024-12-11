package views;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import views.components.*;

public class PantallaPrincipalNueva extends BorderPane {
    private ZooController controller;
    private MapaZooComponent mapaZoo;
    private VBox contenidoPrincipal;
    private HBox barraLateral;

    public PantallaPrincipalNueva(ZooController controller) {
        this.controller = controller;
        this.mapaZoo = new MapaZooComponent(controller);
        inicializarComponentes();
        mostrarMenuPrincipal();
    }

    private void inicializarComponentes() {
        // Configurar barra lateral
        barraLateral = new HBox(10);
        barraLateral.setPadding(new Insets(10));
        barraLateral.setStyle("-fx-background-color: #f0f0f0;");
        barraLateral.setAlignment(Pos.CENTER);
        setTop(barraLateral);

        // Configurar contenido principal
        contenidoPrincipal = new VBox();
        contenidoPrincipal.setAlignment(Pos.CENTER);
        contenidoPrincipal.setPadding(new Insets(20));
        contenidoPrincipal.setSpacing(10);
        VBox.setVgrow(contenidoPrincipal, Priority.ALWAYS);

        // Crear botones de navegación
        Button btnMenu = createMenuButton("Menú Principal", this::mostrarMenuPrincipal);
        Button btnMapa = createMenuButton("Ver Mapa", this::mostrarMapa);

        barraLateral.getChildren().addAll(btnMenu, btnMapa);

        // Configurar el layout
        setCenter(contenidoPrincipal);
    }

    private void mostrarMenuPrincipal() {
        contenidoPrincipal.getChildren().clear();
        VBox menuBotones = new VBox(10);
        menuBotones.setAlignment(Pos.CENTER);
        menuBotones.setSpacing(20);
        menuBotones.setPadding(new Insets(50));

        menuBotones.getChildren().addAll(
                createMenuButton("Crear Hábitat", () -> mostrarPanelCrearHabitat()),
                createMenuButton("Gestionar Animales", () -> mostrarPanelGestionAnimales()),
                createMenuButton("Ver Estado", () -> mostrarPanelEstado())
        );

        contenidoPrincipal.getChildren().add(menuBotones);
    }

    private Button createMenuButton(String texto, Runnable accion) {
        Button boton = new Button(texto);
        boton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-padding: 10 20; " +
                "-fx-background-radius: 5;");
        boton.setPrefWidth(200);

        boton.setOnMouseEntered(e ->
                boton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; " +
                        "-fx-font-size: 14px; -fx-padding: 10 20; " +
                        "-fx-background-radius: 5;"));

        boton.setOnMouseExited(e ->
                boton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                        "-fx-font-size: 14px; -fx-padding: 10 20; " +
                        "-fx-background-radius: 5;"));

        boton.setOnAction(e -> accion.run());
        return boton;
    }

    private void mostrarMapa() {
        contenidoPrincipal.getChildren().clear();
        mapaZoo.refrescarMapa(); // Cambiado de actualizarMapa a refrescarMapa
        contenidoPrincipal.getChildren().add(mapaZoo);
    }

    private void mostrarPanelCrearHabitat() {
        contenidoPrincipal.getChildren().clear();
        PanelCreacionHabitat panelCreacion = new PanelCreacionHabitat(controller);
        contenidoPrincipal.getChildren().add(panelCreacion);
    }

    private void mostrarPanelGestionAnimales() {
        contenidoPrincipal.getChildren().clear();
        PanelGestionAnimales panelGestion = new PanelGestionAnimales(controller, mapaZoo);
        contenidoPrincipal.getChildren().add(panelGestion);
    }

    private void mostrarPanelEstado() {
        contenidoPrincipal.getChildren().clear();
        PanelEstadoAnimales panelEstado = new PanelEstadoAnimales(controller);
        contenidoPrincipal.getChildren().add(panelEstado);
    }
}