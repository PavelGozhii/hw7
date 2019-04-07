package mate.academy.di;

import mate.academy.dao.*;
import mate.academy.factory.ClientDaoFactory;
import mate.academy.factory.HumanDaoFactory;
import mate.academy.handler.ClientConsoleHandler;
import mate.academy.handler.HumanConsoleHandler;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Injector {

    public static void create() throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose object: 1 - Client,  2 - Human, 3 - for exit");
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                injectDependencyForClient();
                break;
            case 2:
                injectDependencyForHuman();
                break;
            default:
                System.exit(0);
        }

    }

    private static void injectDependencyForClient() throws IllegalAccessException {
        Class consoleHandlerClass = ClientConsoleHandler.class;
        Class fileClientDaoClass = FileClientDao.class;
        Class inMemoryClientDao = InMemoryClientDao.class;

        Field[] fields = consoleHandlerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                boolean fileDao = fileClientDaoClass.isAnnotationPresent(Component.class);
                if (fileDao) {
                    System.out.println("Working with files is allowed");
                }
                boolean inMemoryDao = inMemoryClientDao.isAnnotationPresent(Component.class);
                if (inMemoryDao) {
                    System.out.println("Working with RAM is allowed");
                }
                Dao clientDao = ClientDaoFactory.getClientDao(fileDao, inMemoryDao);
                field.set(null, clientDao);
            }
        }
        new ClientConsoleHandler().handle();
    }

    private static void injectDependencyForHuman() throws IllegalAccessException {
        Class consoleHandlerClass = HumanConsoleHandler.class;
        Class fileHumanDaoClass = FileHumanDao.class;
        Class inMemoryHumanDaoClass = InMemoryHumanDao.class;

        Field[] fields = consoleHandlerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                boolean fileDao = fileHumanDaoClass.isAnnotationPresent(Component.class);
                if (fileDao) {
                    System.out.println("Working with files is allowed");
                }
                boolean inMemoryDao = inMemoryHumanDaoClass.isAnnotationPresent(Component.class);
                if (inMemoryDao) {
                    System.out.println("Working with RAM is allowed");
                }
                Dao humanDao = HumanDaoFactory.getHumanDao(fileDao, inMemoryDao);
                field.set(null, humanDao);
            }
        }
        new HumanConsoleHandler().handle();
    }
}
