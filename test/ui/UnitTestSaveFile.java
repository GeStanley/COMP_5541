package ui;

import java.lang.reflect.InvocationTargetException;
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
	 * 
	 * TODO rewrite this test using reflection
	 */
	@Test
	public void testImportExportLine() {
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
	
	@Test
	public void testSave(){
		tester = new Table(5,5);
		saved = new SaveFile(tester);
		
		String defaultMsg = "No message";
		String successMsg = "Success: file saved";
		String errorMsg = "Error: file could not be opened for writing.";
		
		String path = "./o1.csv";
		String outMsg = saved.save(path);
		assertEquals(outMsg, successMsg);
		
		path = "./abc.csv";
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
		
		String path = "./o1.csv";
		String outMsg = saved.load(path);
		assertEquals(outMsg, successMsg);
		
		path = "./abc.csv";
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
		String fileName = "./o1.csv";
	    Method openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    boolean result = (boolean) openReadMethod.invoke(saved, fileName);
		assertEquals(true,result);
		
		fileName = "./abc.csv";
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
		String fileName = "./o1.csv";
	    Method openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
	    boolean result = (boolean) openWriteMethod.invoke(saved, fileName);
		assertEquals(true,result);
		
		fileName = "./abc.csv";
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
