package XML_Parser;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartMenu {
    Scanner scanner = new Scanner(System.in);

    public int startUpMenu() throws InputMismatchException {
        try {
            System.out.println("Welcome to startup menu !");
            System.out.println("Please make your choise:");
            System.out.println("Press '1' if you want to read the patients from local patient list");
            System.out.println("Press '2' if you want to load the new patients to local patient list ");
            System.out.println("Press '3' if you want to see the remote patients list");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect enter! Please repeat! ");
            scanner.nextLine();
            return startUpMenu();
        }
    }
}
