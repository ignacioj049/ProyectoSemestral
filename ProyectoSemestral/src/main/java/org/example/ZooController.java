public class ZooController {
    private Zoo zoo;

    public ZooController(Zoo zoo) {
        this.zoo = zoo;
    }

    public void crearHabitat(String nombre, String tipo) {
        Habitat habitat = new Habitat(nombre, tipo);
        zoo.agregarHabitat(habitat);
    }

    public void agregarAnimalAHabitat(String nombreHabitat, Animal animal) {
        for (Habitat habitat : zoo.getHabitats()) {
            if (habitat.getNombre().equals(nombreHabitat)) {
                habitat.agregarAnimal(animal);
                break;
            }
        }
    }

    public void moverAnimal(String nombreAnimal, String nombreHabitatOrigen, String nombreHabitatDestino) {
        Habitat origen = null;
        Habitat destino = null;
        Animal animal = null;

        for (Habitat habitat : zoo.getHabitats()) {
            if (habitat.getNombre().equals(nombreHabitatOrigen)) {
                origen = habitat;
            }
            if (habitat.getNombre().equals(nombreHabitatDestino)) {
                destino = habitat;
            }
        }

        if (origen != null && destino != null) {
            for (Animal a : origen.getAnimales()) {
                if (a.getNombre().equals(nombreAnimal)) {
                    animal = a;
                    break;
                }
            }
            if (animal != null) {
                zoo.moverAnimal(animal, origen, destino);
            }
        }
    }
}