package veterinaria.util;

import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.models.pet.PetCollection;
import veterinaria.models.schedule.Schedule;

import java.util.Scanner;

public class Veterinaria {
    private ClientCollection<Client> clientSet;
    private PetCollection petList;
    private Schedule schedule;
    static Scanner scan = new Scanner(System.in);

    public Veterinaria() {
        //toDo inicializar todas las colecciones(?
        clientSet= new ClientCollection<>();
        Menu();
    }

    private void Menu() {
        int option;

        do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("1 - Menu Clientes.");
            System.out.println("2 - Menu Mascotas.");
            System.out.println("3 - Menu Agenda.");
            System.out.println("0 -  Salir.");

            option = scan.nextInt();

            switch(option) {
                case 1:
                    break;
                case 2:
                    menuMascotas();//ta chckeado el funcionamento de lo poco q tengo
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Cerrando...");
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
        }while(option!=0);
    }

    // Aparatado Clientes
    // mi código
    // Fin Apartado Clientes

    // Apartado Pets
    public void menuMascotas(){
        //zona declaración de variables
        int option;
        Client aux = new Client();//= cliente recibido
        petList= new PetCollection(aux.getClientPetCollection().getPetList());//a petlist le paso la petCollection que hay dentro del cliente aux
        //fin zona declaración de variables

        do {
            System.out.println("~~~~~~~ Menu de Mascotas ~~~~~~~\n");
            System.out.println("1 - Agregar Mascota.");
            System.out.println("2 - Listar Mascotas.");
            System.out.println("0 -  Salir.");

            option = scan.nextInt();

            switch(option) {
                case 1:
                    petList.create();
                    System.out.println(aux.getClientPetCollection().showCollection());
                    break;
                case 2:
                    System.out.println(petList.showCollection());
                    //subMenuMascotas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
        }while(option!=0);
    }
    //toDo subMenuMascotas(){}

    // Fin Apartado Pets

    // Apartado Schedule
    // mi código
    // Fin Apartado Schedule
}
