package mate.academy.dao;

import mate.academy.di.Component;
import mate.academy.model.Model;

import java.io.*;

@Component
public class FileClientDao implements Dao {

    public void save(Model client) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("storageClient.dat"))) {
            objectOutputStream.writeObject(client);
        } catch (IOException e) {
            System.out.println("Не удалось записать клиента в базу");
        }
    }

    public Model get() {
        try (ObjectInputStream inputObjectStream = new ObjectInputStream(new FileInputStream("storageClient.dat"))) {
            return (Model) inputObjectStream.readObject();
        } catch (Exception e) {
            System.out.println("Клиент не найден в системе");
            return null;
        }
    }
}
