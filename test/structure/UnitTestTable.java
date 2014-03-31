package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Table component of our program 
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * @author	Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

public class UnitTestTable {
	
	private Table tester;
	private Random rand;

	/**
	 * Initialize values before testing
	 */
	@Before
	public void before() {
		tester = new Table(5,5);
		rand = new Random();
	}
	
	/**
	 * Clean up
	 */
	@After
	public void after() {
		tester = null;
		rand = null;
	}
	
	/**
	 * Test equals method of Table
	 */
	@Test
	public void testEquals() {
		Cell selected;
		assertTrue("Compare a table to itself", tester.equals(tester));

		// Now set up three new with some values tables
		Table one = new Table(5,5);
		Table two = new Table(5,5);
		Table three = new Table(5,5);
		Table four = new Table(5,6);
		Table empty = new Table(0,0);
		Table blank = new Table(0,0);

		// Test table dimension equalities
		assertFalse("Compare tables with different dimensions", three.equals(four));
		assertTrue("Compare tables with zero dimensions", empty.equals(blank));

		
		// Populate first three tables with some values
		try {
			one.selectCell("A2");
			two.selectCell("A2");
			three.selectCell("A1");
			one.insertToCell("3.5*2");
			two.insertToCell("3.5*2");
			three.insertToCell("3.5*2");
			one.selectCell("C1");
			two.selectCell("C1");
			three.selectCell("C1");
			one.insertToCell("4-9*2");
			two.insertToCell("4-9*2");
			three.insertToCell("4-8*2");

		}
		catch (Exception e) {
			fail(e.getClass().toString() + ": " + e.getMessage());
		}
		
		assertTrue("Compare tables with matching cells", one.equals(two));
		assertFalse("Compare tables with differing cells", three.equals(two));
		
	}
	
	/**
	 * Test cell selection
	 */
	@Test
	public void testCellSelection() {		
		assertFalse(tester.isCellSelected());
		
		Cell selected = tester.selectCell("A2");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		selected = tester.selectCell("AA2");
		assertEquals("Should be null", "null", selected+"");
		assertTrue(tester.isCellSelected());
	
	}
	
	/**
	 * Test get cell
	 * 
	 * Iterate through every cell in the table and assign it a value,
	 * then check for that value.
	 */
	@Test
	public void testGetCell() {		
		double expected;
		Cell selected;
		// Populate the table with empty cells
		tester.populate();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				
				selected = tester.getCell(i,j);
				try {
					selected.setFormula(i  + "+" + j);
				}
				catch (Exception e) {
					fail("Exception: " + e.getMessage());
				}			
			}
		}
				
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				expected = (double) (i+j);
				assertEquals(expected, tester.getCell(i,j).getValue(), 0.0001);
			}
		}

	}
	
	/**
	 * Test random dimensions
	 */
	@Test
	public void testDimensions() {
		int numRows = rand.nextInt(9)+1;
		int numCols = rand.nextInt(9)+1;
		
		tester = new Table(numRows,numCols);
		
		assertEquals("Should be " + numRows, numRows, tester.getLength());
		assertEquals("Should be " + numCols, numCols, tester.getWidth());

	}
	
	/**
	 * Test update Row
	 */
	@Test
	public void testUpdateRows() {
		int pos = 4;
		
		String in = "1\",\"2\",\"3\",\"4";
		String[] parts = in.split("\",\"");
		Cell[] row = new Cell[parts.length];
		
		for (int count=0; count<parts.length; count++) {
			row[count] = new Cell(tester, parts[count]);
		}

		tester.updateRow(pos, row);
		assertEquals(Integer.parseInt(parts[0]), (int)tester.getCell(pos, 0).getValue());
	}
	

	/**
	 * Test Adding rows to the table
	 */
	@Test
	public void testExpandRow(){
		int numRow = 2;
		int numCol = 2;
		int expandRow = 1;
		tester = new Table(numRow, numCol);
		assertEquals("Added " + 1 + " rows to current number of rows: "+numRow, numRow+expandRow, tester.appendRow(expandRow));
	}
	
	/**
	 * Test Adding columns to the table
	 */
	@Test
	public void testExpandCol(){
		int numRow = 2;
		int numCol = 2;
		int expandCol = 2;
		tester = new Table(numRow, numCol);
		assertEquals("Added " + 2 + " columns to current number of columns: "+numCol, numCol+expandCol, tester.appendColumn(expandCol));
	}
	
	/**
	 * Test private method to expand table
	 */
	@Test
	public void testExpandTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		tester = new Table(2,2);
	    Method expandMethod = getMethodOfClass(Table.class, "expandTable");
	    expandMethod.invoke(tester,new Cell[5][5]);
		assertEquals("new length should be 5", 5, tester.getLength());
	}

	/**
	 * Helper to use reflection to test private methods
	 */
	private Method getMethodOfClass(Class argClass, String argMethodName) {
	    Method[] methods = argClass.getDeclaredMethods();
	    for (Method method : methods) {
	        if (method.getName().equals(argMethodName)) {
	            method.setAccessible(true);
	            return method;
	        }
	    }
	    throw new NoSuchMethodError("couldn't find " + argMethodName + " on class " + argClass);
	}
	
	/**
	 * Test Getting column index relative to first column of table, i.e 'A'
	 */
	@Test
	public void testGetColumnIndex(){
		assertEquals(((int)'A' - (int)'A'), tester.getColumnIndex('A'));
		assertEquals(((int)'Z' - (int)'A'), tester.getColumnIndex('Z'));
    }
	
	/**
     * Test to get the required row
     */
	@Test
    public void testGetRow() {
		int pos = 4;
		
		// updating a row in the table
		String in = "1\",\"2\",\"3\",\"4";
		String[] parts = in.split("\",\"");
		Cell[] row = new Cell[parts.length];
		
		for (int count=0; count<parts.length; count++) {
			row[count] = new Cell(tester, parts[count]);
		}
		tester.updateRow(pos, row);
		
		//getting the updated row from the table
		Cell[] temp= tester.getRow(pos);
		
		//comparing the row of the updated table with the row returned by the method
		assertEquals(tester.getCell(pos, 0), temp[0]);
    }
	
	/**
	 * Test for is a cell selected
	 */
	@Test
	public void testIsCellSelected(){
		assertTrue(!tester.isCellSelected());
		Cell selectedCell = tester.selectCell("A2");
		assertEquals("0.0", selectedCell.getValueString());
		selectedCell = tester.selectCell("Z9");
		assertEquals(null, selectedCell);
	}
	
	/**
	 * Test getLength method
	 */
	@Test
	public void testGetLength(){
		tester = new Table(6,5);
		assertEquals(6, tester.getLength());
	}
	
	/**
	 * Test getWidth method
	 */
	@Test
	public void testGetWidth(){
		tester = new Table(5,6);
		assertEquals(6, tester.getWidth());
	}
	
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferencing() {		
		try {
			Cell selected = tester.selectCell("A1");
			selected.setFormula("5");
			
			Cell selected2 = tester.selectCell("A2");
			selected2.setFormula("10");
			
			Cell referencing = tester.selectCell("B1");
			referencing.setFormula("A1+A2");
			
			assertEquals("Should be 15", "15.0", referencing.getValueString());
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}

	}
	
}
