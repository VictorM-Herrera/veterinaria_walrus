package veterinaria.models.schedule;

import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.util.ICollection;

import java.io.Serializable;
import java.util.*;


public class Schedule implements ICollection, Serializable {
    // La clave del mapa es el numero de los turnos.
    // TODO Investigar sobre funcionamiento de hashmaps.
    private HashMap<Integer, Turn> turnHashMap;
    private static Scanner scan = new Scanner(System.in);

    public Schedule() {
        turnHashMap = new HashMap<Integer, Turn>();
    }

    @Override
    public void add(Object obj) {
        Turn turno = (Turn) obj;
        if(turno instanceof Turn){
            if(!exist(turno.getTurnNumber())){
                turnHashMap.put(turno.getTurnNumber(), turno);
            }
        }
    }

    @Override
    public String showCollection() {
        Iterator<Map.Entry<Integer, Turn>> it = turnHashMap.entrySet().iterator();
        StringBuilder builder = new StringBuilder();

        while(it.hasNext()){
            Map.Entry<Integer, Turn> entry = (Map.Entry<Integer, Turn>) it.next();
            builder.append(entry.getKey() + " / " + entry.getValue().toString() + "\n");
        }
        return null;
    }

    @Override
    public String showSpecific(int data) {
    return "";
    }

    //Funcion que retorna true si el mapa contiene la key dada
    public Boolean exist(Integer key){
        return turnHashMap.containsKey(key);
    }

    public void createTurn(ClientCollection collection){
        Turn turn;
        Client client;
        String dni="", reason;
        Date date = new Date();


        //TODO PONER ESTO EN UNA EXCEPCION DE CLIENTE NO ENCONTRADO

        System.out.println("Ingrese el DNI del cliente");
        dni = scan.nextLine();
        client = collection.search(dni);
        if(client != null){
            System.out.println("Ingrese la razon del turno");
            reason = scan.nextLine();
            turn = new Turn(client,reason);
            turnHashMap.put(turn.getTurnNumber(),turn);
        }

    }
}
