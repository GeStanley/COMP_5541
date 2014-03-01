package ui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import structure.Table;

public class UnitTestInputLine {
	
	InputLineComponent testInput;
	SpreadSheet testSheet;
	GridModel testGrid;
	Table table;
	InputLineComponent inputLineComponent;
	JTextField input;
	
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
	
	
	
	
	
	/**
	 * Intialize values for every test
	 */
	@Before
	public void before()  {
		table = new Table(2,2);
		inputLineComponent =new InputLineComponent(table);
		input= new JTextField();
		
	}
	
	/**
	 * Clean up after a test
	 */
	@After
	public void after() {
		table = null;
		inputLineComponent = null;
		input=null;
		
	}
	
	/**
	 * Test inputLineComponent constructors
	 */
	@Test
	public void testInputLineComponentConstructor() {		
		try {
			InputLineComponent inputLineComponent1 = inputLineComponent;
			
			assertEquals(24, inputLineComponent1.getPreferredSize().height, 0.0001);
			
			assertEquals(400, inputLineComponent1.getPreferredSize().width, 0.0001);  
			// why not 200  ??????????
		
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSetMsg_GetMsg() {
		try{
			InputLineComponent inputLineComponent1 = inputLineComponent;
			
			inputLineComponent1.setMsg("msgtest");
			assertEquals("Should be msgtest","msgtest",inputLineComponent1.getMsg());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSetText() {
		try{
			
			input.setText("texttest");
			assertEquals("Should be texttest","texttest",input.getText());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}



}
