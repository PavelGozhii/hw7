package mate.academy.di;

import mate.academy.dao.*;
import mate.academy.factory.ClientDaoFactory;
import mate.academy.handler.ClientConsoleHandler;
import mate.academy.handler.HumanConsoleHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(FileClientDao.class);
        classes.add(InMemoryClientDao.class);

        Field[] fields = consoleHandlerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(null, checkAccess(classes));
                }
            }
        new ClientConsoleHandler().handle();
    }

    private static Dao checkAccess(ArrayList<Class> classes){
        boolean[] dao = new boolean[classes.size()];
        for(int e = 0; e < classes.size(); e++){
            dao[e] = classes.get(e).isAnnotationPresent(Component.class);
            if(dao[e]){
                System.out.println(classes.get(e).getName() + " allowed.");
            }
        }
        Dao clientDao = ClientDaoFactory.getClientDao(dao[0], dao[1]);
        return clientDao;
    }


    private static void injectDependencyForHuman() throws IllegalAccessException {
        Class consoleHandlerClass = HumanConsoleHandler.class;
        ArrayList<Class> classes = new ArrayList();
        classes.add(FileHumanDao.class);
        classes.add(InMemoryHumanDao.class);
        Field[] fields = consoleHandlerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(null, checkAccess(classes));
            }
        }
        new HumanConsoleHandler().handle();
    }
}
