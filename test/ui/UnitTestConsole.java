package ui;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import structure.Cell;
import structure.Table;
import org.junit.After;

public class UnitTestConsole {
		
	/**
	 * Reset the system streams
	 */
	@After
	public void after() {
		System.setIn(System.in);
		System.setOut(System.out);
	}
	
	@Test
	public void testCreateNew() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method expandMethod = getMethodOfClass(Console.class, "createNew");
	    expandMethod.invoke(Console.class);
		//assertEquals(10, console.getTable().getLength());
	}
	
	@Test
	public void testSelect() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String address = "A1";
	    Method selectMethod = getMethodOfClass(Console.class, "select");
	    selectMethod.invoke(Console.class, address);
	}
	
	@Test
	public void testWelcome() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method welcomeMethod = getMethodOfClass(Console.class, "welcome");
	    welcomeMethod.invoke(Console.class);
	}
	
	@Test
	public void testHelp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method helpMethod = getMethodOfClass(Console.class, "help");
	    helpMethod.invoke(Console.class);
	}
	
	/**
	 * Test console save and load files
	 */
	@Test
	public void testSave() {
		String[] outputLines;
		String testRow;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		// perform the save operation
		System.setIn(new ByteArrayInputStream("A1\n1.0\nC1\nA1*3\nsave\n./tmp.csv\nquit".getBytes()));
		System.setOut(new PrintStream(output));
		Console.main(new String[0]);
		
		outputLines = output.toString().split("\n");
		testRow = outputLines[outputLines.length-14];
		assertEquals("Success: file saved", outputLines[outputLines.length-2]);

		// perform the load op
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		System.setIn(new ByteArrayInputStream("open\nquit\n".getBytes()));
		Console.main(new String[0]);
		outputLines = output.toString().split("\n");
		assertEquals("Success: file loaded", outputLines[outputLines.length-1]);
		assertEquals(testRow, outputLines[outputLines.length-1]);
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
