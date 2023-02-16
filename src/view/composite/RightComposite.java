package view.composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import model.Department;
import model.Employee;
import model.Title;
import view.common.CommonView;

public class RightComposite extends Composite {
	public static Text text;
	public static Table table;
	public static Label lblNewLabel;
	public static Composite compositeData;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RightComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(3, 3, 538, 94);
		
		Label lblSearch = new Label(composite, SWT.NONE);
		lblSearch.setBounds(10, 10, 125, 25);
		lblSearch.setText("Search Name");
		
		text = new Text(composite, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.keyCode == 13) {
					System.out.println(lblNewLabel.getText());
					switch (lblNewLabel.getText()) {
					case "Employee":
						try {
							List<Employee> lE = CommonView.epService.findByNameOrId(text.getText());
							CommonView.reRenderNameColumn("Employee");
							CommonView.removeAllTableItem();						
							CommonView.createTableItem(lE);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					case "Department":
						try {
							List<Department> lD = CommonView.deService.findByNameOrId(text.getText());
							CommonView.reRenderNameColumn("Department");
							CommonView.removeAllTableItem();
							CommonView.createTableItem(lD);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Title":
						try {
							List<Title> lT = CommonView.tiService.findByNameOrId(text.getText());
							CommonView.reRenderNameColumn("Title");
							CommonView.removeAllTableItem();
							CommonView.createTableItem(lT);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
			}
		});
		text.setBounds(10, 41, 518, 43);
		
		compositeData = new Composite(this, SWT.NONE);
		compositeData.setBounds(3, 97, 538, 389);
		
		
		
		lblNewLabel = new Label(compositeData, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 118, 25);
		lblNewLabel.setText("Employee");
		
		// init table 
		Table table_1 = new Table(compositeData, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int index = RightComposite.table.getSelectionIndex();
				if( index == -1)
				{
					return;
				}
				 new Action(Display.getCurrent(), "Edit And Delete" ,lblNewLabel.getText() );
				
			}
		});
		RightComposite.table  = table_1;
		RightComposite.table.setBounds(10, 50, 518, 263);
		RightComposite.table.setHeaderVisible(true);
		RightComposite.table.setLinesVisible(true);
		
		CommonView.reRenderNameColumn("Employee");
		CommonView.reRenderData("Employee");
		
		
		Button btnAdd = new Button(compositeData, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Action(getDisplay(), "Add", lblNewLabel.getText());
			}
		});
		btnAdd.setBounds(10, 331, 105, 35);
		btnAdd.setText("Add");
		}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
