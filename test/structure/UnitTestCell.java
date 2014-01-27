package structure;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import structure.Table.NullCellPointer;

public class UnitTestCell {
	
	Table table;
	
	/**
	 * Test cell constructors
	 */
	@Test
	public void testCellConstructor() {
		table = new Table();
		
		
		
		Cell cell1 = new Cell(table);
		
		assertEquals("Should be 0", "0.0", cell1.getValueString());
		assertEquals(0.0, cell1.getValue(),0.0001);
		
		Cell cell2 = new Cell(table, 7.8);
		
		assertEquals("Should be 7.8", "7.8", cell2.getValueString());
		assertEquals(7.8, cell2.getValue(),0.0001);
		
		Cell cell4 = new Cell(table, "2+5");
		
		assertEquals("Should be 7", "7.0", cell4.getValueString());
		assertEquals(7.0, cell4.getValue(),0.0001);
	}
	
	/**
	 * Test cell formula
	 */
	@Test
	public void testSetCellFormula() {
		table = new Table(2,2);
		Cell cell = new Cell(table);
		
		cell.setFormula("23+27");
		
		assertEquals("Should be 50", "50.0", cell.getValueString());
		assertEquals(50.0, cell.getValue(),0.0001);
		
		
		cell.setFormula(null);
		assertEquals(0.0, cell.getValue(),0);
	}	
	
	@Test
	public void testGetCellFormula(){
		table = new Table(2,2);
		Cell cell = new Cell(table);
		cell.setFormula("5+12");
		assertEquals("5+12", cell.getFormula());
		//TODO null formula validation
		cell.setFormula(null);
		assertEquals("0.0", cell.getFormula());
	}
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferencing() {
		table = new Table(2,2);
		Cell selected = table.selectCell("A1");
		selected.setFormula("5");
		
		Cell selected2 = table.selectCell("A2");
		selected2.setFormula("10");
		
		Cell referencing = table.selectCell("B1");
		referencing.setFormula("A1+A2");
		
		assertEquals("Should be 15", "15.0", referencing.getValueString());

	}
	
	@Test
	public void testGetValue() {
		table = new Table(2,2);
		Cell cell = new Cell(table);
		cell.setFormula("23+27");
		assertEquals(50.0, cell.getValue(),0.0001);
	}
	
	@Test
	public void testGetValueString() {
		table = new Table(2,2);
		Cell cell = new Cell(table);
		cell.setFormula("23+27");
		assertEquals("50.0", cell.getValueString());
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
