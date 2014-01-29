package ui;

package ui;

import javax.swing.JFrame;

public class Gui extends JFrame{
	
	public Gui(){
		setTitle("Calcul-O-Matic");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		InputLineComponent inputLine = new InputLineComponent();
		SpreadsheetGrid spreadsheetGrid = new SpreadsheetGrid();
		
		add(inputLine);
		//add(spreadsheetGrid);
	}
	
	public static void main(String args[]){
		JFrame frame = new Gui();
		frame.setVisible(true);
	}

}
