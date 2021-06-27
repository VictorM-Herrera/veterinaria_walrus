package veterinaria.models.schedule;

import veterinaria.util.ICollection;

import java.io.Serializable;
import java.util.HashMap;

public class Schedule implements ICollection, Serializable {
    // La clave del mapa es el numero de los turnos.
    // TODO Investigar sobre funcionamiento de hashmaps.
    private HashMap<Integer, Turn> turnHashMap;

    public Schedule() {
        turnHashMap = new HashMap<Integer, Turn>();
    }

    @Override
    public void add(Object obj) {

    }

    @Override
    public String showCollection() {
        return null;
    }

    @Override
    public String showSpecific(int data) {
    return "";
    }
}
