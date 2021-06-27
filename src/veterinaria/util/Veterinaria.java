package veterinaria.util;

import veterinaria.exceptions.NotAnExistingClient;
import veterinaria.exceptions.NotAnExistingPet;
import veterinaria.exceptions.NotAnExistingTurn;
import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.models.pet.Pet;
import veterinaria.models.schedule.Schedule;
import veterinaria.models.schedule.Turn;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Veterinaria {
    private ClientCollection<Client> clientSet;
    private ArrayList<Turn> turnArrayList;
    private Schedule schedule;
    static Scanner scan = new Scanner(System.in);
    private File file;

    public Veterinaria() {
        clientSet = new ClientCollection();
        schedule = new Schedule();
        turnArrayList = new ArrayList<Turn>();
        Menu();
    }

    private void Menu() {
        int option;
        clientSet.fileToCollection();
        turnsFileToCollection();

            do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~\n");
            System.out.println("1 - Menu Clientes.");
            System.out.println("2 - Menu Mascotas.");
            System.out.println("3 - Menu Agenda.");
            System.out.println("0 -  Salir.");

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1 -> clientMenu();
                case 2 -> petMenu();
                case 3 -> scheduleMenu(clientSet);
                case 0 -> System.out.println("Cerrando...");
                default -> System.out.println("Ingrese una opción válida.");
            }
        }while(option!=0);
    }

    // Aparatado Clientes

    private void clientMenu() {
        int option;

        do {
            System.out.println("~~~~~~~ Menu Clientes ~~~~~~~\n");
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
        System.out.println("Ingrese el DNI del dueño de la mascota: ");
        DNI = scan.nextLine();
        try {
            aux = clientSet.search(DNI);
            if(aux != null) {
                menuMascotas(aux);
            }
        } catch (NotAnExistingClient notAnExistingClient) {
            notAnExistingClient.printStackTrace();
        }
    }

    public void menuMascotas(Client c){
        //zona declaración de variables
        int option;
        //fin zona declaración de variables

        do {
            System.out.println("~~~~~~~ Menu de Mascotas ~~~~~~~");
            System.out.println("~~~~~~~ Cliente: " + c.getName() + " " + c.getLastName() + " ~~~~~~~");
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
                if(aux != null)
                {
                    menuModificarMascota(aux, c);
                }
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
        int aux=0;
        boolean auxBoolean;
        String auxString;

        do {
            System.out.println("~~~~~~~ Menu Agenda ~~~~~~~\n");
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
                        aux = askTurnNumber();
                        auxBoolean = schedule.exist(aux);
                        try {
                            if (auxBoolean == true) {
                                auxString = askTurnReason();
                                try {
                                    schedule.modifyTurn(aux, auxString);
                                } catch (NotAnExistingTurn notAnExistingTurn) {
                                    notAnExistingTurn.printStackTrace();
                                }
                            }else throw new NotAnExistingTurn("El turno ingresado no existe");
                        }catch (NotAnExistingTurn ex) {
                            System.out.println(ex.getMessage());
                        }
                    break;
                case 3:
                    System.out.println(schedule.showCollection());
                    break;
                case 4:
                    try {
                        schedule.deleteTurn(removeTurnMenu());
                    } catch (NotAnExistingTurn notAnExistingTurn) {
                        notAnExistingTurn.printStackTrace();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
            jsonUtil.grabar(schedule.turnMapToJson());
        }while(option!=0);
    }

    private void turnsFileToCollection() {
        file = new File("turns.json");
        if (file.exists()) {
            try {
                turnArrayList = jsonUtil.jsonToJava(jsonUtil.leer());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        //ArrayList to HashMAP
        for (Turn turn : turnArrayList){
            schedule.add(turn);
        }
    }

    private int removeTurnMenu(){
        int turn = -1;
        System.out.println(schedule.showCollection());
        System.out.println("Ingrese el numero del turno que desea borrar: ");
        turn = scan.nextInt();
        return turn;
    }

    private int askTurnNumber(){
        int turn = -1;
        System.out.println(schedule.showCollection());
        System.out.println("Ingrese el turno que desea modificar la razon: ");
        turn = scan.nextInt();
        scan.nextLine();
        return turn;
    }

    private String askTurnReason(){
        String reason="";
        System.out.println("Ingrese la nueva razon: ");
        reason = scan.nextLine();
        return reason;
    }

    // Fin Apartado Schedule
}
