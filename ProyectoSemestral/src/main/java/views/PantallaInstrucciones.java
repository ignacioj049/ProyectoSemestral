package views;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PantallaInstrucciones {
    private Stage stage;

    public PantallaInstrucciones(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        // Crear el título
        Text titulo = new Text("Instrucciones");
        titulo.setFont(new Font("Arial", 30));
        titulo.setFill(Color.WHITE);

        // Crear las instrucciones
        Text instrucciones = new Text(
                "Bienvenido al Simulador de Zoo! Aquí podrás crear y gestionar tu propio zoológico virtual. Sigue estas instrucciones para comenzar.\n\n" +
                        "1. Crear un Hábitat\n" +
                        "Paso 1: Selecciona la opción 'Crear Hábitat' en el menú principal.\n" +
                        "Paso 2: Elige el tipo de hábitat que deseas construir (selva, desierto, etc.).\n" +
                        "Paso 3: Arrastra y suelta elementos del hábitat en el área de diseño.\n" +
                        "Paso 4: Haz clic en 'Guardar' para finalizar la creación del hábitat.\n\n" +
                        "2. Agregar Animales\n" +
                        "Paso 1: Ve a la sección 'Gestionar Animales'.\n" +
                        "Paso 2: Selecciona un animal de la lista disponible.\n" +
                        "Paso 3: Coloca el animal en el hábitat que has creado.\n" +
                        "Nota: Asegúrate de que el hábitat sea adecuado para el animal seleccionado.\n\n" +
                        "3. Gestionar Comida\n" +
                        "Paso 1: Accede a la opción 'Gestionar Comida'.\n" +
                        "Paso 2: Elige la cantidad de comida que deseas añadir a cada hábitat.\n" +
                        "Paso 3: Haz clic en 'Añadir' para colocar la comida en el hábitat correspondiente.\n\n" +
                        "4. Ver Estado de Animales\n" +
                        "Paso 1: Dirígete a la sección 'Ver Estado de Animales'.\n" +
                        "Paso 2: Revisa la información sobre la salud y felicidad de cada animal.\n" +
                        "Paso 3: Presta atención a las alertas que indican si un animal necesita atención.\n\n" +
                        "5. Recibir Alertas\n" +
                        "Nota: Esté atento a la sección de alertas. Estas alertas te informarán sobre situaciones críticas, como la falta de comida o condiciones inadecuadas en los hábitats.\n\n" +
                        "6. Mover Animales\n" +
                        "Paso 1: Selecciona la opción 'Mover Animales'.\n" +
                        "Paso 2: Elige el animal que deseas mover y el nuevo hábitat.\n" +
                        "Paso 3: Confirma el movimiento.\n\n" +
                        "7. Finalizar\n" +
                        "Cuando hayas terminado de gestionar tu zoológico, puedes salir del simulador haciendo clic en 'Salir' en el menú principal."
        );
        instrucciones.setFont(new Font("Arial", 14));
        instrucciones.setFill(Color.WHITE);
        instrucciones.setWrappingWidth(800); // Ajustar el ancho del texto

        // Crear el botón de volver
        Button btnVolver = new Button("Volver");
        btnVolver.setFont(new Font("Arial", 20));
        btnVolver.setTextFill(Color.WHITE);
        btnVolver.setStyle("-fx-background-color: #4CAF50;");
        btnVolver.setOnAction(event -> {
            ZooSimulator zooSimulator = new ZooSimulator();
            zooSimulator.start(stage);
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titulo, instrucciones, btnVolver);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #000000;"); // Fondo negro para contraste
        root.getChildren().add(layout);

        Scene scene = new Scene(root, 996, 755);
        stage.setScene(scene);
        stage.show();
    }
}
