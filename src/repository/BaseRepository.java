package repository;

import java.util.List;

import javax.xml.parsers.*;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.*;
//import javax.xml.transform.stream.*;
//import org.xml.sax.*;
import org.w3c.dom.*;

public class BaseRepository <ID , T>  implements IBaseRepository<ID, T>{

	@Override
	public List<T> findAll() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.newDocument();
			//Element rootEle = dom.get
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T create(T model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(T model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T findByID(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
