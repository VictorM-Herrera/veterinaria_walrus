package veterinaria.models.schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import veterinaria.exceptions.NotAnExistingClient;
import veterinaria.exceptions.NotAnExistingTurn;
import veterinaria.models.client.Client;
import veterinaria.models.client.ClientCollection;
import veterinaria.util.ICollection;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase de Agenda
 */
public class Schedule implements ICollection, Serializable {
    // La clave del mapa es el numero de los turnos.
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
            if(turnHashMap.get((Integer)entry.getKey()).isStatus()) {
                builder.append("[" + entry.getKey() + "] " + entry.getValue().toString() + "\n");
            }
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

        System.out.println("Ingrese el DNI del cliente: ");
        dni = scan.nextLine();
        try {
            client = collection.search(dni);
            if(client != null){
                do {
                    System.out.println("Ingrese la razon del turno: ");
                    reason = scan.nextLine();
                }while (reason.length()==0);
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
        } catch (NotAnExistingClient notAnExistingClient) {
            notAnExistingClient.printStackTrace();
        }

    }


    public JSONArray turnMapToJson() {

        JSONArray result = null;
        ArrayList<Turn> turnsArrayList = new ArrayList<>(turnHashMap.values());
        try {
            JSONArray turnJSONArray = new JSONArray();

            for (int i = 0; i < turnsArrayList.size(); i++) {
                JSONObject clientJSONObject = new JSONObject();
                JSONObject turnJSONObject = new JSONObject();
                clientJSONObject.put("name", turnsArrayList.get(i).getClient().getName());
                clientJSONObject.put("lastName", turnsArrayList.get(i).getClient().getLastName());
                clientJSONObject.put("DNI", turnsArrayList.get(i).getClient().getDNI());
                clientJSONObject.put("phone", turnsArrayList.get(i).getClient().getPhone());
                clientJSONObject.put("address", turnsArrayList.get(i).getClient().getAddress());
                clientJSONObject.put("paymentMethod", turnsArrayList.get(i).getClient().getPaymentMethod());

                turnJSONObject.put("turnNumber", turnsArrayList.get(i).getTurnNumber());
                turnJSONObject.put("client", clientJSONObject);
                turnJSONObject.put("reason", turnsArrayList.get(i).getReason());
                turnJSONObject.put("date", turnsArrayList.get(i).getDate());
                turnJSONObject.put("status",turnsArrayList.get(i).isStatus());

                turnJSONArray.put(turnJSONObject);
            }
            result = turnJSONArray;
        }catch(JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteTurn(int turnNumber) throws NotAnExistingTurn {
        if (turnHashMap.containsKey(turnNumber)) {
            turnHashMap.get(turnNumber).setStatus(false);
        }else{
            throw new NotAnExistingTurn("El turno ingresado no existe!");
        }
    }

    public void modifyTurn(int turnNumber, String newReason) throws NotAnExistingTurn{
        if(turnHashMap.containsKey(turnNumber)) {
            turnHashMap.get(turnNumber).setReason(newReason);
        }else{
            throw new NotAnExistingTurn("El turno ingresado no existe!");
        }
    }
    @Override
    public String toString() {
        return "Schedule{" +
                "turnHashMap=" + turnHashMap +
                '}';
    }
}
