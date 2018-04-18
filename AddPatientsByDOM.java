package XML_Parser;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;

public interface AddPatientsByDOM {

    public boolean addPatients() throws DoublePatientException, TransformerException, IOException;

    public  void addNewPatient(Document document) throws TransformerFactoryConfigurationError, DOMException;
}
