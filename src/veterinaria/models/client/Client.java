package veterinaria.models.client;

import veterinaria.models.pet.PetCollection;

public class Client extends Person {

    private PetCollection clientPetCollection;//la creo para porder crear y usar el menu :P

    public Client() {
        clientPetCollection = new PetCollection();
    }

    public PetCollection getClientPetCollection() {
        return clientPetCollection;
    }
}
