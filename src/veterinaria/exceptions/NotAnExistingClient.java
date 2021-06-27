package veterinaria.exceptions;

public class NotAnExistingClient extends Exception{
    public NotAnExistingClient(String message){
        super(message);
    }
}
