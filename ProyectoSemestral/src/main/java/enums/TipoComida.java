package enums;

public enum TipoComida {
    CARNE("Carne", 10.0),
    PESCADO("Pescado", 8.0),
    FRUTAS("Frutas", 5.0),
    VEGETALES("Vegetales", 4.0),
    INSECTOS("Insectos", 6.0);

    private final String nombre;
    private final double precioPorKilo;

    TipoComida(String nombre, double precioPorKilo) {
        this.nombre = nombre;
        this.precioPorKilo = precioPorKilo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPorKilo() {
        return precioPorKilo;
    }
}