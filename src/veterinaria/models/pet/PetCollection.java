package veterinaria.models.pet;

import veterinaria.exceptions.NotAnExistingPet;
import veterinaria.util.ICollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class PetCollection implements ICollection, Serializable {
    private ArrayList<Pet> petList;
    private static Scanner scan= new Scanner(System.in);

    public PetCollection() {
        petList = new ArrayList<>();
    }
    public PetCollection(ArrayList<Pet> petList)
    {
        this.petList=petList;
    }

    public ArrayList<Pet> getPetList() {
        return petList;
    }

    /**
    * Este metodo se encarga de registrar todos los datos de un animal para agregarlo a la colección
    */
    public void create()
    {
        //zona de declaraciones
        String name, race;
        float weight, height;
        int age;
        char sex;
        Pet mascota;
        //fin zona de declaraciones
            //carga de datos
            do {
                System.out.println("Ingresa el nombre de la mascota");
                name = scan.nextLine();
            } while (name.length() == 0);
            do {
                System.out.println("Ingresa la Raza de la mascota");
                race = scan.nextLine();
            } while (race.length() == 0);
            do {
                System.out.println("Ingresa la edad de la mascota");
                age = scan.nextInt();
                scan.nextLine();
            } while (age <= 0);
            do {
                System.out.println("Ingresa el sexo de la mascota (m/f)");
                sex = scan.next().charAt(0);
            } while (sex != 'm' && sex != 'f' && sex != 'M' && sex != 'F');
            do {
                System.out.println("Ingresa el Peso de la mascota");
                weight = scan.nextFloat();
            } while (weight <= 0f);
            do {
                System.out.println("Ingresa la altura de la mascota");
                height = scan.nextFloat();
                scan.nextLine();
            } while (height <= 0f);
        System.out.println("FINALIZADO");
            //fin zona carga de datos

        //almacenar en colección
        mascota= new Pet(name,race,age,sex,weight,height);
        petList.add(mascota);
        //fin almacenar en colección
    }
    /**
    * Añade una mascota a la colección
    * @param obj Recibe una mascota o no hace nada
    */

    @Override
    public void add(Object obj) {
        if (obj instanceof Pet){
            petList.add((Pet) obj);
        }
    }
    /**
    * Muestra la colección completa de mascotas de un cliente (que tengan "status=true")
     * @return retorna un StringBuilder con la lista de mascotas
    * */
    @Override
    public String showCollection() {
        StringBuilder builder = new StringBuilder();
        int num=-1;//sirve para marcar el indice en el submenu de mascotas y hacer mas facil la busqueda
        for (Pet pet : petList) {
            num++;
            if (pet.isStatus())//comprueba si el status del pet no es false
            {
                builder.append("[").append(num).append("] ").append(pet.toString());
            }
        }
        return builder.toString();
    }
    /**
    *cambia el status de las mascotas para un "borrado" logico
     * @param data recibe el indice de la mascota a "remover"
     * @throws NotAnExistingPet si el indice es incorrecto o si la mascota ingresada ya tiene "status=false"
    * */
    public void petRemove(int data) throws NotAnExistingPet
    {
        if (data<petList.size())//comprueba si existe el indice
        {
            if (petList.get(data).isStatus())//comprueba si el status es true
            {
                petList.get(data).setStatus(false);//setea el status a false
            }else {
                throw new NotAnExistingPet("La mascota ingresada ya fue eliminada del sistema");
            }
        }else {
            throw new NotAnExistingPet("La mascota Ingresada no existe");
        }
    }
    /**
     * almacena en un String una mascota en especifico ingresando el indice  de la misma por parametro
     * @param data recibe el indice de la mascota a mostrar
     * @throws NotAnExistingPet si el indice es incorrecto o si la mascota ingresada tiene "status=false"
     * @return retorna un string con los datos de la mascota ingresada por parametro
     * */

    @Override
    public String showSpecific(int data) {
        String msj="";
        try{
            if (data<petList.size())//comprueba si existe el indice
            {    if (petList.get(data).isStatus())//comprueba si el status es true
                {
                    msj= petList.get(data).toString();
                }else {
                    throw new NotAnExistingPet("La mascota ingresada fue eliminada del sistema");
                }
            }
            else {
                throw new NotAnExistingPet("La mascota Ingresada no existe");
            }
        }catch(NotAnExistingPet e)
        {
            e.printStackTrace();
        }
        return msj;
    }
    /**
     * Esta funcion es el primer paso antes del metodo modificar, sirve para devolver una mascota en especifico.
     * el usuario ingresa el indice por parametro y el codigo se encarga de retornar la mascota.
     * @param data ingresa el indice de la mascota
     * @return retorna la mascota que corresponde al indice
     * @throws NotAnExistingPet si el indice es incorrecto o si la mascota ingresada tiene "status=false"
     * */

    public Pet returnPet(int data)//1er paso para el metodo modificar
    {
        Pet mascota = null;
        try{
            if (data<petList.size())
            {
                if (petList.get(data).isStatus())
                {
                    mascota = petList.get(data);
                }else {
                    throw new NotAnExistingPet("La mascota ingresada fue eliminada del sistema");
                }
            }else {
                throw new NotAnExistingPet("La mascota Ingresada no Existe");
            }
        } catch (NotAnExistingPet e) {
            e.printStackTrace();
        }
        return mascota;
    }
    /**
     * Esta funcion modifica mediante los "sets" correspondientes los atributos elegidos por el usuario
     * @param mascota recibe la mascota a editar, esto no se ingresa por el usuario sino que ya esta incluida desde el menu
     * @param op recibe la opcion que fue elegida en el menu y la utiliza en el cuerpo con un switch
     * @return retorna un String con un mensaje diciendo si la operacion fue realizada o si el numero de op no corresponde a la cantidad de opciones
     * */
    public String modificar(Pet mascota, int op)//modificar recibe la mascota a modificar y la opcion elegida
    {
        //
        String name, race, msj;
        int age;
        char sex;
        float peso, altura;
        //
        switch (op) {
//nombre
            case 1 -> {
                do {
                    System.out.println("Ingrese el nuevo nombre de la mascota: ");
                    name = scan.nextLine();
                }while(name.length()==0);
                mascota.setName(name);
                msj= "\nRealizado";
            }
//raza
            case 2 -> {
                do {
                    System.out.print("Ingrese la nueva raza de la mascota: ");
                    race = scan.nextLine();
                }while (race.length()==0);
                mascota.setRace(race);
                msj= "\nRealizado";
            }
//edad
            case 3 -> {
                do {
                    System.out.print("Ingrese la nueva edad de la mascota: ");
                    age = scan.nextInt();
                }while (age<=0);
                mascota.setAge(age);
                msj= "\nRealizado";
            }
//sexo
            case 4 -> {
                do {
                    System.out.print("Ingrese el nuevo sexo de la mascota: ");
                    sex = scan.next().charAt(0);
                }while (sex != 'm' && sex != 'f' && sex != 'M' && sex != 'F');
                mascota.setSex(sex);
                msj= "\nRealizado";
            }
//peso
            case 5 -> {
                do {
                    System.out.print("Ingrese el nuevo peso de la mascota: ");
                    peso = scan.nextFloat();
                }while(peso<=0);
                mascota.setWeight(peso);
                msj= "\nRealizado";
            }
            case 6 -> {
                do {
                    System.out.print("Ingrese la nueva altura de la mascota: ");
                    altura = scan.nextFloat();
                }while(altura<=0);
                mascota.setHeight(altura);
                msj= "\nRealizado";
            }
            default -> msj= "Ingrese una opcion valida";
        }
        return msj;
    }

}
