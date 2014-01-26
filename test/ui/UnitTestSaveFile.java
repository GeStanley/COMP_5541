package ui;

import java.lang.reflect.Method;

import org.junit.*;

import static org.junit.Assert.*;
import structure.Table;
import structure.Cell;
import structure.Table.NullCellPointer;
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
		tester.populate();
		saved = new SaveFile(tester);
		Cell[] tmp = new Cell[4];
		String in = "\"1\",\"2\",\"3\",\"4\"";
		
		// Test parsing a CSV line
		try {
			tmp = saved.parseLine(4,in);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertEquals(3.0, tmp[2].getValue(), 0);
		assertEquals(1.0, tmp[0].getValue(), 0);
		assertEquals(4.0, tmp[3].getValue(), 0);
		
		// Test generating a line of csv
		try {
			assertEquals("Should be" + in, in, saved.rowToCSV(tmp));
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test conversion of rows to CSV
	 */
	@Test
	public void testRow2CSV() {
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		
		String line = "\"1\",\"2\",\"3\",\"4\"";
		String lineTemp = line;
		
		Cell[] row;
		String[] parts;
		int count;
		
		// Remove leading and trailing quotes and commas
		if (line.charAt(0) == '"')
			line = line.substring(1);
		if (line.charAt(line.length()-1) == ',')
			line = line.substring(0,line.length()-1);
		if (line.charAt(line.length()-1) == '"')
			line = line.substring(0,line.length()-1);
		
		parts = line.split("\",\"");
		row = new Cell[parts.length];
		
		for (count=0; count<parts.length; count++) {
			row[count] = new Cell(tester, parts[count]);
		}
		try {
			String out= saved.rowToCSV(row);
			assertEquals(lineTemp, out);
		
			assertEquals(null, saved.rowToCSV(null));
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
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

}
