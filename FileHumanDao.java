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
            System.out.println("Не удалось записать человека в базу");
        }
    }

    public Model get() {
        try (ObjectInputStream inputObjectStream = new ObjectInputStream(new FileInputStream("storageHuman.dat"))) {
            return (Model) inputObjectStream.readObject();
        } catch (Exception e) {
            System.out.println("Чееловек не найден в системе");
            return null;
        }
    }
}
