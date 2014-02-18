package ui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import structure.Table;


/**
 * This class handles the creation of the gui for the calcul-o-matic program
 * 
 * @author Mike
 */
public class Gui extends JFrame implements PropertyChangeListener {

	private static SaveFile saved;
	private InputLineComponent inputLine;
	private static JTable spreadsheet;
	private ButtonComponent buttonComponent;
	private JScrollPane scrollPane;

	
	/**
	 * This sets up the GUI aspect by taking the input line and the spreadsheet
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

<<<<<<< HEAD
		// Grid setup
		inputLine = new InputLineComponent();
=======
		// GUI setup
		buttonComponent = new ButtonComponent();
		inputLine = new InputLineComponent(null);
		
>>>>>>> GUI
		spreadsheet = new SpreadSheet();
		JScrollPane scrollPane = new JScrollPane(spreadsheet);
		spreadsheet.setFillsViewportHeight(true);
		
		JTable rowTable = new RowTable(spreadsheet);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		
		
		
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
	 * This handles the coordination between the input line and the spreadsheet
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
				spreadsheet.validate();
				spreadsheet.repaint();
			}
			
		} else if (e.getPropertyName().equals("select")) {
			System.out.println("select");
			String s = (String) e.getNewValue();
			inputLine.setText(s);
		
		} else if  (e.getPropertyName().equals("save")) {
			String location = (String) e.getNewValue();
			System.out.println("save " + location);
			String msg = save( location );
			inputLine.setMsg(msg);
		
		} else if  (e.getPropertyName().equals("load")) {
			String location = (String) e.getNewValue();
			System.out.println("load " + location);
			String msg = load(location);
			inputLine.setMsg(msg);
		} else if  (e.getPropertyName().equals("createNew")) {
			System.out.println("createNew");
			createNewSpreadsheet();
		}
	}
	
	/**
	 * Saves the table to a file.
	 * 
	 * @param location the location where the file will be saved.
	 * @return a message if the save was successful.
	 */
	private static String save(String location) {
		
		Table t = ((SpreadSheet) spreadsheet).getTable();
		saved = new SaveFile( t );
		String msg;
		
		msg = saved.save(location);
		System.out.println(msg);
		return msg;
	}
	
	/**
	 * Loads the table from a file.
	 * 
	 * @param location the location of the file that will be loaded
	 * @return a message confirming if loading succeeded or failed
	 */
	private String load(String location) {
		Table t = ((SpreadSheet) spreadsheet).getTable();
		saved = new SaveFile( t );
		String msg;
	
		msg = saved.load(location);
		System.out.println(msg);
			
		return msg;			
	}
	
	/**
	 * This method clears the data from the existing spreadsheet.
	 */
	private void createNewSpreadsheet() {
		((SpreadSheet) spreadsheet).createNew();
		spreadsheet.validate();
		spreadsheet.repaint();
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
