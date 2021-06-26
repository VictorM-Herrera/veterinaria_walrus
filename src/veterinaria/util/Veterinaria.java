package veterinaria.util;

import veterinaria.models.client.ClientCollection;
import veterinaria.models.pet.PetCollection;
import veterinaria.models.schedule.Schedule;

import java.util.Scanner;

public class Veterinaria {
    private ClientCollection clientSet;
    private PetCollection petList;
    private Schedule schedule;
    static Scanner scan = new Scanner(System.in);

    public Veterinaria() {
        Menu();
    }

    private void Menu() {
        int option;

        do {
            System.out.println("~~~~~~~ Veterinaria Walrus ~~~~~~~");
            System.out.println("");
            System.out.println("1 - Menu Clientes.");
            System.out.println("2 - Menu Mascotas.");
            System.out.println("3 - Menu Agenda.");
            System.out.println("0 -  Salir.");

            option = scan.nextInt();

            switch(option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
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
    // mi código
    // Fin Apartado Pets

    // Apartado Schedule
    // mi código
    // Fin Apartado Schedule
}
