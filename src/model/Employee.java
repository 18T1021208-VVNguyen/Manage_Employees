package model;

public class Employee  extends BaseModel{

	 private Title title;

	 
	 
	 public Employee(String no, String name ) {
		super(no,name);
		
	 }


	public Title getTitle() {
		return title;
	}


	public void setTitle(Title title) {
		this.title = title;
	}


	@Override
	public String print() {
			StringBuilder stringBuilder = new StringBuilder("Employee\n");
			stringBuilder.append("");
			stringBuilder.append("\n");
			stringBuilder.append("title: " + this.title);
			return stringBuilder.toString();
	}

	

	

}
