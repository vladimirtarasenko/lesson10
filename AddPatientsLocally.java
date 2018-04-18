package XML_Parser;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddPatientsLocally implements AddPatientsByDOM {
    Patient patient=new Patient();
    Scanner scanner=new Scanner(System.in);
    PatientCheck check =new PatientCheck();
    private String firstName;
    private String surname;
    private Date birthdate;
    private boolean isHealth;

public boolean addPatients() throws DoublePatientException, TransformerException, IOException {
    System.out.println("Please enter the patient name : ");
    firstName =scanner.nextLine();
    System.out.println("Please enter the patient second name :");
    surname = scanner.nextLine();
    System.out.println("Please enter the patient birthday (in yyyy-mm-dd format) ");
    birthdate = birthday(scanner.nextLine());
    System.out.println("Please enter is the patient ill(true/false) :");
    isHealth = scanner.nextBoolean();
    try {
        if (check.isPatientExist(firstName, surname, birthdate)) throw new DoublePatientException ("This patient already in the list !");
    } catch (DoublePatientException e){
       return XML_Main.mainMenu();
    }
    try {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("patients.xml");
        addNewPatient(document);
    } catch (ParserConfigurationException ex) {
        ex.printStackTrace(System.out);
    } catch (SAXException ex) {
        ex.printStackTrace(System.out);
    } catch (IOException ex) {
        ex.printStackTrace(System.out);
    }
    return true;
}
    @Override
    public  void addNewPatient(Document document) throws TransformerFactoryConfigurationError, DOMException {
        Node root = document.getDocumentElement();
        Scanner scanner=new Scanner(System.in);
        Element patient = document.createElement("Patient");
        Element name = document.createElement("Name");
        name.setTextContent(firstName);
        Element secondName = document.createElement("SecondName");
        secondName.setTextContent(surname);
        Element birthday = document.createElement("Birthday");
        birthday.setTextContent(String.valueOf(birthdate));
        Element isHealthy = document.createElement("IsHealthy");
        isHealthy.setTextContent(String.valueOf(isHealth));
        patient.appendChild(name);
        patient.appendChild(secondName);
        patient.appendChild(birthday);
        patient.appendChild(isHealthy);
        root.appendChild(patient);
        writeDocument(document);
    }
        private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("patients.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public Date birthday(String birthday) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println("The wrong birthday date, please enter again!");
            return birthday(scanner.nextLine());
        }
    }
}

