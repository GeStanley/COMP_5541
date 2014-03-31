package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This tests the Spreadsheet component of the program.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * @author	Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

public class UnitTestSpreadSheet {
	
	SpreadSheet testSheet;
	
	@Test
	public void testConstructor() {
		testSheet = new SpreadSheet();
		
		assertNotNull(testSheet);
		
	}
	
	@Test
	public void testDimensionConstructor() { 
		testSheet = new SpreadSheet(5, 5);
		
		assertNotNull(testSheet);
	}
	
	@Test
	public void testActionPerformed() {
		
		
		testSheet = new SpreadSheet();
		
		testSheet.setRowSelectionInterval(1, 1);
		testSheet.setColumnSelectionInterval(2, 2);
		
		
		//ActionEvent ae = new ActionEvent();
		//testSheet.actionPerformed(ae);
		//fail("needs to be implemented"  
			  //+ "Not sure how to test this. Maybe we should just skip it. -mike");
		
		
	}
	
	@Test
	public void testIsSelected() throws NumberFormatException, Exception {
		testSheet = new SpreadSheet();

		testSheet.selectAll();
		assertTrue(testSheet.isSelected());
		
		testSheet.setEditingColumn(2);
		testSheet.setEditingRow(2);
		
		testSheet.getColumnModel().getSelectionModel().clearSelection();
		testSheet.getSelectionModel().clearSelection();
		assertFalse(testSheet.isSelected());
		
		testSheet.setRowSelectionInterval(1, 1);
		testSheet.setColumnSelectionInterval(2, 2);
		assertTrue(testSheet.isSelected());

	}
	
	@Test
	public void testSetFormulaOfSelectedCell(){
		testSheet = new SpreadSheet();
		
		testSheet.setRowSelectionInterval(1, 1);
		testSheet.setColumnSelectionInterval(2, 2);
		
		testSheet.setFormulaOfSelectedCell("5+3");
		
		assertEquals("8.0", testSheet.gm.getValueAt(1,2).toString());
	}
}
