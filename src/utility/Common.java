package utility;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import model.Department;
import model.Employee;
import model.Title;
import service.DepartmentService;
import service.EmployeeService;
import service.TitleService;

public final class Common {
	public final static String PATH = "Data.xml";

	public static Object getValueProperty(Object object, String nameProperty) {
		if (object instanceof Employee) {
			Employee employee = (Employee) object;
			if (nameProperty.equalsIgnoreCase("iDTitle")) {
				return employee.getIDTitle();
			} else if (nameProperty.equalsIgnoreCase("no")) {
				return employee.getNo();
			} else {
				return employee.getName();
			}
		} else if (object instanceof Department) {
			Department department = (Department) object;
			if (nameProperty.equalsIgnoreCase("no")) {
				return department.getNo();
			} else {
				return department.getName();
			}
		} else {
			Title title = (Title) object;
			if (nameProperty.equalsIgnoreCase("iDDepartment")) {
				return title.getIDdepartment();
			} else if (nameProperty.equalsIgnoreCase("no")) {
				return title.getNo();
			} else {
				return title.getName();
			}

		}

	}

	public static boolean checkObjectInModel(Object objectModel) {
		if (objectModel instanceof Department || objectModel instanceof Employee || objectModel instanceof Title) {
			return true;
		}
		return false;
	}

	public static void sendXML(Document document) throws TransformerException {

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult(Common.PATH);
		transformer.transform(source, result);

	}

	public static String removeString(String model) {
		final String textModel = "model.";
		String res = model.substring(textModel.length());
		return res;
	}
			
		
		


	public static void main(String[] args) throws Exception {
//		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//
//		Document document = documentBuilder.parse("./Server.xml");
//		NodeList root = document.getElementsByTagName("employees");
//
//		Element e = document.createElement("server");
//
//		e.appendChild(document.createTextNode("123"));
//		root.item(0).appendChild(e);
//
//		DOMSource source = new DOMSource(document);
//
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		StreamResult result = new StreamResult("server.xml");
//		transformer.transform(source, result);
//
//		System.out.println();
//		String month = "1000";
//		month = Common.addZeroString(month, 3);
//		System.out.println(month);
		
		System.out.println(Common.generateNodAuto("Department"));

	}
	
	public static String generateNodAuto(String className) throws ParserConfigurationException, SAXException, IOException {
		StringBuilder result = null ;
		final String noDepartment = "DE";
		final String noTitle = "T";
		if(className.equalsIgnoreCase("Employee")) {
			
			Integer year = YearMonth.now().getYear();
			String yearS = year.toString();
			
			Integer month = YearMonth.now().getMonth().getValue();
			String monthS = month.toString();
			
			List<Employee> ls = EmployeeService.getInstanceEmployeeReposi().findAll(className);
			Integer numSizeEmployee =  ls.size() + 1;
			String numSizeEmpS = numSizeEmployee.toString();
			
			result = new StringBuilder(yearS.substring(yearS.length() -2 ) );
			result.append(Common.addZeroString(monthS, 2) );
			result.append(Common.addZeroString(numSizeEmpS, 3));
			
			 
		}
		else if(className.equals("Title")) {
			
			result = new StringBuilder(noTitle);
			List<Title> listTitle = TitleService.getInstanceTitleRepository().findAll(className);
			Integer numSizeTitle =  listTitle.size() + 1;
			String numSizeTitleS = numSizeTitle.toString();
			
			result.append(Common.addZeroString(numSizeTitleS, 3) );
		}
		else if(className.equals("Department")) {
			result = new StringBuilder(noDepartment);
			
			List<Department> listDepartment = DepartmentService.getInstanceDepartmentRepository().findAll(className);
			Integer numSizeDepartment =  listDepartment.size() + 1;
			String numSizeDepartmentS = numSizeDepartment.toString();
			
			result.append(Common.addZeroString(numSizeDepartmentS, 3));
			
		}
		return result.toString();
	}
	
	public static String addZeroString(String text , int minStep) {
		int step = 0;
		if(text.length() < minStep) {
			step = minStep - text.length();
		}
		while(step > 0) {
			text = "0" + text;			
			step--;
		}
		
		return text;
	}

}
