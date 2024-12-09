package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import models.Alerta;
import models.Zoo;
import models.habitats.Habitat;
import models.animals.Animal;
import models.food.Comida;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZooController {
    private Zoo zoo;
    private List<Alerta> alertas;
    private Timeline timeline;
    private Random random = new Random();

    public ZooController(Zoo zoo) {
        this.zoo = zoo;
        this.alertas = new ArrayList<>();
        iniciarSimulacion();
    }

    private void iniciarSimulacion() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> simularConsumoComida()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void simularConsumoComida() {
        for (Habitat habitat : zoo.getHabitats()) {
            for (Animal animal : habitat.getAnimales()) {
                switch (animal.getComidaPreferida().toString().toLowerCase()) {
                    case "carne":
                        habitat.consumirComida("carne", 1);
                        break;
                    case "vegetales":
                        habitat.consumirComida("vegetales", 1);
                        break;
                    case "frutas":
                        habitat.consumirComida("frutas", 1);
                        break;
                    case "pescado":
                        habitat.consumirComida("pescado", 1);
                        break;
                }
                // Verificar si el animal tiene hambre
                if (random.nextInt(100) < 10) { // 10% de probabilidad de tener hambre
                    animal.setEstadoSalud("hambre");
                    agregarAlerta("El animal \"" + animal.getNombre() + "\" tiene hambre.", 2);
                }
                // Verificar si el animal se enferma
                if (random.nextInt(100) < 5) { // 5% de probabilidad de enfermarse
                    animal.setEstadoSalud("enfermo");
                    agregarAlerta("El animal \"" + animal.getNombre() + "\" está enfermo.", 1);
                }
                // Verificar si el animal muere
                if (random.nextInt(100) < 1) { // 1% de probabilidad de morir
                    animal.setEstadoSalud("muerto");
                    agregarAlerta("El animal \"" + animal.getNombre() + "\" ha muerto.", 1);
                }
            }
        }
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void agregarAlerta(String mensaje, int prioridad) {
        Alerta alerta = new Alerta(mensaje, prioridad);
        alertas.add(alerta);
    }

    public void solucionarAlerta(Alerta alerta) {
        String mensaje = alerta.getMensaje();
        if (mensaje.contains("enfermo")) {
            curarAnimal(mensaje);
        } else if (mensaje.contains("hambre")) {
            alimentarAnimal(mensaje);
        } else if (mensaje.contains("muerto")) {
            removerAnimal(mensaje);
        }
        alertas.remove(alerta);
        mostrarAlerta("Alerta solucionada: " + alerta.getMensaje());
    }

    private void curarAnimal(String mensaje) {
        // Lógica para curar al animal
        String nombreAnimal = extraerNombreAnimal(mensaje);
        Animal animal = buscarAnimalPorNombre(nombreAnimal);
        if (animal != null) {
            animal.setEstadoSalud("saludable");
            mostrarAlerta("El animal " + nombreAnimal + " ha sido curado.");
        }
    }

    private void alimentarAnimal(String mensaje) {
        // Lógica para alimentar al animal
        String nombreAnimal = extraerNombreAnimal(mensaje);
        Animal animal = buscarAnimalPorNombre(nombreAnimal);
        if (animal != null) {
            Habitat habitat = buscarHabitatPorAnimal(animal);
            if (habitat != null) {
                habitat.agregarComida(animal.getComidaPreferida().toString().toLowerCase(), 5); // Añadir una cantidad de comida
                mostrarAlerta("El animal " + nombreAnimal + " ha sido alimentado.");
            }
        }
    }

    private void removerAnimal(String mensaje) {
        // Lógica para remover al animal muerto
        String nombreAnimal = extraerNombreAnimal(mensaje);
        Animal animal = buscarAnimalPorNombre(nombreAnimal);
        if (animal != null) {
            Habitat habitat = buscarHabitatPorAnimal(animal);
            if (habitat != null) {
                habitat.getAnimales().remove(animal);
                mostrarAlerta("El animal " + nombreAnimal + " ha sido removido.");
            }
        }
    }

    private String extraerNombreAnimal(String mensaje) {
        // Lógica para extraer el nombre del animal del mensaje de alerta
        // Suponiendo que el nombre del animal está entre comillas en el mensaje
        int start = mensaje.indexOf("\"") + 1;
        int end = mensaje.indexOf("\"", start);
        if (start > 0 && end > start) {
            return mensaje.substring(start, end);
        } else {
            return ""; // Retorna una cadena vacía si no se encuentra el nombre entre comillas
        }
    }

    private Animal buscarAnimalPorNombre(String nombre) {
        for (Habitat habitat : zoo.getHabitats()) {
            for (Animal animal : habitat.getAnimales()) {
                if (animal.getNombre().equals(nombre)) {
                    return animal;
                }
            }
        }
        return null;
    }

    private Habitat buscarHabitatPorAnimal(Animal animal) {
        for (Habitat habitat : zoo.getHabitats()) {
            if (habitat.getAnimales().contains(animal)) {
                return habitat;
            }
        }
        return null;
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
                if (esHabitatAdecuado(destino, animal.getTipo().toString())) {
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
        switch (habitat.getTipo().toString().toLowerCase()) {
            case "sabana_africana":
                return tipoAnimal.equalsIgnoreCase("mamifero") || tipoAnimal.equalsIgnoreCase("ave");
            case "jungla_tropical":
                return tipoAnimal.equalsIgnoreCase("mamifero") || tipoAnimal.equalsIgnoreCase("ave") || tipoAnimal.equalsIgnoreCase("reptil");
            case "region_artica":
                return tipoAnimal.equalsIgnoreCase("mamifero") || tipoAnimal.equalsIgnoreCase("ave");
            case "desierto":
                return tipoAnimal.equalsIgnoreCase("mamifero") || tipoAnimal.equalsIgnoreCase("reptil");
            case "oceano_y_acuarios":
                return tipoAnimal.equalsIgnoreCase("mamifero_marino") || tipoAnimal.equalsIgnoreCase("pez");
            case "bosques_templados":
                return tipoAnimal.equalsIgnoreCase("mamifero") || tipoAnimal.equalsIgnoreCase("ave");
            default:
                return false;
        }
    }

    public void agregarComidaAHabitat(String nombreHabitat, String tipoComida, int cantidad) {
        Habitat habitat = zoo.buscarHabitatPorNombre(nombreHabitat);
        if (habitat != null) {
            habitat.agregarComida(tipoComida, cantidad);
            mostrarAlerta("Comida agregada al hábitat " + nombreHabitat);
        } else {
            mostrarAlerta("Hábitat no encontrado: " + nombreHabitat);
        }
    }

    public Zoo getZoo() {
        return zoo;
    }
}