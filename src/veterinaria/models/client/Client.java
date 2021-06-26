package veterinaria.models.client;

import veterinaria.models.pet.PetCollection;

import java.util.Objects;

public class Client extends Person {
    private static int clientsQuantity = 0;
    private int id;
    private String paymentMethod;
    private PetCollection petList;
    private boolean status;

    public Client() {
        super();
        clientsQuantity++;
        id = clientsQuantity;
        paymentMethod = "";
        petList = new PetCollection();
        status = true;
    }

    public Client(String name, String lastName, String DNI, String phone, String address, String paymentMethod) {
        super(name, lastName, DNI, phone, address);
        clientsQuantity++;
        this.id = clientsQuantity;
        this.paymentMethod = paymentMethod;
        this.petList = new PetCollection();
        status = true;
    }

    public static void setClientsQuantity(int clientsQuantity) {
        Client.clientsQuantity = clientsQuantity;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(paymentMethod, client.paymentMethod) && Objects.equals(petList, client.petList);
    }

    @Override
    public String toString() {
        return "[Cliente ID: " + id + "] {" +
                "Estado: " + status +
                ", " + super.toString() +
                ", Método de Pago: '" + paymentMethod + '\'' +
                '}';
    }
}
