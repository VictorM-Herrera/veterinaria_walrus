package veterinaria.models.client;

import veterinaria.exceptions.NotAClientObjectException;
import veterinaria.util.ICollection;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class ClientCollection<E extends Person> implements ICollection, Serializable {
    private HashSet<E> clientSet;
    File file;

    public ClientCollection() {
        clientSet = new HashSet<E>();
    }

    @Override
    public void add(Object obj) {
        try {
            if (obj instanceof Client) {
                clientSet.add((E) obj);
            } else {
                throw new NotAClientObjectException("El objeto no es un cliente.");
            }
        }catch(NotAClientObjectException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String showCollection() {
        StringBuilder builder = new StringBuilder();
        Iterator<E> it = clientSet.iterator();
        while (it.hasNext())
        {
            Client aux = (Client)it.next();
            if(aux.isStatus()) {
                builder.append(aux).append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public void showSpecific(int data) {

    }

    public Client search(String DNI) {
        Iterator<E> it = clientSet.iterator();
        Client found = null;
        while (it.hasNext())
        {
            Client aux = (Client)it.next();
            if(aux.getDNI().equals(DNI)) {
                found = aux;
                break;
            }
        }
        return found;
    }

    public void remove(E obj) {
        if(obj instanceof Client) {
            clientSet.remove(obj);
        }
    }

    public void collectionToFile() {
        file = new File("Clients.dat");
        if (!file.exists()){
            try{
                file.createNewFile();
                System.out.println(file.getName() + " ha sido creado.");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream("Clients.dat");

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientSet);
            oos.close();
            System.out.println("Colección guardada en el archivo.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileToCollection() {
        file = new File("Clients.dat");
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream("Clients.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashSet<E> clubs = (HashSet<E>) ois.readObject();
                ois.close();
                System.out.println("Archivo Clientes Cargado.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
