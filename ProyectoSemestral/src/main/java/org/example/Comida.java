public class Comida {
    private String tipo;
    private int cantidad;

    public Comida(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void reducirCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}