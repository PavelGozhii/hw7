package mate.academy.handler;

import mate.academy.dao.Dao;
import mate.academy.di.Inject;
import mate.academy.di.Injector;
import mate.academy.model.Client;

import java.util.Scanner;

public class ClientConsoleHandler implements ConsoleHandler{

    @Inject
    private static Dao clientDao;

    public static void handle() throws IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - если вносите клиента, 2 - если получаете клиента, 3 - для выхода");
            int consoleChoice = scanner.nextInt();
            switch (consoleChoice) {
                case 1:
                    addClientInfo(scanner);
                    break;
                case 2:
                    System.out.println(clientDao.get());
                    break;
                case 3:
                    Injector.create();
            }
        }
    }


    private static void addClientInfo(Scanner scanner) {
        System.out.println("Введите информацию о клиенте");
        System.out.println("Введите имя");
        String name = scanner.next();
        System.out.println("Введите телефон");
        String phone = scanner.next();
        Client client = new Client(name, phone);
        clientDao.save(client);
    }


}
