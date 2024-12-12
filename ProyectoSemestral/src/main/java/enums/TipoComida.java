package enums;

public enum TipoComida {
    CARNE("Carne"),
    PESCADO("Pescado"),
    FRUTAS("Frutas"),
    VEGETALES("Vegetales"),
    INSECTOS("Insectos");

    private final String nombre;

    TipoComida(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}