package repository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Title;
import repository.Irepository.ITitleRepository;
import utility.Common;

public class TitleRepository extends BaseRepository<String, Title> implements ITitleRepository{

	@Override
	public void updateTitleWhenDeleDepartment(String idDepartment) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(Common.PATH);

        NodeList rootElement = document.getElementsByTagName("Title" );
        for(int i = 0 ; i<rootElement.getLength() ; i++) {
        	Element eleModel = (Element) rootElement.item(i);
        	NodeList nl =  eleModel.getElementsByTagName("iDDepartment");
        	
        	if(nl.getLength() > 0 && nl != null ) {
        		String iDDepartment = nl.item(0).getTextContent();
        		
        		if(iDDepartment.equalsIgnoreCase(idDepartment)) {
        			
        			String no = eleModel.getElementsByTagName("no").item(0).getTextContent();
                	String name  = eleModel.getElementsByTagName("name").item(0).getTextContent();
        			Title t = new Title(no, name);
        			this.update(t);
        		}
        	}     	
        }
		
	}

}
