package veterinaria.models.schedule;

import veterinaria.models.client.Client;

import java.io.Serializable;
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
        date = new Date();
    }

    public Turn(Client client, String reason) {
        turnsQuantity++;
        this.turnNumber = turnsQuantity;
        this.client = client;
        this.reason = reason;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}