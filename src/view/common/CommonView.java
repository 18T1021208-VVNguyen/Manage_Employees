package view.common;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import model.Department;
import model.Employee;
import model.Title;
import service.DepartmentService;
import service.EmployeeService;
import service.TitleService;
import utility.Common;
import view.composite.RightComposite;

public class CommonView {
	
	public static EmployeeService epService = new EmployeeService();
	public static DepartmentService deService = new DepartmentService();
	public static TitleService tiService = new TitleService();
	public static Map<String, String[]> mapAtr;
	static {
	
		mapAtr = new HashMap<>();
		String[] lisEmp  = new String[]{"No" , "Name" , "Title" ,  };
		String[] listDep = new String[] {"No" , "Name" , };
		String[] listTitle = new String[] {"No", "Name" , "Department" ,};
		mapAtr.put("Employee", lisEmp);
		mapAtr.put("Title", listTitle);
		mapAtr.put("Department", listDep);
	}
	public static void reRenderNameColumn(String className ) {
		TableColumn[] tb=  RightComposite.table.getColumns();
		for(int i= 0 ; i< tb.length; i++) {
			
			tb[i].dispose();
 		}
		String[] listAtr = CommonView.mapAtr.get(className);
		
		for(String item : listAtr) {
			TableColumn  column = new TableColumn(RightComposite.table, SWT.NONE);
			column.setWidth(129);
 			column.setText(item);
 					
 		}

	}
	
	public static void removeAllTableItem() {
		TableItem[] tI =  RightComposite.table.getItems();
		for(int i = 0 ; i< tI.length; i++) {
			tI[i].dispose();
		}
	}
	public static void reRenderData( String className) {
		try {
			
			removeAllTableItem();
			
			
			if(className.equals("Employee")) {
				List<Employee> e = epService.findAll();
				createTableItem(e);			 
			}
			else if(className.equals("Department"))
			{
				List<Department> e = deService.findAll();
				createTableItem(e);	
			}
			else if(className.equals("Title")) {
				List<Title> e = tiService.findAll();
				createTableItem(e);	
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static <T> void createTableItem(List<T> lt) {
		if(lt.size() < 1 ) return;
		try {
			Field [] attributes =  lt.get(0).getClass().getDeclaredFields();
	        Field [] attributeSupper = lt.get(0).getClass().getSuperclass().getDeclaredFields();
	        Field [] atrAll =  ArrayUtils.addAll(attributeSupper,attributes);
	        // hang
			for(int i = 0 ; i < lt.size(); i++) {
				
				// cot
				TableItem tableI = new TableItem(RightComposite.table,SWT.None , i);
			 	for(int j = 0 ;j <  atrAll.length ;j ++) {
//			 		if(j == atrAll.length) {
//			 			tableI.setText(j,"Edit");
//			 			continue;
//			 		}
			 		 
			 	// ten thuoc tinh
			 	  String nameAtr = atrAll[j].getName();
			 		// gia tri thuoc tinh
			 	  Object valueAtr = Common.getValueProperty(lt.get(i),nameAtr );
			 	  
			 	  // Neu la Employee co atr nay
			 	  if(nameAtr.equalsIgnoreCase("iDTitle") ) {
			 		  if(valueAtr != null) {
			 			 Title tie =  tiService.findByID(valueAtr.toString());
				 		 tableI.setText(j,tie.getName());
			 			
			 		  }
			 		  else {
			 			  tableI.setText(j,"Null");
			 		  }
			 			 
			 	  }
			 	  else if(nameAtr.equalsIgnoreCase("iDDepartment")) {
//			 		 Department dep1 =  deService.findByID(valueAtr.toString());
//			 		 if(dep1 == null)
//			 		 System.out.println(dep1.getName());
			 		 if(valueAtr != null) {
			 			 Department dep =  deService.findByID(valueAtr.toString());
				 		 tableI.setText(j,dep.getName());
				 		
			 			
			 		  }
			 		  else {
			 			  tableI.setText(j,"Null");
			 		  }
			 	  }
			 	  else {
			 		  tableI.setText(j,valueAtr.toString());
			 	  }
			 			
			 	}
		 }
		}
		catch(Exception e) {
			
		}
		
	}
}
