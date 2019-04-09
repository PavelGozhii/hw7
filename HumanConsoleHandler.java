package mate.academy.handler;

import mate.academy.dao.Dao;
import mate.academy.di.Inject;
import mate.academy.di.Injector;
import mate.academy.model.Human;

import java.util.Scanner;

public class HumanConsoleHandler{

    @Inject
    private static Dao humanDao;


     public void handle() throws IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - for enter human, 2 - for get human, 3 - return back");
            int consoleChoice = Integer.valueOf(scanner.next());
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

    private void addClientInfo(Scanner scanner) {
        System.out.println("Enter info about human");
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter age");
        int age = Integer.valueOf(scanner.next());
        Human human = new Human(name, age);
        humanDao.save(human);
    }
