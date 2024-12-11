package views.components;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import enums.TipoPanelLateral;

public class PanelLateral extends StackPane {
    private ZooController controller;
    private MapaZooComponent mapaZoo;

    // Paneles
    private PanelCreacionHabitat panelHabitat;
    private PanelGestionAnimales panelAnimales;
    private PanelGestionComida panelComida;
    private PanelEstadoAnimales panelEstado;
    private PanelAlertas panelAlertas;

    // Menú de botones
    private VBox menuBotones;

    public PanelLateral(ZooController controller, MapaZooComponent mapaZoo) {
        this.controller = controller;
        this.mapaZoo = mapaZoo;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Inicializar paneles
        panelHabitat = new PanelCreacionHabitat(controller, mapaZoo);
        panelAnimales = new PanelGestionAnimales(controller, mapaZoo);
        panelComida = new PanelGestionComida(controller, mapaZoo);
        panelEstado = new PanelEstadoAnimales(controller);
        panelAlertas = new PanelAlertas(controller);

        // Ocultar todos los paneles inicialmente
        panelHabitat.setVisible(false);
        panelAnimales.setVisible(false);
        panelComida.setVisible(false);
        panelEstado.setVisible(false);
        panelAlertas.setVisible(false);

        // Crear menú de botones
        crearMenuBotones();

        // Agregar todos los componentes
        getChildren().addAll(
                menuBotones,
                panelHabitat,
                panelAnimales,
                panelComida,
                panelEstado,
                panelAlertas
        );
    }

    private void crearMenuBotones() {
        menuBotones = new VBox(10);
        menuBotones.setPadding(new Insets(10));
        menuBotones.setAlignment(Pos.CENTER_RIGHT);
        menuBotones.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");

        // Botón Crear Hábitat
        Button btnHabitat = crearBotonMenu("Crear Hábitat", TipoPanelLateral.CREAR_HABITAT);

        // Botón Gestionar Animales
        Button btnAnimales = crearBotonMenu("Gestionar Animales", TipoPanelLateral.GESTIONAR_ANIMALES);

        // Botón Gestionar Comida
        Button btnComida = crearBotonMenu("Gestionar Comida", TipoPanelLateral.GESTIONAR_COMIDA);

        // Botón Estado Animales
        Button btnEstado = crearBotonMenu("Estado Animales", TipoPanelLateral.ESTADO_ANIMALES);

        // Botón Alertas
        Button btnAlertas = crearBotonMenu("Alertas", TipoPanelLateral.ALERTAS);

        menuBotones.getChildren().addAll(
                btnHabitat,
                btnAnimales,
                btnComida,
                btnEstado,
                btnAlertas
        );
    }

    private Button crearBotonMenu(String texto, TipoPanelLateral tipo) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Efectos hover
        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-background-color: #45a049; -fx-text-fill: white;"));
        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));

        // Acción del botón
        btn.setOnAction(e -> mostrarPanel(tipo));

        return btn;
    }

    public void mostrarPanel(TipoPanelLateral tipo) {
        // Ocultar todos los paneles
        panelHabitat.setVisible(false);
        panelAnimales.setVisible(false);
        panelComida.setVisible(false);
        panelEstado.setVisible(false);
        panelAlertas.setVisible(false);

        // Mostrar el panel seleccionado
        switch (tipo) {
            case CREAR_HABITAT -> panelHabitat.setVisible(true);
            case GESTIONAR_ANIMALES -> panelAnimales.setVisible(true);
            case GESTIONAR_COMIDA -> panelComida.setVisible(true);
            case ESTADO_ANIMALES -> panelEstado.setVisible(true);
            case ALERTAS -> {
                panelAlertas.setVisible(true);
                panelAlertas.actualizarAlertas();
            }
        }
    }

    public void actualizarPaneles() {
        if (panelAlertas.isVisible()) {
            panelAlertas.actualizarAlertas();
        }
        mapaZoo.refrescarMapa();
    }
}