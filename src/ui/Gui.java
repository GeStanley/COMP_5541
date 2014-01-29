package ui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gui extends JFrame implements PropertyChangeListener{
	
	public Gui(){
		setTitle("Calcul-O-Matic");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		InputLineComponent inputLine = new InputLineComponent(null);
		JTable spreadsheet = new SpreadSheet();
		JScrollPane scrollPane = new JScrollPane(spreadsheet);
		spreadsheet.setFillsViewportHeight(true);
		
		inputLine.addPropertyChangeListener(this);
		spreadsheet.addPropertyChangeListener(this);
		
		
		
		add(inputLine, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	public static void main(String args[]){
		JFrame frame = new Gui();
		frame.setVisible(true);
	}

	
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		
		
		if (e.getPropertyName().equals("input")){
			System.out.println("input");
		} else if (e.getPropertyName().equals("select")){
			System.out.println("select");
		}
		
		
	}

}
