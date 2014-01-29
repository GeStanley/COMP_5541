package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputLineComponent extends JComponent implements ActionListener {
	
	private JTextField input;
	private JLabel msg;
	
	public InputLineComponent(){
		
	}
	
	public String setFormula(){
		return "";
	}
	
	public String setMsg(){
		return "";
	}
	
	public void actionPerformed(ActionEvent e) {}
}
