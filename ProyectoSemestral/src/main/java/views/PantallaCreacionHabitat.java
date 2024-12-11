package views;

import controllers.ZooController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import enums.TipoHabitat;

public class PantallaCreacionHabitat extends Stage {
    private ZooController controller;
    private TextField nombreHabitat;
    private ComboBox<TipoHabitat> tipoHabitat;
    private Spinner<Integer> capacidad;

    public PantallaCreacionHabitat(ZooController controller) {
        this.controller = controller;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("Crear Nuevo Hábitat");

        VBox contenedor = new VBox(20);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: white;");

        // Título
        Label titulo = new Label("Crear Nuevo Hábitat");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Formulario
        GridPane formulario = crearFormulario();

        // Botones
        HBox botonesContainer = crearBotones();

        contenedor.getChildren().addAll(titulo, formulario, botonesContainer);

        Scene scene = new Scene(contenedor, 400, 300);
        setScene(scene);
    }

    private GridPane crearFormulario() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Nombre del hábitat
        Label lblNombre = new Label("Nombre:");
        nombreHabitat = new TextField();
        nombreHabitat.setPromptText("Ingrese el nombre del hábitat");

        // Tipo de hábitat
        Label lblTipo = new Label("Tipo:");
        tipoHabitat = new ComboBox<>();
        tipoHabitat.getItems().addAll(TipoHabitat.values());
        tipoHabitat.setPromptText("Seleccione el tipo de hábitat");

        // Capacidad
        Label lblCapacidad = new Label("Capacidad:");
        capacidad = new Spinner<>(1, 10, 5);
        capacidad.setEditable(true);

        // Agregar elementos al grid
        grid.add(lblNombre, 0, 0);
        grid.add(nombreHabitat, 1, 0);
        grid.add(lblTipo, 0, 1);
        grid.add(tipoHabitat, 1, 1);
        grid.add(lblCapacidad, 0, 2);
        grid.add(capacidad, 1, 2);

        return grid;
    }

    private HBox crearBotones() {
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);

        Button btnCrear = new Button("Crear");
        btnCrear.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnCrear.setOnAction(e -> crearHabitat());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        btnCancelar.setOnAction(e -> close());

        hbox.getChildren().addAll(btnCrear, btnCancelar);
        return hbox;
    }

    private void crearHabitat() {
        String nombre = nombreHabitat.getText().trim();
        TipoHabitat tipo = tipoHabitat.getValue();
        int capacidadValue = capacidad.getValue();

        if (nombre.isEmpty() || tipo == null) {
            mostrarError("Por favor, complete todos los campos");
            return;
        }

        try {
            controller.crearHabitat(nombre, tipo, capacidadValue);
            mostrarExito("Hábitat creado exitosamente");
            close();
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}