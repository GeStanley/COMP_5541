package ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	@Test
	public void testSave(){
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		
		String defaultMsg = "No message";
		String successMsg = "Success: file saved";
		String errorMsg = "Error: file could not be opened for writing.";
		
		String path = "o1.csv";
		String outMsg = saved.save(path);
		assertEquals(outMsg, successMsg);
		
		path = "c:///~/abc.csv";
		outMsg = saved.save(path);
		assertTrue(errorMsg.equals(outMsg));
		
		path = null;
		outMsg = saved.save(path);
		assertTrue(errorMsg.equals(outMsg));
		
		path = "";
		outMsg = saved.save(path);
		assertTrue(errorMsg.equals(outMsg));
		
		
	}
	
	@Test
	public void testLoad(){
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		
		String defaultMsg = "No message";
		String successMsg = "Success: file loaded";
		String errorMsg = "Error: file could not be opened for reading.";
		
		String path = "o1.csv";
		String outMsg = saved.load(path);
		assertEquals(outMsg, successMsg);
		
		path = "c:///~/abc.csv";
		outMsg = saved.load(path);
		assertTrue(errorMsg.equals(outMsg));
		
		path = null;
		outMsg = saved.load(path);
		assertTrue(errorMsg.equals(outMsg));
		
		path = "";
		outMsg = saved.load(path);
		assertTrue(errorMsg.equals(outMsg));
		
		
	}
	
	@Test
	public void testOpenRead() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		//update the filename variable to the current csv file
		String fileName = "o1.csv";
	    Method openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    boolean result = (boolean) openReadMethod.invoke(saved, fileName);
		assertEquals(true,result);
		
		fileName = "c:///~/#/abc.csv";
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
		fileName = null;
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
		fileName = "";
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
	}
	
	@Test
	public void testOpenWrite() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		//update the filename variable to the current csv file
		String fileName = "o1.csv";
	    Method openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
	    boolean result = (boolean) openWriteMethod.invoke(saved, fileName);
		assertEquals(true,result);
		
		fileName = "c:///~/#/abc.csv";
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
		fileName = null;
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
		fileName = "";
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, fileName);
		assertEquals(false,result);
		
	}
	
	
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
		
		String out= saved.rowToCSV(row);
		assertEquals(lineTemp, out);
		
		//assertEquals(null, saved.rowToCSV(null));
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
