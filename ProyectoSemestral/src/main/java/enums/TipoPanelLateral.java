package enums;

public enum TipoPanelLateral {
    CREAR_HABITAT("Crear Hábitat"),
    GESTIONAR_ANIMALES("Gestionar Animales"),
    GESTIONAR_COMIDA("Gestionar Comida"),
    ESTADO_ANIMALES("Estado de Animales"),
    ALERTAS("Alertas");

    private final String nombre;

    TipoPanelLateral(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}