package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import structure.Table;

public class InputLineComponent extends JComponent implements ActionListener {
	
	JTextField input;
	JLabel msg;
	
	public InputLineComponent(Table table){
		
	}
	
	public String setFormula(){
		return "";
	}
	
	public String setMsg(){
		return "";
	}
	
	public void actionPerformed(ActionEvent e) {}
}
