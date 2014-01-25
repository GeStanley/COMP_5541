package structure;

import static org.junit.Assert.*;

import org.junit.Test;

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
		
		Cell cell3 = new Cell(5.5);
		
		assertEquals("Should be 5.5", "5.5", cell3.getValueString());
		assertEquals(5.5, cell3.getValue(),0.0001);
		
		Cell cell4 = new Cell(table, "2+5");
		
		assertEquals("Should be 7", "7.0", cell4.getValueString());
		assertEquals(7.0, cell4.getValue(),0.0001);
	}
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellFormula() {
		table = new Table(2,2);
		Cell cell = new Cell(table);
		
		cell.setFormula("23+27");
		
		assertEquals("Should be 50", "50.0", cell.getValueString());
		assertEquals(50.0, cell.getValue(),0.0001);
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
}
