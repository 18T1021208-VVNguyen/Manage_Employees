package service;

import java.util.List;

import model.Employee;
import repository.EmployeeRepository;
import service.Iservice.IEmployeeService;

public class EmployeeService implements IEmployeeService{
	
	public static EmployeeRepository employeeRepository;
	
	public static EmployeeRepository getInstanceEmployeeReposi() {
		if(EmployeeService.employeeRepository == null )
		{
			EmployeeService.employeeRepository = new EmployeeRepository();
		}
		
		return EmployeeService.employeeRepository;
	}
	
	public final static  String CLASS_NAME = "Employee";
	
	@Override
	public List<Employee> findAll() throws Exception {
		return getInstanceEmployeeReposi().findAll(CLASS_NAME);
	}

	@Override
	public Employee create(Employee model) throws Exception {
		return getInstanceEmployeeReposi().create(model);
	}

	@Override
	public boolean delete(Employee e) throws Exception {
		if(e.getIDTitle() != null) {
			return false;
		}
		return getInstanceEmployeeReposi().delete(e.getNo(), CLASS_NAME);
	}

	@Override
	public boolean update(Employee model) throws Exception {
		return getInstanceEmployeeReposi().update(model);
	}

	@Override
	public Employee findByID(String id) throws Exception {
		return getInstanceEmployeeReposi().findByID(id, CLASS_NAME);
	}

	@Override
	public List<Employee> findByNameOrId(String text) throws Exception {
		return getInstanceEmployeeReposi().findByNameOrId(text, CLASS_NAME);
		
	}

}
