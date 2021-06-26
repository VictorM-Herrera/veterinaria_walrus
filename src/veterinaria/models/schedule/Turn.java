package veterinaria.models.schedule;

import veterinaria.models.client.Client;

import java.io.Serializable;
import java.util.Date;

public class Turn implements Serializable {
    private int turnNumber;
    private Client client;
    private String reason;
    private Date date;

    public Turn() {

    }
}