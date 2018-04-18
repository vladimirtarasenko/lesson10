package XML_Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GetPatientsInfo {

    public void PatientsInfo() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("patients.xml");
            Node root = document.getDocumentElement();
            System.out.println("List of patients:");
            System.out.println();
            NodeList patients = root.getChildNodes();
            for (int i = 0; i < patients.getLength(); i++) {
                Node patient = patients.item(i);
                if (patient.getNodeType() != Node.TEXT_NODE) {
                    NodeList patientProps = patient.getChildNodes();
                    for (int j = 0; j < patientProps.getLength(); j++) {
                        Node patientProp = patientProps.item(j);
                        if (patientProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(patientProp.getNodeName() + ":" + patientProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===============");
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
    }
}



