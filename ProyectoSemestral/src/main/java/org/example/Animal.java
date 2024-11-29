public class Animal {
    private String nombre;
    private String especie;
    private int edad;
    private String unidadEdad;

    public Animal(String nombre, String especie, int edad, String unidadEdad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.unidadEdad = unidadEdad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUnidadEdad() {
        return unidadEdad;
    }

    public void setUnidadEdad(String unidadEdad) {
        this.unidadEdad = unidadEdad;
    }
}