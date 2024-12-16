package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static ImageManager instance;
    private final Map<String, Image> imageCache;

    private ImageManager() {
        imageCache = new HashMap<>();
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    private Image getImage(String path) {
        if (!imageCache.containsKey(path)) {
            try {
                // Intentar cargar usando getResourceAsStream
                InputStream is = getClass().getResourceAsStream(path);
                if (is == null) {
                    // Si falla, intentar con File
                    File file = new File("src/main/resources" + path);
                    if (file.exists()) {
                        BufferedImage img = ImageIO.read(file);
                        imageCache.put(path, img);
                    } else {
                        System.err.println("No se encontró la imagen: " + path);
                        System.err.println("Ruta absoluta: " + file.getAbsolutePath());
                        return null;
                    }
                } else {
                    BufferedImage img = ImageIO.read(is);
                    imageCache.put(path, img);
                }
            } catch (Exception e) {
                System.err.println("Error cargando imagen: " + path);
                e.printStackTrace();
                return null;
            }
        }
        return imageCache.get(path);
    }

    public Image getAnimalImage(String habitat, String animal) {
        String path = "/images/animales/" + habitat.toLowerCase() + "/" + animal.toLowerCase() + ".png";
        return getImage(path);
    }

    public Image getHabitatImage(String habitat) {
        return getImage("/images/habitats/" + habitat.toLowerCase() + ".png");
    }

    public Image getFoodImage(String food) {
        return getImage("/images/comida/" + food.toLowerCase() + ".png");
    }

}