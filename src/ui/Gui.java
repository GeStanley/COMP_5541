package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gui extends JFrame{
	
	public Gui(){
		setTitle("Calcul-O-Matic");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		InputLineComponent inputLine = new InputLineComponent(null);
		JTable spreadsheetGrid = new JTable( new SpreadsheetGrid() );
		JScrollPane scrollPane = new JScrollPane(spreadsheetGrid);
		spreadsheetGrid.setFillsViewportHeight(true);
		
		add(inputLine, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public static void main(String args[]){
		JFrame frame = new Gui();
		frame.setVisible(true);
	}

}
