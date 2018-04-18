package XML_Parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPatientsRemotelyByStAX implements AddPatientByStAX {

    URL myURL = new URL("https://raw.githubusercontent.com/vladimirtarasenko/lesson5/master/patients_remote.xml");
    boolean bFirstName = false;
    boolean bSecondName = false;
    boolean bBirthday = false;
    boolean bHealthy = false;

    public GetPatientsRemotelyByStAX() throws MalformedURLException {
    }

    public void getRemotely() throws IOException {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new InputStreamReader(myURL.openStream()));
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("Name")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("SecondName")) {
                            bSecondName = true;
                        } else if (qName.equalsIgnoreCase("Birthday")) {
                            bBirthday = true;
                        } else if (qName.equalsIgnoreCase("isHealthy")) {
                            bHealthy = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bFirstName) {
                            System.out.println("Name: " + characters.getData());
                            bFirstName = false;
                        }
                        if (bSecondName) {
                            System.out.println("Second Name: " + characters.getData());
                            bSecondName = false;
                        }
                        if (bBirthday) {
                            System.out.println("Bithday: " + characters.getData());
                            bBirthday = false;
                        }
                        if (bHealthy) {
                            System.out.println("isHealthy: " + characters.getData());
                            bHealthy = false;
                            System.out.println("=====================");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
