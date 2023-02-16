package model;

public class Employee  extends BaseModel{

	 private String iDTitle;

	 
	 
	 public Employee(String no, String name ) {
		super(no,name);
		
	 }


	

	public String getIDTitle() {
		return iDTitle;
	}




	public void setIDTitle(String iDTitle) {
		this.iDTitle = iDTitle;
	}


	@Override
	public String print() {
			StringBuilder stringBuilder = new StringBuilder("Employee\n");
			stringBuilder.append(super.toString());
			stringBuilder.append("\n");
			stringBuilder.append("iDTitle: " + this.iDTitle);
			return stringBuilder.toString();
	}

	

	

}
