package veterinaria.util;

import veterinaria.exceptions.NotAnExistingPet;
import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.models.pet.Pet;
import veterinaria.models.schedule.Schedule;

import java.util.Scanner;

public class Veterinaria {
    private ClientCollection<Client> clientSet;
    private Schedule schedule;
    static Scanner scan = new Scanner(System.in);

    public Veterinaria() {
        clientSet = new ClientCollection();
        schedule = new Schedule();
        Menu();
    }

    private void Menu() {
        int option;
        clientSet.fileToCollection();

        do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("1 - Menu Clientes.");
            System.out.println("2 - Menu Mascotas.");
            System.out.println("3 - Menu Agenda.");
            System.out.println("0 -  Salir.");

            option = scan.nextInt();
            scan.nextLine();

            switch(option) {
                case 1:
                    clientMenu();
                    break;
                case 2:
                    petMenu();
                    break;
                case 3:
                    scheduleMenu(clientSet);
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

    private void clientMenu() {
        int option;

        do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("1 - Añadir Cliente.");
            System.out.println("2 - Modificar Cliente.");
            System.out.println("3 - Listar Clientes.");
            System.out.println("4 - Eliminar Cliente.");
            System.out.println("0 - Regresar.");

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    clientSet.create();
                    break;
                case 2:
                    clientSet.update();
                    break;
                case 3:
                    System.out.println(clientSet.showCollection());
                    break;
                case 4:
                    clientSet.removeClient();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
            clientSet.collectionToFile();
        }while(option!=0);
    }

    // Fin Apartado Clientes

    // Apartado Pets
    private void petMenu() {
        String DNI;
        Client aux;
        System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
        System.out.println("Ingrese el DNI del dueño de la mascota: ");
        DNI = scan.nextLine();
        aux = clientSet.search(DNI);
        if(aux != null) {
            menuMascotas(aux);
        } else {
            System.out.println("El cliente no existe.");
        }
    }

    public void menuMascotas(Client c){
        //zona declaración de variables
        int option;
        //fin zona declaración de variables

        do {
            System.out.println("~~~~~~~ Menu de Mascotas ~~~~~~~");
            System.out.println("~~~~~~~ Cliente " + c.getName() + " " + c.getLastName() + " ~~~~~~~");
            System.out.println("1 - Agregar Mascota.");
            System.out.println("2 - Listar Mascotas.");
            System.out.println("0 - Regresar.");

            option = scan.nextInt();

            switch(option) {
                case 1:
                    c.getClientPetCollection().create();
                    System.out.println(c.getClientPetCollection().showCollection());
                    break;
                case 2:
                    System.out.println(c.getClientPetCollection().showCollection());
                    subMenuMascotas(c);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
            clientSet.collectionToFile();
        }while(option!=0);
    }
    //toDo subMenuMascotas(){}
    private void subMenuMascotas(Client c) {
        int op, i;
        Pet aux;

        System.out.println("1 - Modificar Mascota.");
        System.out.println("2 - Eliminar una Mascota.");
        System.out.println("0 - Regresar.");

        op = scan.nextInt();
        scan.nextLine();

        switch(op) {
            case 1:
                i = indiceMascota();
                aux = c.getClientPetCollection().returnPet(i);
                menuModificarMascota(aux, c);
                break;
            case 2:
                i = indiceMascota();
                try {
                    c.getClientPetCollection().petRemove(i);
                } catch (NotAnExistingPet notAnExistingPet) {
                    notAnExistingPet.printStackTrace();
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Ingrese una opción válida.");
                break;

        }
    }

    private int indiceMascota() {
        int op;
        System.out.println("Ingrese el número de la mascota: ");

        op = scan.nextInt();
        scan.nextLine();
        return op;
    }

    private void menuModificarMascota(Pet aux, Client c) {
        int op;

        System.out.println("¿Qué desea modificar?\n");
        System.out.println("1 - Nombre.");
        System.out.println("2 - Raza.");
        System.out.println("3 - Edad.");
        System.out.println("4 - Sexo.");
        System.out.println("5 - Peso.");
        System.out.println("6 - Altura.");
        System.out.println("0 - Regresar.");

        op = scan.nextInt();
        scan.nextLine();

        System.out.println(c.getClientPetCollection().modificar(aux, op));
    }
    // Fin Apartado Pets

    // Apartado Schedule
    private void scheduleMenu(ClientCollection cc) {
        int option;

        do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("1 - Añadir Turno");
            System.out.println("2 - Modificar Turno.");
            System.out.println("3 - Listar Turnos.");
            System.out.println("4 - Eliminar Turno.");
            System.out.println("0 - Regresar.");

            option = scan.nextInt();
            scan.nextLine();

            switch(option) {
                case 1:
                    schedule.createTurn(cc);
                    break;
                case 2:

                    break;
                case 3:
                    System.out.println(schedule.showCollection());
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
        }while(option!=0);
    }
    // Fin Apartado Schedule
}
