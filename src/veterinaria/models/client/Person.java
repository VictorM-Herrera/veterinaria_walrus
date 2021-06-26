package veterinaria.models.client;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {
    private String name;
    private String lastName;
    private String DNI;
    private String phone;
    private String address;

    public Person() {
        name = "";
        lastName = "";
        DNI = "";
        phone = "";
        address = "";
    }

    public Person(String name, String lastName, String DNI, String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.DNI = DNI;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(DNI, person.DNI) && Objects.equals(phone, person.phone) && Objects.equals(address, person.address);
    }

    @Override
    public String toString() {
        return  "Nombre: '" + name + '\'' +
                ", Apellido: '" + lastName + '\'' +
                ", DNI: '" + DNI + '\'' +
                ", Teléfono: '" + phone + '\'' +
                ", Dirección: '" + address + '\'';
    }
}
