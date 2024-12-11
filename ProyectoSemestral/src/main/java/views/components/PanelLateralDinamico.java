package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class PanelLateralDinamico extends VBox {
    private ZooController controller;
    private MapaZooComponent mapaZoo;
    private VBox contenidoDinamico;
    private HBox barraNavegacion;

    // Componentes de las diferentes vistas
    private PanelCreacionHabitat panelHabitat;
    private PanelGestionAnimales panelAnimales;
    private PanelGestionComida panelComida;
    private PanelEstadoAnimales panelEstado;
    private PanelAlertas panelAlertas;

    // Botones de navegación
    private Button botonActivo;

    public PanelLateralDinamico(ZooController controller, MapaZooComponent mapaZoo) {
        this.controller = controller;
        this.mapaZoo = mapaZoo;
        inicializarComponentes();
        aplicarEstilos();
    }

    private void inicializarComponentes() {
        setSpacing(15);
        setPadding(new Insets(15));

        // Crear título
        Label titulo = new Label("Zoo Simulator");
        titulo.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2c3e50;"
        );
        titulo.setAlignment(Pos.CENTER);
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setMaxWidth(Double.MAX_VALUE);

        // Crear barra de navegación
        barraNavegacion = crearBarraNavegacion();

        // Crear contenedor para el contenido dinámico
        contenidoDinamico = new VBox(15);
        contenidoDinamico.setPadding(new Insets(15));
        VBox.setVgrow(contenidoDinamico, Priority.ALWAYS);

        // Scroll pane para el contenido
        ScrollPane scrollPane = new ScrollPane(contenidoDinamico);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-padding: 0;"
        );
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Inicializar todos los paneles
        inicializarPaneles();

        // Agregar componentes al panel lateral
        getChildren().addAll(titulo, barraNavegacion, scrollPane);

        // Mostrar panel de hábitats por defecto
        mostrarPanelHabitats();
    }

    private HBox crearBarraNavegacion() {
        HBox barra = new HBox(10);
        barra.setPadding(new Insets(10));
        barra.setAlignment(Pos.CENTER);
        barra.setStyle(
                "-fx-background-color: #f8f9fa;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0);"
        );

        Button[] botones = {
                crearBotonNavegacion("Hábitats", this::mostrarPanelHabitats),
                crearBotonNavegacion("Animales", this::mostrarPanelAnimales),
                crearBotonNavegacion("Comida", this::mostrarPanelComida),
                crearBotonNavegacion("Estado", this::mostrarPanelEstado),
                crearBotonNavegacion("Alertas", this::mostrarPanelAlertas)
        };

        barra.getChildren().addAll(botones);
        return barra;
    }

    private Button crearBotonNavegacion(String texto, Runnable accion) {
        Button boton = new Button(texto);
        boton.setStyle(getEstiloBotonInactivo());

        boton.setOnMouseEntered(e -> {
            if (boton != botonActivo) {
                boton.setStyle(getEstiloBotonHover());
            }
        });

        boton.setOnMouseExited(e -> {
            if (boton != botonActivo) {
                boton.setStyle(getEstiloBotonInactivo());
            }
        });

        boton.setOnAction(e -> {
            if (botonActivo != null) {
                botonActivo.setStyle(getEstiloBotonInactivo());
            }
            boton.setStyle(getEstiloBotonActivo());
            botonActivo = boton;
            accion.run();
        });

        return boton;
    }

    private String getEstiloBotonInactivo() {
        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #6c757d;" +
                "-fx-padding: 8 15;" +
                "-fx-cursor: hand;" +
                "-fx-background-radius: 5;";
    }

    private String getEstiloBotonHover() {
        return "-fx-background-color: #e9ecef;" +
                "-fx-text-fill: #495057;" +
                "-fx-padding: 8 15;" +
                "-fx-cursor: hand;" +
                "-fx-background-radius: 5;";
    }

    private String getEstiloBotonActivo() {
        return "-fx-background-color: #007bff;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 8 15;" +
                "-fx-cursor: hand;" +
                "-fx-background-radius: 5;";
    }

    private void inicializarPaneles() {
        panelHabitat = new PanelCreacionHabitat(controller, mapaZoo);
        panelAnimales = new PanelGestionAnimales(controller, mapaZoo);
        panelComida = new PanelGestionComida(controller, mapaZoo);
        panelEstado = new PanelEstadoAnimales(controller);
        panelAlertas = new PanelAlertas(controller);
    }

    private void mostrarPanelHabitats() {
        cambiarContenido(panelHabitat);
    }

    private void mostrarPanelAnimales() {
        cambiarContenido(panelAnimales);
    }

    private void mostrarPanelComida() {
        cambiarContenido(panelComida);
    }

    private void mostrarPanelEstado() {
        cambiarContenido(panelEstado);
    }

    private void mostrarPanelAlertas() {
        cambiarContenido(panelAlertas);
    }

    private void cambiarContenido(VBox nuevoContenido) {
        contenidoDinamico.getChildren().clear();
        contenidoDinamico.getChildren().add(nuevoContenido);
    }

    private void aplicarEstilos() {
        setStyle(
                "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, -5, 0);" +
                        "-fx-background-radius: 10;"
        );
    }
}