package veterinaria.models.pet;

import veterinaria.util.ICollection;

import java.io.Serializable;
import java.util.ArrayList;

public class PetCollection implements ICollection, Serializable {
    private ArrayList<Pet> petList;

    public PetCollection() {
        petList = new ArrayList<Pet>();
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
