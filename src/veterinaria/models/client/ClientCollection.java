package veterinaria.models.client;

import veterinaria.util.ICollection;

import java.io.Serializable;
import java.util.HashSet;

public class ClientCollection<E extends Person> implements ICollection, Serializable {
    private HashSet<E> clientSet;

    public ClientCollection() {
        clientSet = new HashSet<E>();
    }

    @Override
    public void add(Object obj) {

    }

    @Override
    public String showCollection() {
        return null;
    }

    @Override
    public void showSpecific(int data) {

    }
}
