package XML_Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PatientCheck {
    ArrayList <String> patientsData = new ArrayList <>();
    Scanner scanner = new Scanner(System.in);

    public boolean isPatientExist(String name, String secondName, Date date) throws TransformerException {
        boolean isPatientExists = false;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("patients.xml");
            Node root = document.getDocumentElement();
            NodeList patients = root.getChildNodes();
            for (int i = 0; i < patients.getLength(); i++) {
                Node patient = patients.item(i);
                if (patient.getNodeType() != Node.TEXT_NODE) {
                    NodeList patientProps = patient.getChildNodes();
                    for (int j = 0; j < patientProps.getLength(); j++) {
                        Node patientProp = patientProps.item(j);
                        if (patientProp.getNodeType() != Node.TEXT_NODE) {
                            patientsData.add(patientProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    if (patientsData.contains(name) && patientsData.contains(secondName)) {
                        System.out.println("You already have this patient ! Do you want to change patient's heallth status (press 'y' or 'n')?");
                        if (scanner.nextLine().equals("y")) {
                            System.out.println("Current health status is " + patientsData.get(3) + ". Please enter the new one (true or false) : ");
                            String status = scanner.nextLine();
                            for (int j = 0; j < patientProps.getLength(); j++) {
                                Node patientProp = patientProps.item(j);
                                Element eElement = (Element) patientProp;
                                if (patientProp.getNodeType() != Node.TEXT_NODE) {
                                    if ("IsHealthy".equals(eElement.getNodeName())) {
                                        if (patientsData.get(3).equals(eElement.getTextContent())) {
                                            eElement.setTextContent(status);
                                            Transformer transformer = TransformerFactory.newInstance()
                                                    .newTransformer();
                                            DOMSource source = new DOMSource(document);
                                            StreamResult result = new StreamResult(new File("patients.xml"));
                                            transformer.transform(source, result);
                                            System.out.println("Changes saved.");
                                        }
                                    }
                                }
                            }
                        }
                        isPatientExists = true;
                    }
                    patientsData.removeAll(patientsData);
                }
            }
        } catch (
                ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (
                SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (
                IOException ex) {
            ex.printStackTrace(System.out);
        }
        return isPatientExists;
    }
}
