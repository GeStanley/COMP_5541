package ui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

import structure.Table;

public class UnitTestInputLine {
	
	InputLineComponent testInput;
	SpreadSheet testSheet;
	GridModel testGrid;
	
	@Test
	public void testInputConstructor(){
		testInput = new InputLineComponent(null);
		assertNotNull(testInput);
		assertEquals("Message:",testInput.getMsg());
	}
	
	@Test
	public void testSetGetMsg(){
		testInput = new InputLineComponent(null);
		
		testInput.setMsg("abc");
		assertEquals("abc", testInput.getMsg());
		
		testInput.setMsg(null);
		assertEquals("Message:", testInput.getMsg());
		
		testInput.setMsg("");
		assertEquals("Message:", testInput.getMsg());
	}
	
	@Test
	public void testSetGetText(){
		testInput = new InputLineComponent(null);
		
		testInput.setText("5+3");
		assertEquals("5+3", testInput.input.getText());
		
		testInput.setText("abc");
		assertEquals("0.0", testInput.input.getText());
		
		testInput.setText(null);
		assertEquals("0.0", testInput.input.getText());
		
		testInput.setText("");
		assertEquals("0.0", testInput.input.getText());
	}
	
//	@Test
//	public void testCellDisplay() throws NumberFormatException, Exception {
//		
//		testSheet = new SpreadSheet();
//		testInput = new InputLineComponent(null);
//				
//		testSheet.setRowSelectionInterval(1, 1);
//		testSheet.setColumnSelectionInterval(2, 2);
//		
//		testSheet.setFormulaOfSelectedCell("5+7");
////		Robot enter = new Robot();
////		enter.keyPress(KeyEvent.VK_ENTER);
////		enter.keyRelease(KeyEvent.VK_ENTER);
//		
//		
//		assertEquals("5+7",testInput.input.getText());
//	}
	


}
