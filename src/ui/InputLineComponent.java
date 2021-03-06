package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import structure.Table;

/**
 * The Component where a user enters input to cell, and can see messages that are
 * displayed to him.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * 			Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */
public class InputLineComponent extends JPanel implements ActionListener{
	
	JTextField input;
	JLabel msg;
	

	/**
	 * Constructor for the class.
	 * 
	 * @param table the table containing all the data.
	 */
	public InputLineComponent(Table table){

		
		setLayout(new GridLayout(2, 1));
		
		input = new JTextField();
		input.addActionListener(this);
		input.setPreferredSize(new Dimension( 200, 24 ));
		
		msg = new JLabel("Message:");
		msg.setName("message");
		
		add(msg);
		add(input);
		
	}
	/**
	 * Gets the message that is displayed.
	 * 
	 * @return the message displayed.
	 */
	public String getMsg() {
		return msg.getText();
	}
	
	/**
	 * Sets the mesage for a user to see.
	 * 
	 * @param newMsg
	 */
	public void setMsg(String newMsg){
		if ( newMsg == null || newMsg.equals(""))
			newMsg = "";
		msg.setText("Message: " + newMsg);
	}
	
	/**
	 * Sets the text of the input field to the contents of a cell.
	 * 
	 * @param s a string of the formula in a cell.
	 */
	public void setText(String s){
		if ( s == null || s.equals(""))
			s = "";
		input.setText(s);
	}
	
	/**
	 * This method fires a property change to the listener.
	 * It contains the data that is in the input field.
	 */
	public void actionPerformed(ActionEvent e) {
		//System.out.println( input.getText() );
		this.firePropertyChange("input", false, input.getText());
		
	}
}
