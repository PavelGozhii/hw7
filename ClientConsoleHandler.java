package mate.academy.handler;

import mate.academy.dao.Dao;
import mate.academy.di.Inject;
import mate.academy.di.Injector;
import mate.academy.model.Client;

import java.util.Scanner;

public class ClientConsoleHandler{

    @Inject
    private static Dao clientDao;

    public void handle() throws IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - for enter client, 2 - for get client, 3 - return back");
            int consoleChoice = Integer.valueOf(scanner.next());
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


    private void addClientInfo(Scanner scanner) {
        System.out.println("Enter info about client");
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter phone");
        String phone = scanner.next();
        Client client = new Client(name, phone);
        clientDao.save(client);
    }


}
