package structure;

import org.junit.*;

import static org.junit.Assert.*;
import structure.Table;
import structure.Cell;
import structure.Table.NullCellPointer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import javax.script.ScriptException;

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
		
		assertFalse(tester.isCellSelected());
		
		Cell selected = tester.selectCell("A2");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		selected = tester.selectCell("AA2");
		assertEquals("Should be null", "null", selected+"");
		assertTrue(tester.isCellSelected());
		
	}
	
	/**
	 * Test get cell
	 */
	@Test
	public void testGetCell() {
		tester = new Table(5,5);
		
		int counter = 1;
		
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++){
				
				Cell selected = tester.getCell(i,j);
				selected.setFormula(Integer.toString(counter));
				
				counter++;
			}
		
		counter=1;
		
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++){
				assertEquals("Should be " + counter, Integer.toString(counter)+ ".0", tester.getCell(i,j).getValueString());
				counter++;
				}

	}
	
	/**
	 * Test get cell
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
		tester = new Table(5,5);
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
		tester = new Table(5,5);
		assertEquals(((int)'A' - (int)'A'), tester.getColumnIndex('A'));
		assertEquals(((int)'Z' - (int)'A'), tester.getColumnIndex('Z'));
		tester = null;
    }
	
	 /**
     * Test to get the required row
     */
	@Test
    public void testGetRow() {
		tester = new Table(5,5);
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
		tester = null;
    }
	
	
	@Test
	public void testIsCellSelected(){
		tester = new Table(5,5);
		assertTrue(!tester.isCellSelected());
		Cell selectedCell = tester.selectCell("A2");
		assertEquals("0.0", selectedCell.getValueString());
		selectedCell = tester.selectCell("Z9");
		assertEquals(null, selectedCell);
		tester = null;
	}
	
	@Test
	public void TestInsertFormula() throws NumberFormatException, NullCellPointer, ScriptException{
		//To Do test with formula as referneces to be uncommented
		tester = new Table(5, 5);
		String formula = "5+12";
		String selectedCell = "B1";
		tester.setSelectedCell(tester.selectCell(selectedCell));
		tester.insertToCell(formula);
		assertEquals("17.0",tester.getCell(0, 1).getValueString());
	
		selectedCell = "A2";
		formula = "B1";
		tester.setSelectedCell(tester.selectCell(selectedCell));
		tester.insertToCell(formula);
		//assertEquals(tester.getValue(formula),tester.getCell(1, 0).getValueString());
		
		selectedCell = "A3";
		formula = "B1+A2";
		tester.setSelectedCell(tester.selectCell(selectedCell));
		tester.insertToCell(formula);
		//assertEquals(tester.getValue(formula),tester.getCell(2, 0).getValueString());
		
		
		// to do test for references as formula
		
	}
	
	@Test
	public void testGetLength(){
		tester = new Table(6,5);
		assertEquals(6, tester.getLength());
		tester = null;
	}
	
	@Test
	public void testGetWidth(){
		tester = new Table(5,6);
		assertEquals(6, tester.getWidth());
		tester=null;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetValue() throws ScriptException{
		tester = new Table(5,5);
		String formula = "5+12";
		assertEquals("17.0", String.valueOf(tester.getValue(formula)));
		
//		formula = null;
//		assertEquals(null, tester.getValue(formula));
//		
//		formula = "";
//		assertEquals(null, tester.getValue(formula));
		
		//TO DO Test to validate references
	}
}
