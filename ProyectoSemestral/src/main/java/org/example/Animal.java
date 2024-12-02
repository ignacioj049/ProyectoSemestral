public class Animal {
    private String nombre;
    private String especie;
    private int edad;
    private String unidadEdad;
    private String tipoComida;
    private String estadoSalud;

    public Animal(String nombre, String especie, int edad, String unidadEdad, String tipoComida) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.unidadEdad = unidadEdad;
        this.tipoComida = tipoComida;
        this.estadoSalud = "saludable";
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getUnidadEdad() { return unidadEdad; }
    public void setUnidadEdad(String unidadEdad) { this.unidadEdad = unidadEdad; }
    public String getTipoComida() { return tipoComida; }
    public void setTipoComida(String tipoComida) { this.tipoComida = tipoComida; }
    public String getEstadoSalud() { return estadoSalud; }
    public void setEstadoSalud(String estadoSalud) { this.estadoSalud = estadoSalud; }
}