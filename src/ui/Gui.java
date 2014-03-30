package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import structure.Cell;
import structure.KeyboardControl;
import structure.Table;
import structure.Cell.Format;


/**
 * This class handles the creation of the gui for the calcul-o-matic program
 * 
 * @author Mike
 */
@SuppressWarnings("serial")
public class Gui extends JFrame implements PropertyChangeListener{

	//TODO removed private to make testing easier.
	//if there is another way of doing it please let me know.
	static SaveFile saved;
	InputLineComponent inputLine;
	static JTable spreadsheet;
	ButtonComponent buttonComponent;
	JScrollPane scrollPane;
	int gRow=0,gColumn=0;
	
	/**
	 * This sets up the GUI aspect by taking the input line and the spreadsheet
	 * and displaying them.
	 * 
	 */
	public Gui() {
		// Frame setup
		super("Calcul-O-Matic");
		setSize(600, 320);
		setMinimumSize(new Dimension(700,400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());


		// GUI setup
		buttonComponent = new ButtonComponent();
		inputLine = new InputLineComponent(null);	

		spreadsheet = new SpreadSheet();
		spreadsheet.setName("data");
		JScrollPane scrollPane = new JScrollPane(spreadsheet);
		spreadsheet.setFillsViewportHeight(true);
		
		JTable rowTable = new RowTable(spreadsheet);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());			
		
		inputLine.addPropertyChangeListener(this);
		spreadsheet.addPropertyChangeListener(this);
		buttonComponent.addPropertyChangeListener(this);		
		
		//Keyboard input setup
		@SuppressWarnings("unused")
		KeyboardControl keyInputController = new KeyboardControl((SpreadSheet) spreadsheet);
		
		JPanel menuAndInput = new JPanel(new GridLayout(2,1));
		
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
		//InputLineComponent action.
		
		boolean isSpecialCharPresent = false;
		boolean hasNumberFormat = false;
		char numberFormatChar = 'z';
		if (e.getPropertyName().equals("input")) {
			String in = (String) e.getNewValue();
			
			if ( ((SpreadSheet) spreadsheet).isSelected() ) { 
				System.out.println("input " + in);
				// To Handle '@' 
				if((in).charAt(0)=='@'){
					in = in.substring(1);
					isSpecialCharPresent = true;
				}
				// To Handle ':'
				else if((in).charAt(0)==':')
				{
					in = in.substring(1);
					numberFormatChar = (in).charAt(0);
					in = in.substring(1);
					hasNumberFormat = true;
				}
				String msg = ((SpreadSheet) spreadsheet).setFormulaOfSelectedCell( in );
				((SpreadSheet) spreadsheet).getTable().selectGivenCell(gRow,gColumn);
				
				Cell cell = ((SpreadSheet) spreadsheet).getTable().getSelectedCell();
				
				if(isSpecialCharPresent){
						in = "@" + in;
						cell.setFormulaForSpecialChar(in);
				}
				
				if(hasNumberFormat){
					in = ":" + numberFormatChar + in;
					cell.setFormulaForSpecialChar(in);
					switch (numberFormatChar){
					case 'I':
						cell.setCellFormat(Format.intFormat);
						break;
					case 'M':
						cell.setCellFormat(Format.moneyFormat);
						break;
					case 'S':
						cell.setCellFormat(Format.scienceFormat);
						break;
					case 'z':
						break;					
					}
				}
				
				inputLine.setText(in);
				inputLine.setMsg(msg);
				
				spreadsheet.validate();
				spreadsheet.repaint();
				
			} else {
				inputLine.setMsg("You have to select a cell first.");
			}
			
		
		//SpreadSheet component action.
		} else if (e.getPropertyName().equals("select")) {
			
	        int row = spreadsheet.getSelectedRow();	       
	        int col = spreadsheet.getSelectedColumn();
			((SpreadSheet) spreadsheet).getTable().selectGivenCell(row, col);
			gRow=row;
			gColumn=col;
			System.out.println("select Cell at " + row + "," + col);
			Cell cell = ((SpreadSheet) spreadsheet).getTable().getSelectedCell();
			String s = (String) e.getNewValue();			
			inputLine.setText(cell.getFormula());								
		//Button component action.
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
			inputLine.setMsg("Created new spreadsheet.");
			createNewSpreadsheet();
		}
		else if (e.getPropertyName().equals("help")){
			String helpMessage 	= 	
					"The shortcut keys are the following:" + System.lineSeparator() +
					"Ctrl+1 for default number format" + System.lineSeparator() +
					"Ctrl+2 for monetary number format" + System.lineSeparator() +
					"Ctrl+3 for scientific number format" + System.lineSeparator() +
					"Ctrl+4 for integer number format" + System.lineSeparator() +
					"Ctrl+x to cut a cell" + System.lineSeparator() +
					"Ctrl+c to copy a cell" + System.lineSeparator() +
					"Ctrl+v to paste a cell" + System.lineSeparator();
			JOptionPane.showMessageDialog(this,	helpMessage,
				    "Shortcut keys", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			spreadsheet.validate();
			spreadsheet.repaint();
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

