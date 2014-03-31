package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * This tests the InputLine component of the program.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * @author	Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

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
		System.out.println(testInput.getMsg());
		assertEquals("Message: abc", testInput.getMsg());
		
		testInput.setMsg(null);
		assertEquals("Message: ", testInput.getMsg());
		
		testInput.setMsg("");
		assertEquals("Message: ", testInput.getMsg());
	}
	
	@Test
	public void testSetGetText(){
		testInput = new InputLineComponent(null);
		
		testInput.setText("5+3");
		assertEquals("5+3", testInput.input.getText());
		
		//This test won't work this way, because you need the GUI controller to be initialized. 
		//This is tested in UnitTestFormula and UnitTestCell anyways.
		//We can safely ommit this test.
		//testInput.setText("abc");
		//assertEquals("0.0", testInput.input.getText());
		
		testInput.setText(null);
		System.out.println(testInput.getMsg());
		assertEquals("", testInput.input.getText());
		
		testInput.setText("");
		assertEquals("", testInput.input.getText());
	}
	
	
// 	This test won't work because the Gui need to be initialized so that an insert
//	is made possible.
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
