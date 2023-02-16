package repository;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Employee;
import repository.Irepository.IEmployeeRepository;
import utility.Common;

public class EmployeeRepository extends BaseRepository<String, Employee> implements IEmployeeRepository{

	@Override
	public void updateEmployeeWhenDeleTitle(String idTitle) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(Common.PATH);

        NodeList rootElement = document.getElementsByTagName("Employee" );
        for(int i = 0 ; i<rootElement.getLength() ; i++) {
        	Element eleModel = (Element) rootElement.item(i);
        	NodeList nl =  eleModel.getElementsByTagName("iDTitle");
        	
        	if(nl.getLength() > 0 && nl != null ) {
        		String iDTitle = nl.item(0).getTextContent();
        		
        		if(iDTitle.equalsIgnoreCase(idTitle)) {
        			
        			String no = eleModel.getElementsByTagName("no").item(0).getTextContent();
                	String name  = eleModel.getElementsByTagName("name").item(0).getTextContent();
        			Employee e = new Employee(no, name);
        			this.update(e);
        		}
        	}
        	
        	
//        	String name  = eleModel.getElementsByTagName("name").item(0).getTextContent();       	
        }
		
	}
		
	
		

}
