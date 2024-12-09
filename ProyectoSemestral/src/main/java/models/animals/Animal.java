package models.animals;
import enums.TipoAnimal;
import enums.TipoComida;
import enums.TipoHabitat;

public class Animal {
    private String nombre;
    private TipoAnimal tipo;
    private TipoComida comidaPreferida;
    private TipoHabitat habitat;
    private String estadoSalud;

    public Animal(String nombre, TipoAnimal tipo, TipoComida comidaPreferida, TipoHabitat Habitat) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.comidaPreferida = comidaPreferida;
        this.estadoSalud = "saludable";
        this.habitat = habitat;
    }

    public Animal(String nombre, String especie, int i, String años, String tipoComida) {
    }

    public String getNombre() {
        return nombre;
    }

    public TipoAnimal getTipo() {
        return tipo;
    }

    public TipoComida getComidaPreferida() {
        return comidaPreferida;
    }

    public TipoHabitat getHabitat() {
        return habitat;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }


}