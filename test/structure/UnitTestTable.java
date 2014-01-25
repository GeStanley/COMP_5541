package structure;

import org.junit.*;
import static org.junit.Assert.*;
import structure.Table;
import structure.Cell;
import java.util.Random;

public class UnitTestTable {
	
	private Table tester;
	private static Random rand = new Random();

	/**
	 * Test table dimensions
	 * 
	 * This tests:
	 *   getlength()
	 *   getWidth()
	 *   appendRow()
	 *   appendColumn()
	 */
	@Test
	public void testTableExpansion() {
		int numRows = rand.nextInt(9)+1;
		int numCols = rand.nextInt(9)+1;
		int expand;
		
		// First check normal cell dimensions using random numbers
		tester = new Table(numRows,numCols);
		assertEquals("Should be " + numRows + " rows", numRows, tester.getLength());
		assertEquals("Should be " + numCols + " columns", numCols, tester.getWidth());
		
		// Now expand the existing table with random numbers
		expand = rand.nextInt(9)+1;
		numRows += expand;
		assertEquals("Added " + expand + " rows", numRows, tester.appendRow(expand));
		expand = rand.nextInt(9)+1;
		numCols += expand;
		assertEquals("Added " + expand + " columns", numCols, tester.appendColumn(expand));
		
		// Now try the 0 dimension edge case
		tester = new Table(0,0);
		assertEquals("Should be 0 rows", 0, tester.getLength());
		assertEquals("Should be 0 columns", 0, tester.getWidth());
				
		// Reset the tester object
		tester = null;
	}
	
	/**
	 * Test cell selection and changing values
	 */
	@Test
	public void testCellSelection() {
		tester = new Table(5,5);
		Cell selected = tester.selectCell("A2");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		selected = tester.selectCell("AA2");
		assertEquals("Should be null", "null", selected+"");
	}
	
	/**
	 * Test get cell
	 */
	@Test
	public void testGetCell() {
		tester = new Table(5,5);
		Cell selected = tester.getCell(1, 'A');
		selected.setFormula("5");
		
		assertEquals("Should be 5", "5.0", selected.getValueString());

	}
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferencing() {
		tester = new Table(2,2);
		Cell selected = tester.selectCell("A1");
		selected.setFormula("5");
		
		Cell selected2 = tester.selectCell("A2");
		selected2.setFormula("10");
		
		Cell referencing = tester.selectCell("B1");
		referencing.setFormula("A1+A2");
		
		assertEquals("Should be 15", "15.0", referencing.getValueString());

	}

}
