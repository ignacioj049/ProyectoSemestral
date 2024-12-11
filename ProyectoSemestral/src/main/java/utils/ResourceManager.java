package utils;

import javafx.scene.image.Image;
import java.io.File;
import java.net.URL;

public class ResourceManager {
    private static final String BASE_PATH = "src/resources/";

    public static Image loadImage(String path) {
        try {
            File file = new File(BASE_PATH + path);
            if (!file.exists()) {
                System.err.println("No se pudo encontrar la imagen: " + file.getAbsolutePath());
                return null;
            }
            return new Image(file.toURI().toString());
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public static String getResourcePath(String path) {
        try {
            File file = new File(BASE_PATH + path);
            if (!file.exists()) {
                System.err.println("No se pudo encontrar el recurso: " + file.getAbsolutePath());
                return null;
            }
            return file.toURI().toString();
        } catch (Exception e) {
            System.err.println("Error al obtener la ruta del recurso: " + path);
            e.printStackTrace();
            return null;
        }
    }
}