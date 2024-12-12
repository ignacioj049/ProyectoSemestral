package models;

import models.habitats.Habitat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Habitat> habitats;
    private String nombre;

    public Zoo() {
        this.habitats = new ArrayList<>();
        this.nombre = "Zoo Simulator";
    }

    public boolean agregarHabitat(Habitat habitat) {
        if (!existeHabitat(habitat.getNombre())) {
            return habitats.add(habitat);
        }
        return false;
    }

    public boolean removerHabitat(Habitat habitat) {
        return habitats.remove(habitat);
    }

    public List<Habitat> getHabitats() {
        return new ArrayList<>(habitats);
    }

    public Habitat buscarHabitatPorNombre(String nombre) {
        return habitats.stream()
                .filter(h -> h.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public boolean existeHabitat(String nombre) {
        return habitats.stream()
                .anyMatch(h -> h.getNombre().equalsIgnoreCase(nombre));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "nombre='" + nombre + '\'' +
                ", número de hábitats=" + habitats.size() +
                '}';
    }
}