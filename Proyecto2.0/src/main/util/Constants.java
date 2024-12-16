package util;

import java.awt.*;

public class Constants {
    // Rutas de imágenes
    public static final String IMAGE_PATH = "/images/";
    public static final String ANIMALS_PATH = IMAGE_PATH + "animales/";
    public static final String HABITATS_PATH = IMAGE_PATH + "habitats/";
    public static final String FOOD_PATH = IMAGE_PATH + "comida/";

    // Dimensiones
    public static final int ANIMAL_WIDTH = 64;
    public static final int ANIMAL_HEIGHT = 64;
    public static final int HABITAT_WIDTH = 300;
    public static final int HABITAT_HEIGHT = 200;
    public static final int FOOD_SIZE = 32;

    // Tiempos
    public static final int ANIMATION_DELAY = 100;
    public static final int FOOD_CHECK_INTERVAL = 5000;

    // Colores
    public static final Color HABITAT_BORDER_COLOR = new Color(0, 0, 0, 100);
    public static final Color MENU_BACKGROUND = new Color(230, 230, 230);
    public static final Color BUTTON_HABITAT = new Color(200, 230, 200);
    public static final Color BUTTON_ANIMAL = new Color(230, 200, 200);
    public static final Color BUTTON_FOOD = new Color(200, 200, 230);

    // Dimensiones de interfaz
    public static final int MENU_WIDTH = 200;
    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 40;

    // Mensajes
    public static final String ERROR_HABITAT_INVALIDO = "Este animal no puede vivir en este hábitat";
    public static final String ALERTA_COMIDA = "¡Falta comida en el hábitat %s!";
}