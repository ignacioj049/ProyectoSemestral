import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ZooSimulator extends Application {
    private ZooController controller;

    @Override
    public void start(Stage primaryStage) {
        Zoo zoo = new Zoo();
        controller = new ZooController(zoo);

        primaryStage.setTitle("Simulador de Zoo");

        Button btnCrearHabitat = new Button("Crear Hábitat");
        btnCrearHabitat.setOnAction(event -> {
            controller.crearHabitat("Sabana", "Tropical");
            System.out.println("Hábitat creado: Sabana");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btnCrearHabitat);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}