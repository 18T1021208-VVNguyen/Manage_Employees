package service;

import java.util.List;

import model.Title;
import repository.TitleRepository;
import service.Iservice.ITitleService;

public class TitleService implements ITitleService {

	public static TitleRepository titleRepository;
	
	public static TitleRepository getInstanceTitleRepository() {
		if(TitleService.titleRepository == null )
		{
			TitleService.titleRepository = new TitleRepository();
		}
		
		return TitleService.titleRepository;
	}
	
	public final static  String CLASS_NAME = "Title";
	@Override
	public List<Title> findAll() throws Exception {
		return getInstanceTitleRepository().findAll(CLASS_NAME);
	}

	@Override
	public Title create(Title model) throws Exception {
		return getInstanceTitleRepository().create(model);
	}

	@Override
	public boolean delete(Title title) throws Exception {
		if(title.getIDdepartment() != null) {
			return false;
		}
		boolean result =  getInstanceTitleRepository().delete(title.getNo(), CLASS_NAME);
		
		// Update Employee. 
		EmployeeService.getInstanceEmployeeReposi().updateEmployeeWhenDeleTitle(title.getNo());
		
		return result;
	}

	@Override
	public boolean update(Title model) throws Exception {
		return getInstanceTitleRepository().update(model);
	}

	@Override
	public Title findByID(String id) throws Exception {
		return getInstanceTitleRepository().findByID(id, CLASS_NAME);
	}

	@Override
	public List<Title> findByNameOrId(String text) throws Exception {
		return getInstanceTitleRepository().findByNameOrId(text, CLASS_NAME);
	}

}
