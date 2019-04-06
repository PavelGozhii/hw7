package mate.academy.handler;

import mate.academy.dao.Dao;
import mate.academy.di.Inject;
import mate.academy.di.Injector;
import mate.academy.model.Human;

import java.util.Scanner;

public class HumanConsoleHandler implements ConsoleHandler{

    @Inject
    private static Dao humanDao;


     public static void handle() throws IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - если внести человека, 2 - если получить человека, 3 - для вихода");
            int consoleChoice = scanner.nextInt();
            switch (consoleChoice) {
                case 1:
                    addClientInfo(scanner);
                    break;
                case 2:
                    System.out.println(humanDao.get());
                    break;
                case 3:
                    Injector.create();
            }
        }
    }

    private static void addClientInfo(Scanner scanner) {
        System.out.println("Введите информацию о человеке");
        System.out.println("Введите имя");
        String name = scanner.next();
        System.out.println("Введите количество лет");
        int age = scanner.nextInt();
        Human human = new Human(name, age);
        humanDao.save(human);
    }

}
