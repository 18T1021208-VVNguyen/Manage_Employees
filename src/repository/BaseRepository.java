package repository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.ArrayUtils;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.*;
//import javax.xml.transform.stream.*;
//import org.xml.sax.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.BaseModel;
import model.Department;
import model.Employee;
import model.Title;
import repository.Irepository.IBaseRepository;
import utility.Common;

public class BaseRepository <ID , T extends BaseModel>  implements IBaseRepository<ID, T >{

	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String className ) throws ParserConfigurationException, SAXException, IOException {
		
		List<T> result  = new ArrayList<T>();
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(Common.PATH);

        NodeList rootElement = document.getElementsByTagName(className );
        
        for(int i = 0 ; i < rootElement.getLength(); i++)  {
        	Element eleModel = (Element) rootElement.item(i);
        	String no = eleModel.getElementsByTagName("no").item(0).getTextContent();
        	
        	String name  = eleModel.getElementsByTagName("name").item(0).getTextContent();       	
        	if(eleModel.getNodeName().equalsIgnoreCase("Employee")) {
        		
        		Employee e = new Employee(no, name);
        		NodeList nl =  eleModel.getElementsByTagName("iDTitle");
        		if(nl != null && nl.getLength() > 0) {
        			String iDTitle = nl.item(0).getTextContent();
            		e.setIDTitle(iDTitle); 
        		}
        		result.add((T) e);		
	
        	}
        	else if(eleModel.getNodeName().equalsIgnoreCase("Title")) {
        		
        		Title t = new Title(no , name);
        		NodeList nl = eleModel.getElementsByTagName("iDDepartment");
        		if(nl !=null && nl.getLength() > 0) {
        			String iDDepartment = nl.item(0).getTextContent();
        			t.setIDdepartment(iDDepartment);
        		}
        		
        		result.add((T)t);
        	}
        	else {
        		Department d = new Department(no, name);
        		result.add((T)d);
        	}
        }
        
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T create(T model) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if(model == null) return null;
		
		
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(Common.PATH);
        // <Employees> 
        
        String className = Common.removeString( model.getClass().getName() );
        T modelByID = findByID((ID) model.getNo(), className );
        if(modelByID != null) {
        	return null;
        }
        NodeList root =  document.getElementsByTagName( className + "s");
//        System.out.println(Common.removeString(className));
        // <Employee>
        Element elementModel = document.createElement(className);
        
        Field [] attributes =  model.getClass().getDeclaredFields();
        Field [] attributeSupper = model.getClass().getSuperclass().getDeclaredFields();
        Field [] atrAll =  ArrayUtils.addAll(attributes,attributeSupper);
        for (Field field : atrAll) {
        	
        	Object valueAtr = Common.getValueProperty(model,field.getName());
        	if(valueAtr !=null) {
        		Element elementAttribute = document.createElement(field.getName());	
        		elementAttribute.appendChild( document.createTextNode( valueAtr.toString() )) ;
        		elementModel.appendChild(elementAttribute);
        	}
        	
        }
      
        root.item(0).appendChild(elementModel);
        // add vao File XML 
        Common.sendXML(document);
        
		return model;
	}

	@Override
	public boolean delete(ID id , String className) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(Common.PATH);
        NodeList root =  document.getElementsByTagName(className);
        
       for(int i = 0 ;i < root.getLength() ;i++) {
    	   Element elementModel = (Element) root.item(i);
    	   Element elementNo = (Element) elementModel.getElementsByTagName("no").item(0);
    	   if(elementNo.getTextContent().equals(id)) {
    		   //Employees . remove Employee
    		   elementModel.getParentNode().removeChild(elementModel);
    		   System.out.println("Xoa done !");
    		   Common.sendXML(document);
    		   return true;
    	   }
       }           
       System.out.println("Xoa Fail !");
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean update(T model) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if(model == null) return false;
		
		String className = Common.removeString(model.getClass().getName());
		
		boolean dele =  this.delete((ID) model.getNo(), className);
		if(dele) {
			this.create(model);
			System.out.println("Update Done !");
			return true;
		}
		System.out.println("Update Fail !");
		return false;
	}

	@Override
	public T findByID(ID id , String className) throws ParserConfigurationException, SAXException, IOException {
		List<T> listT = this.findAll(className);
		for(T ele : listT) {
			BaseModel base = (BaseModel) ele;
			if(base.getNo().equalsIgnoreCase(id.toString()) ) {
				return ele;
			}
		}
		return null;
	}
	

	@Override
	public List<T> findByNameOrId(String text, String className) throws Exception {
		// TODO Auto-generated method stub
		List<T> result = new ArrayList<>();
		List<T> listT = this.findAll(className);
		for(T ele : listT) {
			BaseModel base = (BaseModel) ele;
			if(base.getName().equals(text)  || base.getName().contains(text) || text.contains(base.getName())  ) {
				result.add((T) ele);
			}
		}
		return result;
	}
	
public static void main(String[] args) {
	 
		
	}
}
