package utils;

import java.io.*;

public class GestorArchivos {
    private static final String DIRECTORIO_DATOS = "datos/";

    static {
        new File(DIRECTORIO_DATOS).mkdirs();
    }

    public static boolean existenDatosGuardados(String nombreArchivo) {
        File archivo = new File(DIRECTORIO_DATOS + nombreArchivo);
        return archivo.exists() && archivo.length() > 0;
    }

    public static void guardarObjeto(Object objeto, String nombreArchivo) throws IOException {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaCompleta))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            throw new IOException("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static Object cargarObjeto(String nombreArchivo) throws IOException, ClassNotFoundException {
        String rutaCompleta = DIRECTORIO_DATOS + nombreArchivo;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaCompleta))) {
            return ois.readObject();
        } catch (IOException e) {
            throw new IOException("Error al cargar el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error al deserializar el objeto: " + e.getMessage());
        }
    }

    public static void eliminarArchivo(String nombreArchivo) {
        File archivo = new File(DIRECTORIO_DATOS + nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    public static void limpiarDirectorio() {
        File directorio = new File(DIRECTORIO_DATOS);
        if (directorio.exists()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    archivo.delete();
                }
            }
        }
    }
}