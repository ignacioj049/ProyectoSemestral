package org.example;

public class Notificaciones {
    private String mensaje;
    private String tipo; //salud o comida

    public Notificaciones(String mensaje, String tipo){
        this.mensaje = mensaje;
        this.tipo = tipo;
    }
    public  String getMensaje(){
        return mensaje;
    }

    public String getTipo() {
        return tipo;
    }
}
