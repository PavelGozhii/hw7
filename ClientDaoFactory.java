package mate.academy.factory;

import mate.academy.dao.Dao;
import mate.academy.dao.FileClientDao;
import mate.academy.dao.InMemoryClientDao;
import mate.academy.service.PropertyLoader;

import java.io.IOException;

public class ClientDaoFactory {

    private static final Dao inMemoryDao = new InMemoryClientDao();
    private static final Dao fileDao = new FileClientDao();

    public static Dao getClientDao(boolean isFileDao, boolean isInMemoryDao) {
        try {
            String dbName = PropertyLoader.getProperty("db.name");
            if (dbName.equals("memory") && isInMemoryDao) {
                return inMemoryDao;
            }
        } catch (IOException e) {
            System.out.println("There is no access to the file");
        }
        if (isFileDao) {
            return fileDao;
        } else {
            throw new ComponentNotFoundException();
        }
    }
}
