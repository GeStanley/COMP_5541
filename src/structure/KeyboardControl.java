package structure;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import structure.Cell.Format;
import ui.SpreadSheet;

public class KeyboardControl {
	
	private CellFormatControl cellFormat;
	private ClipboardControl clip;
	private InputMap myKeyInput;
	private ActionMap myActionMap;

	@SuppressWarnings("serial")
	public KeyboardControl(SpreadSheet spreadsheet){
		this.clip = new ClipboardControl(spreadsheet.getTable());
		this.cellFormat = new CellFormatControl(spreadsheet.getTable());
		myKeyInput = spreadsheet.getInputMap(JPanel.WHEN_FOCUSED);
		myActionMap = spreadsheet.getActionMap();
		
		//Copy
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "copy");
	    myActionMap.put("copy",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	            clip.copy();
	            System.out.println("Copy");
	        }
	    });	  		
		
		//Cut
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK), "cut");
	    myActionMap.put("cut",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	            clip.cut();
	            System.out.println("Cut");
	        }
	    });	    
	    
		//Paste
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK), "paste");
	    myActionMap.put("paste",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	            clip.paste();
	            System.out.println("Paste");
	        }
	    });	  	    
	    
		//Default Format
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK), "defaultFormat");
	    myActionMap.put("defaultFormat",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	    		cellFormat.setCellFormat(Format.defaultFormat);
	            System.out.println("default Format");
	        }
	    });	  		    
		//Monetary Format
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK), "moneyFormat");
	    myActionMap.put("moneyFormat",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	    		cellFormat.setCellFormat(Format.moneyFormat);
	            System.out.println("money ");
	        }
	    });	  		    	    
		//Scientific Format
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK), "scienceFormat");
	    myActionMap.put("scienceFormat",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	    		cellFormat.setCellFormat(Format.scienceFormat);
	            System.out.println("science Format");
	        }
	    });	  	
		//Integer Format
	    myKeyInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK), "intFormat");
	    myActionMap.put("intFormat",new AbstractAction() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	    		cellFormat.setCellFormat(Format.intFormat);
	            System.out.println("integer Format");
	        }
	    });	  			  	    
	    
	}
	
}
