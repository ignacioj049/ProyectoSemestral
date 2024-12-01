import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ZooController {
    private Zoo zoo;

    public ZooController(Zoo zoo) {
        this.zoo = zoo;
    }

    public void crearHabitat(String nombre, String tipo, int capacidad) {
        Habitat habitat = new Habitat(nombre, tipo, capacidad);
        zoo.agregarHabitat(habitat);
        mostrarAlerta("Has creado un nuevo hábitat");
    }

    public void agregarAnimalAHabitat(String nombreHabitat, Animal animal) {
        Habitat habitat = zoo.buscarHabitatPorNombre(nombreHabitat);
        if (habitat != null) {
            if (habitat.getAnimales().size() < habitat.getCapacidad()) {
                habitat.agregarAnimal(animal);
                mostrarAlerta("Animal agregado al hábitat " + nombreHabitat);
            } else {
                mostrarAlerta("El hábitat está lleno.");
            }
        } else {
            mostrarAlerta("Hábitat no encontrado: " + nombreHabitat);
        }
    }

    public void moverAnimal(String nombreAnimal, String nombreHabitatOrigen, String nombreHabitatDestino) {
        Habitat origen = zoo.buscarHabitatPorNombre(nombreHabitatOrigen);
        Habitat destino = zoo.buscarHabitatPorNombre(nombreHabitatDestino);
        if (origen != null && destino != null) {
            Animal animal = zoo.buscarAnimalPorNombre(nombreAnimal, origen);
            if (animal != null) {
                if (esHabitatAdecuado(destino, animal.getEspecie())) {
                    if (destino.getAnimales().size() < destino.getCapacidad()) {
                        zoo.moverAnimal(animal, origen, destino);
                        mostrarAlerta("Animal movido de " + nombreHabitatOrigen + " a " + nombreHabitatDestino);
                    } else {
                        mostrarAlerta("El hábitat destino está lleno.");
                    }
                } else {
                    mostrarAlerta("El hábitat destino no es adecuado para el animal.");
                }
            } else {
                mostrarAlerta("Animal no encontrado en el hábitat de origen: " + nombreAnimal);
            }
        } else {
            mostrarAlerta("Hábitat de origen o destino no encontrado.");
        }
    }

    private boolean esHabitatAdecuado(Habitat habitat, String tipoAnimal) {
        switch (habitat.getTipo()) {
            case "Selva Tropical":
                return tipoAnimal.equals("Jaguar") || tipoAnimal.equals("Mono aullador") || tipoAnimal.equals("Perezoso") || tipoAnimal.equals("Tapir") || tipoAnimal.equals("Guacamayo") || tipoAnimal.equals("Tucán") || tipoAnimal.equals("Águila arpía") || tipoAnimal.equals("Boa constrictora") || tipoAnimal.equals("Anaconda") || tipoAnimal.equals("Camaleón") || tipoAnimal.equals("Rana dardo venenosa") || tipoAnimal.equals("Mariposa morpho") || tipoAnimal.equals("Hormiga cortadora de hojas");
            case "Sabana":
                return tipoAnimal.equals("León") || tipoAnimal.equals("Elefante africano") || tipoAnimal.equals("Jirafa") || tipoAnimal.equals("Guepardo") || tipoAnimal.equals("Cebra") || tipoAnimal.equals("Avestruz") || tipoAnimal.equals("Secretary bird") || tipoAnimal.equals("Cocodrilo del Nilo") || tipoAnimal.equals("Víbora bufadora") || tipoAnimal.equals("Escarabajo pelotero");
            case "Bosque Templado":
                return tipoAnimal.equals("Oso pardo") || tipoAnimal.equals("Lobo gris") || tipoAnimal.equals("Ciervo") || tipoAnimal.equals("Zorro rojo") || tipoAnimal.equals("Búho real") || tipoAnimal.equals("Pájaro carpintero") || tipoAnimal.equals("Serpiente rata negra") || tipoAnimal.equals("Escarabajo ciervo");
            case "Desierto":
                return tipoAnimal.equals("Camello") || tipoAnimal.equals("Fenec") || tipoAnimal.equals("Ratón canguro") || tipoAnimal.equals("Águila calva") || tipoAnimal.equals("Buitre") || tipoAnimal.equals("Serpiente cascabel") || tipoAnimal.equals("Lagarto cornudo") || tipoAnimal.equals("Escorpión") || tipoAnimal.equals("Cucaracha del desierto");
            case "Taiga":
                return tipoAnimal.equals("Alce") || tipoAnimal.equals("Lince") || tipoAnimal.equals("Zorro ártico") || tipoAnimal.equals("Búho nival") || tipoAnimal.equals("Ganso salvaje") || tipoAnimal.equals("Culebra de collar");
            case "Tundra":
                return tipoAnimal.equals("Oso polar") || tipoAnimal.equals("Reno") || tipoAnimal.equals("Lobo ártico") || tipoAnimal.equals("Perdiz nival") || tipoAnimal.equals("Charrán ártico") || tipoAnimal.equals("Mosquito ártico");
            case "Arrecife de Coral":
                return tipoAnimal.equals("Pez payaso") || tipoAnimal.equals("Tiburón de arrecife") || tipoAnimal.equals("Mero") || tipoAnimal.equals("Coral") || tipoAnimal.equals("Estrella de mar") || tipoAnimal.equals("Pulpo");
            case "Río":
                return tipoAnimal.equals("Bagre") || tipoAnimal.equals("Piraña") || tipoAnimal.equals("Trucha") || tipoAnimal.equals("Tortuga de río") || tipoAnimal.equals("Caimán") || tipoAnimal.equals("Rana toro");
            case "Pantano":
                return tipoAnimal.equals("Nutria") || tipoAnimal.equals("Castor") || tipoAnimal.equals("Garza real") || tipoAnimal.equals("Ibis") || tipoAnimal.equals("Cocodrilo americano") || tipoAnimal.equals("Tortuga mordedora") || tipoAnimal.equals("Salamandra gigante") || tipoAnimal.equals("Libélula") || tipoAnimal.equals("Caracol acuático");
            case "Océano Abierto":
                return tipoAnimal.equals("Delfín") || tipoAnimal.equals("Ballena jorobada") || tipoAnimal.equals("Orca") || tipoAnimal.equals("Tiburón blanco") || tipoAnimal.equals("Pez espada") || tipoAnimal.equals("Medusa") || tipoAnimal.equals("Calamar gigante");
            case "Glaciares y Hielo Ártico":
                return tipoAnimal.equals("Oso polar") || tipoAnimal.equals("Foca") || tipoAnimal.equals("Morsa") || tipoAnimal.equals("Pingüino emperador") || tipoAnimal.equals("Krill");
            case "Montaña Rocosa":
                return tipoAnimal.equals("Cabra montés") || tipoAnimal.equals("Puma") || tipoAnimal.equals("Cóndor") || tipoAnimal.equals("Águila real");
            case "Aviario":
                return tipoAnimal.equals("Guacamayo") || tipoAnimal.equals("Tucán") || tipoAnimal.equals("Loro gris") || tipoAnimal.equals("Halcón peregrino");
            case "Terrario":
                return tipoAnimal.equals("Iguana") || tipoAnimal.equals("Gecko") || tipoAnimal.equals("Pitón real") || tipoAnimal.equals("Salamandra tigre");
            case "Acuario":
                return tipoAnimal.equals("Tiburón gato") || tipoAnimal.equals("Manta raya") || tipoAnimal.equals("Pez león") || tipoAnimal.equals("Caballito de mar") || tipoAnimal.equals("Erizo de mar");
            case "Zona Nocturna":
                return tipoAnimal.equals("Murciélago") || tipoAnimal.equals("Aye-aye") || tipoAnimal.equals("Serpiente nocturna") || tipoAnimal.equals("Escorpión fluorescente");
            default:
                return false;
        }
    }

    private void mostrarAlerta(String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }

    public Zoo getZoo() {
        return zoo;
    }
}