package model;

public abstract class BaseModel {
	protected String no;
	protected  String name;
	
	
	public BaseModel(String no, String name) {
		this.no = no;
		this.name = name;
	}
	
	

	public String getNo() {
		return no;
	}


	
	public void setNo(String no) {
		this.no = no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BaseModel [No=" + no + ", Name=" + name + "]";
	}
	
	public abstract String print();
	
}
