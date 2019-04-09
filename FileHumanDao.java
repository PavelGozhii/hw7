package mate.academy.dao;

import mate.academy.di.Component;
import mate.academy.model.Model;

import java.io.*;

@Component
public class FileHumanDao implements Dao {

    public void save(Model human) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("storageHuman.dat"))) {
            objectOutputStream.writeObject(human);
        } catch (IOException e) {
            System.out.println("Could not write a person to the database");
        }
    }

    public Model get() {
        try (ObjectInputStream inputObjectStream = new ObjectInputStream(new FileInputStream("storageHuman.dat"))) {
            return (Model) inputObjectStream.readObject();
        } catch (Exception e) {
            System.out.println("The person is not found in the system");
            throw new NullPointerException();
        }
    }
}
