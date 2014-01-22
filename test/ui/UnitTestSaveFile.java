package ui;

import org.junit.*;
import static org.junit.Assert.*;
import structure.Table;
import structure.Cell;
import ui.SaveFile;

public class UnitTestSaveFile {
	
	private Table tester;
	private SaveFile saved;
	
	/**
	 * Test importing and exporting lines of csv code
	 */
	@Test
	public void testImportLine() {
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		Cell[] tmp;
		String in = "\"1\",\"2\",\"3\",\"4\"";
		
		// Test parsing a CSV line
		tmp = saved.parseLine(4,in);
		assertEquals(3.0, tmp[2].getValue(), 0);
		assertEquals(1.0, tmp[0].getValue(), 0);
		assertEquals(4.0, tmp[3].getValue(), 0);
		
		// Test generating a line of csv
		assertEquals("Should be" + in, in, saved.rowToCSV(tmp));
	}

}
