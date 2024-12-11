package enums;

public enum TipoAnimal {
    // Animales de la selva
    TIGRE("Tigre", TipoHabitat.SELVA, TipoComida.CARNE),
    MONO("Mono", TipoHabitat.SELVA, TipoComida.FRUTAS),
    JAGUAR("Jaguar", TipoHabitat.SELVA, TipoComida.CARNE),

    // Animales de la sabana
    LEON("León", TipoHabitat.SAVANA, TipoComida.CARNE),
    JIRAFA("Jirafa", TipoHabitat.SAVANA, TipoComida.VEGETALES),
    CEBRA("Cebra", TipoHabitat.SAVANA, TipoComida.VEGETALES),
    ELEFANTE("Elefante", TipoHabitat.SAVANA, TipoComida.VEGETALES),

    // Animales acuáticos
    DELFIN("Delfín", TipoHabitat.ACUATICO, TipoComida.PESCADO),
    TIBURON("Tiburón", TipoHabitat.ACUATICO, TipoComida.PESCADO),
    TORTUGA_MARINA("Tortuga Marina", TipoHabitat.ACUATICO, TipoComida.VEGETALES),

    // Animales polares
    PINGUINO("Pingüino", TipoHabitat.POLAR, TipoComida.PESCADO),
    OSO_POLAR("Oso Polar", TipoHabitat.POLAR, TipoComida.PESCADO),
    FOCA("Foca", TipoHabitat.POLAR, TipoComida.PESCADO),

    // Animales del desierto
    CAMELLO("Camello", TipoHabitat.DESERTICO, TipoComida.VEGETALES),
    ZORRO_DESIERTO("Zorro del Desierto", TipoHabitat.DESERTICO, TipoComida.CARNE),
    AGUILA_CALVA("Águila Calva", TipoHabitat.DESERTICO, TipoComida.CARNE);

    private final String nombre;
    private final TipoHabitat habitatPreferido;
    private final TipoComida tipoComidaPreferida;

    TipoAnimal(String nombre, TipoHabitat habitatPreferido, TipoComida tipoComidaPreferida) {
        this.nombre = nombre;
        this.habitatPreferido = habitatPreferido;
        this.tipoComidaPreferida = tipoComidaPreferida;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoHabitat getHabitatPreferido() {
        return habitatPreferido;
    }

    public TipoComida getTipoComidaPreferida() {
        return tipoComidaPreferida;
    }

    public String getImagenPath() {
        return name().toLowerCase().replace('_', '_');
    }
}