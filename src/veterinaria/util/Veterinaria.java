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
                    petMenu(); // Puente para buscar el cliente y luego llama a MenuMascotas.
                    //menuMascotas();//ta chckeado el funcionamento de lo poco q tengo
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
                    clientCreate();
                    break;
                case 2:
                    clientUpdate();
                    break;
                case 3:
                    System.out.println(clientSet.showCollection());
                    break;
                case 4:
                    clientRemove();
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

    private void clientCreate() {
        try {
            Client c;

            String name, lastName, DNI, phone, address, paymentMethod;
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("Ingrese el nombre del cliente: ");
            name = scan.nextLine();
            System.out.println("Ingrese el apellido del cliente: ");
            lastName = scan.nextLine();
            System.out.println("Ingrese el DNI del cliente: ");
            DNI = scan.nextLine();
            System.out.println("Ingrese el teléfono del cliente: ");
            phone = scan.nextLine();
            System.out.println("Ingrese la dirección del cliente: ");
            address = scan.nextLine();
            System.out.println("Ingrese el método de pago del cliente(Tarjeta/Efectivo): ");
            paymentMethod = scan.nextLine();

            c = new Client(name, lastName, DNI, phone, address, paymentMethod);

            if(c != null) {
                clientSet.add(c);
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clientUpdate() {
        int option;
        System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
        System.out.println("1 - Modificar nombre.");
        System.out.println("0 - Regresar.");
        option = scan.nextInt();
        scan.nextLine();

        switch(option) {
            case 1:
                nameModify();
                break;
            case 0:
                break;
            default:
                System.out.println("Ingrese una opción válida.");
                break;
        }

    }

    private void nameModify() {
        String DNI, name;
        Client found;
        System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
        System.out.println("Ingrese el DNI del cliente a modificar: ");
        DNI = scan.nextLine();
        found = clientSet.search(DNI);  // Buscamos el cliente y lo guardamos.
        if (found != null) {
            clientSet.remove(found);        // Removemos el cliente del hashSet genérico.
            System.out.println("Ingrese el nuevo nombre del Cliente: ");
            name = scan.nextLine();
            found.setName(name);         // Cambiamos el nombre del cliente.
            clientSet.add(found);           // Ingresamos el cliente actualizado en la colección.
        } else {
            System.out.println("El cliente no existe.");
        }
    }

    private void clientRemove() {
        String DNI;
        Client found;
        System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
        System.out.println("Ingrese el DNI del cliente a remover: ");
        DNI = scan.nextLine();
        found = clientSet.search(DNI);  // Buscamos el cliente y lo guardamos.
        if (found != null) {
            clientSet.remove(found);        // Removemos el cliente del hashSet genérico.
            found.setStatus(false);         // Cambiamos el estado del cliente a falso.
            clientSet.add(found);           // Ingresamos el cliente actualizado en la colección.
        } else {
            System.out.println("El cliente no existe.");
        }
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
    // mi código
    // Fin Apartado Schedule
}
