package view.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import view.common.CommonView;

public class LeftComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LeftComposite(Composite parent, int style) {
		super(parent, style);
		setLayout( new GridLayout(1, true));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1) );
	     composite.setLayout(new GridLayout(1, true));
	     
	     Button btnEmployee = new Button(composite, SWT.BUTTON1);
	     btnEmployee.addSelectionListener(new SelectionAdapter() {
	     	@Override
	     	public void widgetSelected(SelectionEvent e) {
	     		RightComposite.lblNewLabel.setText("Employee");	  
	     	 CommonView.reRenderNameColumn("Employee");
	     	CommonView.reRenderData("Employee");
	     		 
	     		
	     	}
	     });
	     btnEmployee.setText("Employee");
	     btnEmployee.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
	     
	     Button btnTitle = new Button(composite, SWT.BUTTON1);
	     btnTitle.addSelectionListener(new SelectionAdapter() {
	     	@Override
	     	public void widgetSelected(SelectionEvent e) {
	     		RightComposite.lblNewLabel.setText("Title");
	     		CommonView.reRenderNameColumn("Title");
	     		CommonView.reRenderData("Title");

	     	}
	     });
	     btnTitle.setText("Title");
	     btnTitle.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
	     
	     Button btnDepartment = new Button(composite, SWT.BUTTON1);
	     btnDepartment.addSelectionListener(new SelectionAdapter() {
	     	@Override
	     	public void widgetSelected(SelectionEvent e) {
	     		RightComposite.lblNewLabel.setText("Department");
	     		CommonView.reRenderNameColumn("Department");
	     		CommonView.reRenderData("Department");
	     	}
	     });
	     btnDepartment.setText("Department");
	     btnDepartment.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
	     

	     
	     
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
