package ui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

import structure.Table;

public class UnitTestInputLine {
	
	InputLineComponent testComp;
	GridModel testGrid;
	
	@Test
	public void testCellDisplay() throws NumberFormatException, Exception {
		
		testGrid = new GridModel();
		testComp = new InputLineComponent();
				
		
//		Robot enter = new Robot();
//		enter.keyPress(KeyEvent.VK_ENTER);
//		enter.keyRelease(KeyEvent.VK_ENTER);
		
		
		assertEquals("5+7",testComp.input.getText());
	}
	
	@Test
	public void testInputLine() throws AWTException {

	}

}
