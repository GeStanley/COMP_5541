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
	Table testTable;
	
	public void testInputLine() throws AWTException {
		
		testTable = new Table(5,5);
		testComp = new InputLineComponent(testTable);
		
		testComp.input.setText("=2+5");
		
		Robot enter = new Robot();
		enter.keyPress(KeyEvent.VK_ENTER);
		enter.keyRelease(KeyEvent.VK_ENTER);
		
		assertEquals("2+5",testTable.getSelectedCell().getFormula());
	}

}
