package model;

public class Department extends BaseModel{

	
	
	
	public Department(String no, String name) {
		super(no, name);
	}

	@Override
	public String print() {
		StringBuilder stringBuilder = new StringBuilder("Department\n");
		stringBuilder.append(super.toString());
		return stringBuilder.toString();
	}

}
