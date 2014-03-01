package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import structure.Cell;
import structure.Table;

public class UnitTestGridModel {

	GridModel testGrid;
	
	@Test
	public void testTableConstructor() {
		testGrid = new GridModel(null);
		testGrid = new GridModel(new Table());
	}
	
	@Test
	public void testDimensionConstructor() {
		int i=0;
		int j=0;
		
		testGrid = new GridModel(i,j);

	}
	
	@Test
	public void testDimensionRetreaval() {
		testGrid = new GridModel();
		assertEquals(10,testGrid.getRowCount());
		assertEquals(11,testGrid.getColumnCount());
		
		testGrid = new GridModel(20,20);
		assertEquals(20,testGrid.getRowCount());
		assertEquals(20,testGrid.getColumnCount());
		
		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		assertEquals(15,testGrid.getRowCount());
		assertEquals(15,testGrid.getColumnCount());
		
	}
	
	@Test
	public void testGetValue() throws NumberFormatException, Exception {

		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		
		testTable.getCell(5, 5).setFormula("5+5");
		testTable.selectCell(5, 5);
		
		assertEquals("10.0",testGrid.getValueAt(5, 5).toString());
		
		testTable.getCell(4, 4).setFormula(null);
		testTable.selectCell(4, 4);
		
		assertEquals("0.0",testGrid.getValueAt(4, 4).toString());
		
		testTable.getCell(3, 3).setFormula("0.0");
		testTable.selectCell(3, 3);
		
		assertEquals("0.0",testGrid.getValueAt(3, 3).toString());
		
		
		// this can't be tested this way, because the error catching for a failed set
		// is done in GridModel, not in the cell. This is already tested in this UnitTest
		// in the method testSetValueAt() and it works, therefore it will be ommitted.
		//testTable.getCell(2, 2).setFormula("abc");
		//testTable.selectCell(2, 2);
		
		//assertEquals("0.0",testGrid.getValueAt(2, 2).toString());
	}
	
	@Test
	public void testSelectCell() throws NumberFormatException, Exception {

		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		
		Cell selection = testGrid.select(1, 1);
		
		assertEquals(testTable.getSelectedCell(), selection);
	}
	
	@Test
	public void testSetValueAt(){
		
		testGrid = new GridModel(5,5);
		
		assertEquals("Out of bounds.", testGrid.setValueAt(7, 7, "2+2"));
		
		testGrid.setValueAt(1, 1, "2+2");
		assertEquals("4.0",testGrid.getValueAt(1, 1).toString());
		
		
		testGrid.setValueAt(2, 2, "2.2+2.4");
		assertEquals("4.6",testGrid.getValueAt(2, 2).toString());
		
		testGrid.setValueAt(3, 3, "abvc");
		assertEquals("0.0",testGrid.getValueAt(3, 3).toString());
		
	}
}
