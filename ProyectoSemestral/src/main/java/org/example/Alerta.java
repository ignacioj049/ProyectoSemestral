public class Alerta {
    private String mensaje;
    private int prioridad;

    public Alerta(String mensaje, int prioridad) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}