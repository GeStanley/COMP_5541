package ui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * This class handles the creation of the gui for the calcul-o-matic program
 * 
 * @author Mike
 */
public class Gui extends JFrame implements PropertyChangeListener {

	private InputLineComponent inputLine;
	private JTable spreadsheet;
	private ButtonComponent buttonComponent;

	
	/**
	 * This sets up the Gui aspect by taking the input line and the spreadsheet
	 * and displaying them.
	 * 
	 */
	public Gui() {
		// Frame setup
		super("Calcul-O-Matic");
		setSize(600, 255);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Grid setup
		inputLine = new InputLineComponent(null);
		spreadsheet = new SpreadSheet();
		JScrollPane scrollPane = new JScrollPane(spreadsheet);
		spreadsheet.setFillsViewportHeight(true);
		
		JTable rowTable = new RowTable(spreadsheet);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		
		buttonComponent = new ButtonComponent();
		
		inputLine.addPropertyChangeListener(this);
		spreadsheet.addPropertyChangeListener(this);
		buttonComponent.addPropertyChangeListener(this);
		
		JPanel menuAndInput = new JPanel();
		
		menuAndInput.add(buttonComponent);
		menuAndInput.add(inputLine);
		
		add(menuAndInput, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

	}

	/**
	 * This handeles the coordination between the input line and the spreadsheet
	 * 
	 * An "input" change event comes from the input line, whereas a "select"
	 * change comes from a change in the selected cell.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {

		if (e.getPropertyName().equals("input")) {
			String in = (String) e.getNewValue();
			
			
			if ( ((SpreadSheet) spreadsheet).isSelected() ) { 
				System.out.println("input " + in);
				((SpreadSheet) spreadsheet).setFormulaOfSelectedCell( in );
			}
			

		} else if (e.getPropertyName().equals("select")) {
			System.out.println("select");
			String s = (String) e.getNewValue();

			inputLine.setText(s);
		
		} else if  (e.getPropertyName().equals("save")) {
			System.out.println("save");
		
		} else if  (e.getPropertyName().equals("saveAs")) {
			System.out.println("saveAs");
		
		} else if  (e.getPropertyName().equals("load")) {
			System.out.println("load");
			
		}

	}

	/**
	 * Initializes the frame, and displays it.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame frame = new Gui();
		frame.setVisible(true);
	}

}
