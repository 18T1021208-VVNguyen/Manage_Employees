package model;


public  class Title extends BaseModel{

	
	private String iDDepartment;
	
	//private List<Employee> employee;
	
	public Title(String no, String name) {
		super(no, name);
		
	}





	public String getIDdepartment() {
		return iDDepartment;
	}





	public void setIDdepartment(String iDdepartment) {
		this.iDDepartment = iDdepartment;
	}





	@Override
	public String print() {
		StringBuilder stringBuilder = new StringBuilder("Title\n");
		stringBuilder.append(super.toString());
		stringBuilder.append("\n");
		stringBuilder.append("iDDepartment: " + this.iDDepartment);
		return stringBuilder.toString();
		
	}

}
