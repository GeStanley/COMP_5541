package ui;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.*;
import static org.junit.Assert.*;
import structure.Cell;
import structure.Table;


public class UnitTestConsole {
		
	private ByteArrayOutputStream output;
	private ByteArrayInputStream input;
	private Table testable, comparable;
	private File theFile;
	private static String tmpFile = "./tmp.csv";
	private String[] args, outputLines;
	
	/**
	 * Set up a system to capture outputs
	 * 
	 * @throws Exception 
	 */
	@Before
	public void before() throws Exception {
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		testable = new Table(5,5);
		testable.populate();
		comparable = new Table(5,5);
		comparable.populate();
		args = new String[0];
		
		// Set up some values in comparable
		comparable.selectCell("A1");
		comparable.insertToCell("1.0");
		comparable.selectCell("C1");
		comparable.insertToCell("A1*3");
		
		// Create a file for testing
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
			writer.println("\"1.0\",\"\",\"A1*3\",\"\",\"\"");
			writer.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset the system streams and remove testing files
	 */
	@After
	public void after() {
		System.setIn(System.in);
		System.setOut(System.out);
		output = null;
		input = null;
		testable = null;
		comparable = null;
		Console.testTable(null);
		
		// Delete the testing file
		File theFile = new File(tmpFile);
		theFile.delete();
		theFile = null;
	}
	
	/**
	 * Test create new - disabled
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	//@Test
	public void testCreateNew() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Iteration 2 - find a way to test this in the GUI
	}
	
	/**
	 * Test the select functionality
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testSelect() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String ops = "A1\n1.0\nC1\nA1*3\nquit\n";
		ByteArrayInputStream input = new ByteArrayInputStream(ops.getBytes());
		input.reset();
		System.setIn(input);
		Console.testTable(testable); // Use the testable for this
		Console.main(args);
		
	    Cell a1 = testable.getCell(0,0);
	    assertEquals("1.0 = 1.0", a1.toString());
	    assertTrue("Comparing tables", comparable.equals(testable));
	}
	
	/**
	 * Test welcome message
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testWelcome() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] lines = ("**************************************************************\n" +
				"*                       CALCUL-O-MATIC                       *\n" +
				"*                             by                             *\n" +
				"*                           TEAM 2                           *\n" +
				"**************************************************************\n").split("\n");
	    Method welcomeMethod = getMethodOfClass(Console.class, "welcome");
	    welcomeMethod.invoke(Console.class);
	    outputLines = output.toString().split("\n");
	    for (int i=lines.length-1; i>=0; i--) { // Page backwards through the results comparing to expected output
	    	assertEquals(lines[i], outputLines[outputLines.length-lines.length+i]);
	    }
	}
	
	/**
	 * Test help
	 */
	@Test
	public void testHelp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    String[] commands = (
				"\tDisplay\n" +
				"\tOpen\n" +
				"\tCreate New\n" +
				"\tSave\n" +
				"\t[letter][number] of the column and row to select a cell\n" +
				"\tQuit\n" +
				"\t\"Help\" to see this again\n").split("\n");
	    Method helpMethod = getMethodOfClass(Console.class, "help");
	    helpMethod.invoke(Console.class);
	    outputLines = output.toString().split("\n");
	    for (int i=commands.length-1; i>=0; i--) { // Page backwards through the results comparing to expected output
	    	assertEquals(commands[i], outputLines[outputLines.length-commands.length+i]);
	    }
	}
	
	/**
	 * Test console save file
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testSave() throws IOException {
		String saveOps = "A1\n1.0\nC1\nA1*3\nsave\n" + tmpFile + "\nquit";
		ByteArrayInputStream input = new ByteArrayInputStream(saveOps.getBytes());
		input.reset();
		
		// perform the save operation
		System.setIn(input);
		Console.testTable(testable); // Use the testable for this
		Console.main(new String[0]);
		outputLines = output.toString().split("\n");
		assertEquals("Success: file saved", outputLines[outputLines.length-2]);
	}
	
	/**
	 * Test console load file
	 */
	@Test
	public void testOpen() {
		String loadOps = "open\n" + tmpFile + "\nquit\n";
		ByteArrayInputStream input = new ByteArrayInputStream(loadOps.getBytes());
	
		// perform the load op
		
		System.setIn(input);
		Console.testTable(testable); // Use the testable table 
		Console.main(new String[0]);
		outputLines = output.toString().split("\n");
		assertEquals("Success: file loaded", outputLines[outputLines.length-9]);
		assertTrue("Compare table states", testable.equals(comparable));
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
