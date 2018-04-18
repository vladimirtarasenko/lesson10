package XML_Parser;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class XML_Main {
    static Scanner scanner = new Scanner(System.in);
    static StartMenu menu = new StartMenu();
    static AddPatientsLocally patient = new AddPatientsLocally();
    static GetPatientsInfo patientsInfo = new GetPatientsInfo();
    static GetPatientsRemotelyByStAX patientsRemotely;

    static {
        try {
            patientsRemotely = new GetPatientsRemotelyByStAX();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public XML_Main() throws MalformedURLException {
    }

    public static boolean mainMenu() throws TransformerException, DoublePatientException, IOException {
        do {
            switch (menu.startUpMenu()) {
                case 1:
                    patientsInfo.PatientsInfo();
                    break;
                case 2:
                    do {
                        patient.addPatients();
                       // scanner.nextLine();
                        System.out.println("Do you want to add one more patient (enter 'y' or 'n') ? ");
                    } while (scanner.nextLine().equals("y"));
                    break;
                case 3:
                    patientsRemotely.getRemotely();
                    break;
            }
            System.out.println("Do you want to contitnue the work with patients list (press 'y' or 'n') ? ");
        } while (scanner.nextLine().equals("y"));
        return false;
    }
    public static void main(String[] args) throws IOException, DoublePatientException, TransformerException {
        if (!XML_Main.mainMenu()) {
            System.out.println("See you next time!");
        }
    }
}




