package ui;

import static org.junit.Assert.*;

import org.junit.Test;

import structure.Cell;
import structure.Table;

public class testGridModel {
	
	GridModel testGrid;
	
	@Test
	public void testDimensionRetreaval() {
		testGrid = new GridModel();
		assertEquals(10,testGrid.getRowCount());
		assertEquals(11,testGrid.getColumnCount());
		
		//TODO This may need to change, but I'm assuming you are inserting an extra column to account for
		//row headers. As such, when creating a grid with provided dimensions it may be a good idea to add an
		//extra column automatically.
		testGrid = new GridModel(20,20);
		assertEquals(20,testGrid.getRowCount());
		assertEquals(21,testGrid.getColumnCount());
		
		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		assertEquals(15,testGrid.getRowCount());
		assertEquals(16,testGrid.getColumnCount());
	}
	
	@Test
	public void testGetValue() throws NumberFormatException, Exception {

		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		
		testTable.getCell(5, 5).setFormula("5+5");
		testTable.selectCell(5, 5);
		
		assertEquals("5+5",testGrid.getValueAt(5, 5));
	}
	
	@Test
	public void testSelectCell() throws NumberFormatException, Exception {

		Table testTable = new Table(15,15);
		testGrid = new GridModel(testTable);
		
		Cell selection = testGrid.select(1, 1);
		
		assertEquals(testTable.getSelectedCell(), selection);
	}
}
