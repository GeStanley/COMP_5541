package structure;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import structure.Table.NullCellPointer;

public class UnitTestCell {
	
	Table table;
	Cell cell;
	
	/**
	 * Intialize values for every test
	 */
	@Before
	public void before() {
		table = new Table(2,2);
		cell = new Cell(table);
	}
	
	/**
	 * Clean up after a test
	 */
	@After
	public void after() {
		table = null;
		cell = null;
	}
	
	/**
	 * Test cell constructors
	 */
	@Test
	public void testCellConstructor() {		
		try {
			Cell cell1 = cell;
			
			assertEquals("Should be 0", "0.0", cell1.getValueString());
			assertEquals(0.0, cell1.getValue(),0.0001);
			
			Cell cell2 = new Cell(table, 7.8);
			
			assertEquals("Should be 7.8", "7.8", cell2.getValueString());
			assertEquals(7.8, cell2.getValue(),0.0001);
			
			Cell cell4 = new Cell(table, "2+5");
			
			assertEquals("Should be 7", "7.0", cell4.getValueString());
			assertEquals(7.0, cell4.getValue(),0.0001);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test cell formula
	 */
	@Test
	public void testSetCellFormula() {
		try {
			cell.setFormula("23+27");
			
			assertEquals("Should be 50", "50.0", cell.getValueString());
			assertEquals(50.0, cell.getValue(),0.0001);
			
			cell.setFormula(null);
			assertEquals(0.0, cell.getValue(),0);
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}	
	
	/**
	 * Test getFormula method of Cell
	 */
	@Test
	public void testGetCellFormula(){
		try {
			cell.setFormula("5+12");
			assertEquals("5+12", cell.getFormula());
			
			cell.setFormula(null);
			assertEquals("0.0", cell.getFormula());
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}

	}
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferencing() {		
		try {
			Cell selected = table.selectCell("A1");
			selected.setFormula("5");
			
			Cell selected2 = table.selectCell("A2");
			selected2.setFormula("10");
			
			Cell referencing = table.selectCell("B1");
			referencing.setFormula("A1+A2");
			
			assertEquals("Should be 15", "15.0", referencing.getValueString());
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}

	}
	
	/**
	 * Test getValue method of Cell
	 */
	@Test
	public void testGetValue() {
		try {
			cell.setFormula("23+27");
			assertEquals(50.0, cell.getValue(),0.0001);
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Test equals method of Cell
	 */
	@Test
	public void testEquals() {
		assertFalse("Compare a cell to a table", cell.equals(table));
		assertFalse("Compare a cell to null", cell.equals(table));
		assertTrue("Compare a cellt o itself", cell.equals(cell));

		// Now compare actual cells
		Cell one = new Cell(table, "2+3");
		Cell two = new Cell(table, "2+3");
		Cell three = new Cell(table, "3+2");
		assertTrue("Compare cells with the same formula", one.equals(two));
		assertFalse("Compare cells with different formulas", two.equals(three));
	}
	
	/**
	 * Test getValueString method of Cell
	 */
	@Test
	public void testGetValueString() {
		table = new Table(2,2);
		Cell cell = new Cell(table);
		try {
			cell.setFormula("23+27");
			assertEquals("50.0", cell.getValueString());
		}
		catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
//	@Test
//	public void testGetReferenceValues() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NullCellPointer {
//		table = new Table(5,5);
//		Cell cell = new Cell(table);
//		
//		String formula = "5+12";
//		String selectedCell = "A1";
//		table.setSelectedCell(table.selectCell(selectedCell));
//		table.insertToCell(formula);
//		
//		formula = "A1+12";
//		cell.setFormula(formula);
//		cell.setFormulaWithCellReference(formula);
//	    Method getReferenceValuesMethod = getMethodOfClass(Cell.class, "getReferenceValues");
//	    getReferenceValuesMethod.invoke(cell);
//		assertEquals("formulaWithoutCellReference would be 17.0+12","17.0+12", cell.getFormulaWithoutCellReference());
//	}
	
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
	
}
