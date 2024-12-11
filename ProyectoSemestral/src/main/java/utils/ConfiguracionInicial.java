package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionInicial {
    private Properties config;
    private static final String CONFIG_FILE = "config.properties";

    public ConfiguracionInicial() {
        config = new Properties();
    }

    public void cargarConfiguracion() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("No se encuentra el archivo de configuración");
            }
            config.load(input);
        }
    }

    public String obtenerPropiedad(String key) {
        return config.getProperty(key);
    }

    public int obtenerPropiedadNumerica(String key) {
        return Integer.parseInt(config.getProperty(key, "0"));
    }

    public boolean obtenerPropiedadBooleana(String key) {
        return Boolean.parseBoolean(config.getProperty(key, "false"));
    }
}