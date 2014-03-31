package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import structure.Cell;
import structure.Table;

/**
 * This tests the Saving and Loading component of the program.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * @author	Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

public class UnitTestSaveFile {
	
	private Table tester, loaded;
	private SaveFile saved;
	private File theFile;
	private static String tmpFile = "./tmp.csv";
	private static String brokenPath = "./this/directory/should/not/exist.csv";
	private static String csvRow = "\"1\",\"2\",\"3\",\"4\"";
	
	/**
	 * Before the tests
	 */
	@Before
	public void before() {
		tester = new Table(5,5);
		try {
			tester.selectCell("A1");
			tester.insertToCell("1");
			tester.selectCell("B1");
			tester.insertToCell("2");
			tester.selectCell("C1");
			tester.insertToCell("3");
			tester.selectCell("D1");
			tester.insertToCell("4");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		loaded = new Table(5,5);
		saved = new SaveFile(loaded);
		theFile = new File(tmpFile);
		try {
			theFile.createNewFile();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(theFile);
			writer.println(csvRow);
			writer.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * After the tests
	 */
	@After
	public void after() {
		tester = null;
		loaded = null;
		saved = null;
		theFile = new File(tmpFile);
		theFile.delete();
		theFile = null;
	}
	
	/**
	 * Test importing and exporting lines of csv code
	 * 
	 * TODO rewrite this test using reflection
	 */
	@Test
	public void testImportExportLine() {
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
	 * Test save functionality
	 * 
	 * Try:	an existing file
	 * 		a new file
	 * 		a broken path
	 * 		a null path
	 * 		an empty path
	 */
	@Test
	public void testSave(){
		
		String newFile = "./new.csv";
		File newbie;
		
		String defaultMsg = "No message";
		String successMsg = "Success: file saved";
		String errorMsg = "Error: file could not be opened for writing.";
		String outMsg;
		
		// test a new file
		outMsg = saved.save(newFile);
		assertEquals(outMsg, successMsg);
		newbie = new File(newFile);
		newbie.delete();
		
		// test a working file path
		outMsg = saved.save(tmpFile);
		assertEquals(successMsg, outMsg);
		
		// test a broken file path
		outMsg = saved.save(brokenPath);
		assertEquals(errorMsg, outMsg);
		
		// test a null file path
		outMsg = saved.save(null);
		assertEquals(errorMsg, outMsg);
		
		// test a empty file path
		outMsg = saved.save("");
		assertEquals(errorMsg, outMsg);
	}
	
	/**
	 * Test loading a table
	 *
	 * Try:	an existing file
	 * 		a broken path
	 * 		a null path
	 * 		an empty path
	 */
	@Test
	public void testLoad(){
		
		String defaultMsg = "No message";
		String successMsg = "Success: file loaded";
		String errorMsg = "Error: file could not be opened for reading.";
		
		// test a working file path
		String outMsg = saved.load(tmpFile);
		assertEquals(outMsg, successMsg);
		assertTrue("Compare test table to loaded table.", tester.equals(loaded));
		
		// test a broken file path
		outMsg = saved.load(brokenPath);
		assertTrue(errorMsg.equals(outMsg));
		
		// test a null file path
		outMsg = saved.load(null);
		assertTrue(errorMsg.equals(outMsg));
		
		// test a empty file path
		outMsg = saved.load("");
		assertTrue(errorMsg.equals(outMsg));
		
		
	}
	
	/**
	 * Test openRead private method
	 * 
	 * 	 * Try:	an existing file
	 * 		a broken path
	 * 		a null path
	 * 		an empty path
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testOpenRead() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String path;
		
		// test a working file path
	    Method openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    boolean result = (boolean) openReadMethod.invoke(saved, tmpFile);
		assertEquals(true,result);
		
		// test a broken file path
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, brokenPath);
		assertEquals(false,result);
		
		// test a null file path
		path = null;
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, path);
		assertEquals(false,result);
		
		// test a empty file path
		path = "";
	    openReadMethod = getMethodOfClass(SaveFile.class, "openRead");
	    result = (boolean) openReadMethod.invoke(saved, path);
		assertEquals(false,result);
		
	}

	/**
	 * Test openWrite private method
	 * 
	 * 	 * Try:	an existing file
	 * 		a broken path
	 * 		a null path
	 * 		an empty path
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testOpenWrite() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String path;
		
		// test the working file
	    Method openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
	    boolean result = (boolean) openWriteMethod.invoke(saved, tmpFile);
		assertEquals(true,result);
		
		// test a broken path
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, brokenPath);
		assertEquals(false,result);
		
		
		// test a null file value
		path = null;
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, path);
		assertEquals(false,result);
		
		
		// test an empty file path
		path = "";
		openWriteMethod = getMethodOfClass(SaveFile.class, "openWrite");
		result = (boolean) openWriteMethod.invoke(saved, path);
		assertEquals(false,result);
	}

	/**
	 * Helper to test private methods
	 * 
	 * @param argClass
	 * @param argMethodName
	 * @return
	 */
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
