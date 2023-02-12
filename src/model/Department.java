package model;

import java.util.List;

public class Department extends BaseModel{

	
	
	
	public Department(String no, String name) {
		super(no, name);
	}

	private List<Title> listTitle;
	
	

	public List<Title> getListTitle() {
		return listTitle;
	}



	public void setListTitle(List<Title> listTitle) {
		this.listTitle = listTitle;
	}



	@Override
	public String print() {
		StringBuilder stringBuilder = new StringBuilder("Department\n");
		stringBuilder.append(super.toString());
		return stringBuilder.toString();
	}

}
