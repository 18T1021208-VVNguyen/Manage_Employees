package view.composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import model.Department;
import model.Employee;
import model.Title;
import utility.Common;
import view.common.CommonView;

public class Action extends Shell {
	private Text textNo;
	private Text textName;
	//private Text text_2;
	private Shell action;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public Action(Display display, String nameShell, String className) {
		super(display);

		action = new Shell(SWT.CLOSE | SWT.RESIZE | SWT.TITLE);

		action.setSize(513, 333);
		action.setText(nameShell + " " + className);

		action.open();
		action.layout();

		Composite composite = new Composite(action, SWT.NONE);
		composite.setBounds(10, 0, 475, 287);

		Label lblNo = new Label(composite, SWT.NONE);
		lblNo.setBounds(10, 54, 81, 25);
		lblNo.setText("No: ");

		textNo = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textNo.setBounds(119, 51, 327, 31);
		try {
			textNo.setText(Common.generateNodAuto(className));
		} catch (Exception e1) {

		}

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setBounds(10, 103, 81, 25);
		lblName.setText("Name: ");

		Label lblMesage = new Label(composite, SWT.NONE);
		lblMesage.setForeground(display.getSystemColor(SWT.COLOR_RED));

		lblMesage.setBounds(119, 10, 226, 25);
		lblMesage.setVisible(false);

		Label lblCombo;
		Combo comboAdd_Edit;
		//Text textDelete;

		if (nameShell.equals("Add")) {

			textName = new Text(composite, SWT.BORDER);
			textName.setBounds(119, 103, 327, 31);

			Button btnNewButton = new Button(composite, SWT.NONE);
			btnNewButton.setBounds(116, 198, 105, 35);
			btnNewButton.setText("Save");

			if (className.equals("Employee")) {
				lblCombo = new Label(composite, SWT.NONE);
				lblCombo.setBounds(10, 149, 81, 25);
				lblCombo.setText("Title");

				comboAdd_Edit = new Combo(composite, SWT.NONE);
				comboAdd_Edit.setBounds(116, 149, 300, 33);
				comboAdd_Edit.setText("--- No Select --- ");

				List<Title> lTitle;
				try {
					lTitle = CommonView.tiService.findAll();
					for (int i = 0; i < lTitle.size(); i++) {
						comboAdd_Edit.add(lTitle.get(i).getName(), i);
					}

					btnNewButton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (textName.getText().trim().equals("")) {
								lblMesage.setText("Add Faild !");
								// lblMesage
								lblMesage.setVisible(true);
								return;
							}
							Employee emp = new Employee(textNo.getText(), textName.getText());
							if (comboAdd_Edit.getSelectionIndex() != -1) {
								emp.setIDTitle(lTitle.get(comboAdd_Edit.getSelectionIndex()).getNo());
							}
							try {
								Employee checkE = CommonView.epService.create(emp);
								if (checkE == null) {
									lblMesage.setText("Add Faild !");
									
								} else {
									lblMesage.setText("Add Done !");
									
								}
								lblMesage.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});

				} catch (Exception e) {

					e.printStackTrace();
				}

			} else if (className.equals("Title")) {
				lblCombo = new Label(composite, SWT.NONE);
				lblCombo.setBounds(10, 149, 81, 25);
				lblCombo.setText("Department");

				comboAdd_Edit = new Combo(composite, SWT.NONE);
				comboAdd_Edit.setBounds(116, 149, 300, 33);
				comboAdd_Edit.setText("--- No Select --- ");

				List<Department> lDepartement;
				try {
					lDepartement = CommonView.deService.findAll();
					for (int i = 0; i < lDepartement.size(); i++) {
						comboAdd_Edit.add(lDepartement.get(i).getName(), i);
					}

					btnNewButton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (textName.getText().trim().equals("")) {
								lblMesage.setText("Add Faild !");
								// lblMesage
								lblMesage.setVisible(true);
								return;
							}
							Title tl = new Title(textNo.getText(), textName.getText());
							// Vi tri tro hien tai cua Combo
							if (comboAdd_Edit.getSelectionIndex() != -1) {

								tl.setIDdepartment(lDepartement.get(comboAdd_Edit.getSelectionIndex()).getNo());
							}
							try {
								Title checkT = CommonView.tiService.create(tl);
								if (checkT == null) {
									lblMesage.setText("Add Faild !");
									
								} else {
									lblMesage.setText("Add Done !");
									
								}
								lblMesage.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});

				} catch (Exception e) {

					e.printStackTrace();
				}
			} else {
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (textName.getText().trim().equals("")) {
							lblMesage.setText("Add Faild !");
							// lblMesage
							lblMesage.setVisible(true);
							return;
						}
						Department dep = new Department(textNo.getText(), textName.getText());
						try {
							Department checkD = CommonView.deService.create(dep);
							if (checkD == null) {
								lblMesage.setText("Add Faild !");
								
							} else {
								lblMesage.setText("Add Done !");
								
							}
							lblMesage.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});

			}

		}

//		Button btnNewButton = new Button(composite, SWT.NONE);
//		btnNewButton.setBounds(116, 198, 105, 35);
//		btnNewButton.setText("Delete");
//		
//		Button btnNewButton_1 = new Button(composite, SWT.NONE);
//		btnNewButton_1.setBounds(327, 198, 119, 35);
//		btnNewButton_1.setText("Edit");

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
