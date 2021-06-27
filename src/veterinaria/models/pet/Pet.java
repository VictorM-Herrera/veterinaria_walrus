package veterinaria.models.pet;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private String race;
    private int age;
    private char sex;// male or female
    private float weight;
    private float height;
    private boolean status;

    public Pet() {
        name="";
        race="";
        age=0;
        sex='m';//m de base
        weight=0f;
        height=0f;
        status=true;
    }
    /**
     * Constructor de Mascotas con todos los datos menos "status" que inicia por defecto en true
     * @param name recibe un nombre (String)
     * @param race recibe una raza (String)
     * @param age recibe una edad  (int)
     * @param sex recibe el sexo del animal m o f (char)
     * @param weight recibe el peso (float)
     * @param height reciba la altura (float)
     * */
    public Pet(String name, String race, int age, char sex, float weight, float height)
    {
        this.name=name;
        this.race=race;
        this.age=age;
        this.sex=sex;
        this.weight=weight;
        this.height=height;
        status=true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Nombre: '" + name + '\'' +
                ", Raza: '" + race + '\'' +
                ", Edad: " + age +
                ", Sexo: " + sex +
                ", Peso: " + weight +
                ", Altura: " + height + "\n";
    }
}
