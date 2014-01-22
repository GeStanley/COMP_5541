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

}
