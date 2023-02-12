package model;

import java.util.List;

public  class Title extends BaseModel{

	
	private Department department;
	
	private List<Employee> employee;
	
	public Title(String no, String name) {
		super(no, name);
		
	}

	

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	



	public List<Employee> getEmployee() {
		return employee;
	}



	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}



	public Department getDepartment() {
		return this.department ;
	}





	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

}
