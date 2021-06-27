package veterinaria.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import veterinaria.models.client.Client;
import veterinaria.models.schedule.Turn;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class jsonUtil {
    private final static String nombreArchivo = "turns.json";

    public static void grabar(JSONArray array) {
        try {
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(array.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void grabar(JSONObject jsonObject) {
        try {
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(jsonObject.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leer()
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }

    public static ArrayList<Turn> jsonToJava(String json)
    {

        ArrayList<Turn> turnArrayList = new ArrayList<Turn>();

        try
        {
            JSONArray turnJSONArray = new JSONArray(json);

            for (int i = 0; i < turnJSONArray.length(); i++)
            {
                JSONObject objClient = new JSONObject();
                JSONObject objTurn = new JSONObject();
                Date date = null;
                objTurn = turnJSONArray.getJSONObject(i);
                objClient = objTurn.getJSONObject("client");


                Client client = new Client(objClient.getString("name"), objClient.getString("lastName"), objClient.getString("DNI"), objClient.getString("phone"), objClient.getString("address"), objClient.getString("paymentMethod"));
                String fechaDesdeJson = objTurn.getString("date");
                String fecha =  fechaDesdeJson.substring(8,10) +"/"+ monthToNumberMonth(fechaDesdeJson.substring(4,7)) + "/"+fechaDesdeJson.substring(24, 28);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String dateInString = fecha;
                date = sdf.parse(dateInString);

                Turn turn = new Turn(client, objTurn.getString("reason"), date);
                turnArrayList.add(turn);
            }

        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return turnArrayList;

    }

    private static String monthToNumberMonth(String month) {
        String result = "";
        switch(month) {
            case "Jan":
                result = "01";
                break;
            case "Feb":
                result = "02";
                break;
            case "Mar":
                result = "03";
                break;
            case "Apr":
                result = "04";
                break;
            case "May":
                result = "05";
                break;
            case "Jun":
                result = "06";
                break;
            case "Jul":
                result = "07";
                break;
            case "Aug":
                result = "08";
                break;
            case "Sep":
                result = "09";
                break;
            case "Oct":
                result = "10";
                break;
            case "Nov":
                result = "11";
                break;
            case "Dec":
                result = "12";
                break;
        }
        return result;
    }

}
