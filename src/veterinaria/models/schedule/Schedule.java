package veterinaria.models.schedule;

import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.util.ICollection;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        //Turn turno = (Turn) obj;
        if(obj instanceof Turn){
            if(!exist(((Turn) obj).getTurnNumber())){
                turnHashMap.put(((Turn) obj).getTurnNumber(), (Turn) obj);
            }
        }
    }

    @Override
    public String showCollection() {
        Iterator<Map.Entry<Integer, Turn>> it = turnHashMap.entrySet().iterator();
        StringBuilder builder = new StringBuilder();

        while(it.hasNext()){
            Map.Entry<Integer, Turn> entry = (Map.Entry<Integer, Turn>) it.next();
            builder.append("[" + entry.getKey() + "] " + entry.getValue().toString() + "\n");
        }
        return builder.toString();
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
        String dni="", reason, fecha;
        Date date;
        //TODO PONER ESTO EN UNA EXCEPCION DE CLIENTE NO ENCONTRADO

        System.out.println("Ingrese el DNI del cliente: ");
        dni = scan.nextLine();
        client = collection.search(dni);
        if(client != null){
            System.out.println("Ingrese la razon del turno: ");
            reason = scan.nextLine();
            do {
                System.out.println("Ingrese la fecha del turno: dd/mm/yyyy");
                fecha = scan.nextLine();
                //
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String dateInString = fecha;
                date = null;
                try {
                    date = sdf.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while(fecha.length() != 10);
            turn = new Turn(client, reason, date);
            turnHashMap.put(turn.getTurnNumber(),turn);
        }
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "turnHashMap=" + turnHashMap +
                '}';
    }
}
