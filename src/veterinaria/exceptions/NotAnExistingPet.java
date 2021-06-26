package veterinaria.exceptions;

public class NotAnExistingPet extends Exception{
    public NotAnExistingPet(String message){
        super(message);
    }
}
