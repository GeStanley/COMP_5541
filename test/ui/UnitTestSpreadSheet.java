package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class UnitTestSpreadSheet {
	
	SpreadSheet testSheet;
	
	@Test
	public void testConstructor() {
		testSheet = new SpreadSheet();
		
		assertNotNull(testSheet);
		
	}
	
	@Test
	public void testDimensionConstructor() {
		fail("shouldn't there be a constructor with dimensions?");
	}
	
	@Test
	public void testActionPerformed() {
		
		
		testSheet = new SpreadSheet();
		
		testSheet.setRowSelectionInterval(1, 1);
		testSheet.setColumnSelectionInterval(2, 2);
		
		//ActionEvent ae = new ActionEvent();
		
		//testSheet.actionPerformed(ae);
		
		fail("needs to be implemented");
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
