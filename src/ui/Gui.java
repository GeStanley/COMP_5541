package ui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
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

	
	/**
	 * This sets up the Gui aspect by taking the input line and the spreadsheet
	 * and displaying them.
	 * 
	 */
	public Gui() {
		// Frame setup
		super("Calcul-O-Matic");
		setSize(600, 247);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Grid setup
		inputLine = new InputLineComponent();
		spreadsheet = new SpreadSheet();
		JScrollPane scrollPane = new JScrollPane(spreadsheet);
		spreadsheet.setFillsViewportHeight(true);
		
		JTable rowTable = new RowTable(spreadsheet);
		scrollPane.setRowHeaderView(rowTable);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		
		

		inputLine.addPropertyChangeListener(this);
		spreadsheet.addPropertyChangeListener(this);

		add(inputLine, BorderLayout.NORTH);
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
			System.out.println("input");

		} else if (e.getPropertyName().equals("select")) {
			System.out.println("select");
			String s = (String) e.getNewValue();

			inputLine.setText(s);
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
