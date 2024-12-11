package enums;

public enum TipoHabitat {
    SELVA("Selva", "Hábitat tropical con alta humedad y vegetación densa"),
    SAVANA("Sabana", "Hábitat cálido con vegetación dispersa y grandes llanuras"),
    DESERTICO("Desierto", "Hábitat árido con altas temperaturas y poca vegetación"),
    POLAR("Polar", "Hábitat frío con temperaturas bajo cero y nieve"),
    ACUATICO("Acuático", "Hábitat con agua dulce o salada para especies marinas");

    private final String nombre;
    private final String descripcion;

    TipoHabitat(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}