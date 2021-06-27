package veterinaria.models.schedule;

import veterinaria.models.client.Client;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Turn implements Serializable {
    private static int turnsQuantity = 0;
    private int turnNumber;
    private Client client;
    private String reason;
    private Date date;

    public Turn() {
        turnsQuantity++;
        turnNumber = turnsQuantity;
        client = new Client();
        reason = "";
        date = null;
    }

    public Turn(Client client, String reason, Date date) {
        turnsQuantity++;
        this.turnNumber = turnsQuantity;
        this.client = client;
        this.reason = reason;
        this.date = date;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "{Número de Turno: " + turnNumber +
                ", Cliente: " + client.getName() + " " + client.getLastName() +
                ", DNI: " + client.getDNI() +
                ", Motivo: '" + reason + '\'' +
                ", Fecha: " + date.toInstant().toString().substring(8, 10) + "/" + date.toInstant().toString().substring(5, 7) + "/" + date.toInstant().toString().substring(0,4) +
                '}';
    }
}