package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.composite.LeftComposite;
import view.composite.RightComposite;

public class App {

	protected Shell shell;
	public static Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		 display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(777, 558);
		shell.setText("Manage Employee");
		
	
		
		
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
	    composite.setLayout(new GridLayout(4, true));
	    
	    LeftComposite leftComposite = new LeftComposite(composite, SWT.BORDER);
	    leftComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1) );
		
	    
	    RightComposite rightComposite = new RightComposite(composite, SWT.BORDER);
	    GridData gd_rightComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
	    gd_rightComposite.widthHint = 453;
	    rightComposite.setLayoutData(gd_rightComposite );
//	    
//	    RightCompositex rightComposite = new RightCompositex(composite, SWT.BORDER);
//	    rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1) );
//		
		

	}
}
