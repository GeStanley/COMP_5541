package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import structure.Table;

public class InputLineComponent extends JPanel implements ActionListener{
	
	JTextField input;
	JLabel msg;
	
	public InputLineComponent(){
		
		setLayout(new GridLayout());
		
		input = new JTextField();
		input.addActionListener(this);
		input.setPreferredSize(new Dimension( 200, 24 ));
		
		msg = new JLabel("Message:");
		
		add(input);
		add(msg);
		
	}
	
	public String setFormula(){
		return "";
	}
	
	public String setMsg(){
		return "";
	}
	
	public void setText(String s){
		input.setText(s);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println( input.getText() );
		this.firePropertyChange("input", false, true);
		
	}
}
