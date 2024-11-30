import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ZooController {
    private Zoo zoo;

    public ZooController(Zoo zoo) {
        this.zoo = zoo;
    }

    public void crearHabitat(String nombre, String tipo, int capacidad) {
        Habitat habitat = new Habitat(nombre, tipo, capacidad);
        zoo.agregarHabitat(habitat);
        mostrarAlerta("Has creado un nuevo habitat");
    }

    public void agregarAnimalAHabitat(String nombreHabitat, Animal animal) {
        for (Habitat habitat : zoo.getHabitats()) {
            if (habitat.getNombre().equals(nombreHabitat)) {
                habitat.agregarAnimal(animal);
                break;
            }
        }
    }

    public void moverAnimal(String nombreAnimal, String nombreHabitatOrigen, String nombreHabitatDestino) {
        Habitat origen = null;
        Habitat destino = null;
        Animal animal = null;

        for (Habitat habitat : zoo.getHabitats()) {
            if (habitat.getNombre().equals(nombreHabitatOrigen)) {
                origen = habitat;
            }
            if (habitat.getNombre().equals(nombreHabitatDestino)) {
                destino = habitat;
            }
        }

        if (origen != null && destino != null) {
            for (Animal a : origen.getAnimales()) {
                if (a.getNombre().equals(nombreAnimal)) {
                    animal = a;
                    break;
                }
            }
            if (animal != null) {
                zoo.moverAnimal(animal, origen, destino);
            }
        }
    }
    private void mostrarAlerta(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

<<<<<<< HEAD
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
=======
>>>>>>> 38137e9957e64fc4ffa65f033ba08dbb541bbf15
    }

    public Zoo getZoo() {
        return zoo;
    }
}
