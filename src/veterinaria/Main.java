package veterinaria;

import veterinaria.exceptions.NotAnExistingPet;
import veterinaria.models.client.Client;
import veterinaria.models.pet.Pet;
import veterinaria.models.pet.PetCollection;
import veterinaria.models.schedule.Turn;
import veterinaria.util.Veterinaria;

public class Main {

    public static void main(String[] args) {
        //Veterinaria vet = new Veterinaria();
        /*//prueba Pets
        Pet mascota = new Pet("max","perro",10,'m',5,1.9f);//0
        PetCollection lista=new PetCollection();
        lista.add(mascota);
        lista.create();//1
        //lista.create();//2
        //lista.create();//3
        System.out.println(lista.showSpecific(2));
        try {
            lista.petRemove(2);
        } catch (NotAnExistingPet notAnExistingPet) {
            notAnExistingPet.printStackTrace();
        }
        System.out.println(lista.showCollection());
        //fin prueba Pets*/

        //TODO PRUEBAS TURNOS
        Client client = new Client("pedro", "gomez","202020","2121210","colombres","visa");
        Turn turno = new Turn(client,"lala");






    }
}
