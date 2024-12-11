package models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.UUID;

public class Alerta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String mensaje;
    private int prioridad;
    private LocalDateTime fecha;
    private boolean leida;
    private String tipoAlerta;
    private String objetivoId;
    private String habitatId;

    public Alerta(String mensaje, int prioridad, LocalDateTime fecha) {
        this.id = UUID.randomUUID().toString();
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.leida = false;
        analizarMensaje();
    }

    private void analizarMensaje() {
        if (mensaje.contains("comida")) {
            tipoAlerta = "ALIMENTACION";
        } else if (mensaje.contains("médica") || mensaje.contains("salud")) {
            tipoAlerta = "SALUD";
        } else if (mensaje.contains("limpieza")) {
            tipoAlerta = "LIMPIEZA";
        } else if (mensaje.contains("actividades") || mensaje.contains("atención")) {
            tipoAlerta = "ENTRETENIMIENTO";
        } else {
            tipoAlerta = "GENERAL";
        }
    }

    public String getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void marcarComoLeida() {
        this.leida = true;
    }

    public void setObjetivoId(String objetivoId) {
        this.objetivoId = objetivoId;
    }

    public void setHabitatId(String habitatId) {
        this.habitatId = habitatId;
    }

    public String getObjetivoId() {
        return objetivoId;
    }

    public String getHabitatId() {
        return habitatId;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Prioridad: %d",
                fecha.toString(), mensaje, prioridad);
    }
}