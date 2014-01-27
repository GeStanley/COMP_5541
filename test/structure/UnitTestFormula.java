package structure;

import static org.junit.Assert.*;
import java.lang.reflect.Method;
import org.junit.*;
import structure.*;

/**
 * This Junit class does a unit test on the Formula class 
 * to ensure that there are no errors using it as a
 * 'black box' for grammar
 * 
 * @author Nick
 */

public class UnitTestFormula {
	
	Formula tester;
	Table table;
	
	/**
	 * Pre conditions for tests
	 */
	@Before
	public void before() {
		table = new Table(5,5);
		tester = new Formula(null, table);
	}
	
	/**
	 * Post conditions for tests
	 */
	public void after() {
		table = null;
		tester = null;
	}
	
	
	/**
	 * Test all private methods in one go
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrivateMethodsFormula() throws Exception{
		boolean resultBool;
		double resultDouble;
		String methodName;
		Method testMe;			
		
		//Test inNum
		Class params[] = new Class[1];
		params[0] = char.class; 
		methodName = "inNum";
		testMe = getMethodOfClass(structure.Formula.class, methodName, params);		
		//Should passs
		resultBool = (boolean) testMe.invoke(tester, '9');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '0');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '4');
		assertTrue(resultBool);
		//Should fail
		resultBool = (boolean) testMe.invoke(tester, 'a');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '-');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '~');
		assertFalse(resultBool);
		
		//Test isLetter
		methodName = "isLetter";
		testMe = getMethodOfClass(structure.Formula.class, methodName, params);
		//Should pass
		resultBool = (boolean) testMe.invoke(tester, 'a');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'z');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'A');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'Z');
		assertTrue(resultBool);
		//Should fail
		resultBool = (boolean) testMe.invoke(tester, '3');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '?');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '*');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '/');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '$');
		assertFalse(resultBool);
		
		//Test isNumBound
		methodName = "isNumBound";
		testMe = getMethodOfClass(structure.Formula.class, methodName, params);		
		//Should pass
		resultBool = (boolean) testMe.invoke(tester, '+');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '-');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, ' ');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '(');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, ')');
		//Should fail
		resultBool = (boolean) testMe.invoke(tester, '?');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '%');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '$');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'j');
		assertFalse(resultBool);
		
		//Test isNumBound
		methodName = "isOp";
		testMe = getMethodOfClass(structure.Formula.class, methodName, params);		
		//Should pass
		resultBool = (boolean) testMe.invoke(tester, '+');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '-');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '*');
		assertTrue(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '/');
		assertTrue(resultBool);
		//Should fail
		resultBool = (boolean) testMe.invoke(tester, '3');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '%');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, '@');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'j');
		assertFalse(resultBool);
		resultBool = (boolean) testMe.invoke(tester, 'Z');
		assertFalse(resultBool);
		
		//Test calc
		methodName = "calc";
		Class params2[] = new Class[3];
		params2[0] = double.class;
		params2[1] = char.class; 
		params2[2] = double.class;
		testMe = getMethodOfClass(structure.Formula.class, methodName, params2);
		//Should pass
		resultDouble = (double) testMe.invoke(tester, 3.0, '+', 4.0);
		assertEquals(7.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, 8.0, '-', 4.0);
		assertEquals(4.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, 3.0, '*', 4.0);
		assertEquals(12.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, 3.0, '/', 4.0);
		assertEquals(0.75,resultDouble,0);	
		resultDouble = (double) testMe.invoke(tester, -1.0, '+', 4.0);
		assertEquals(3.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -7, '-', 4.0);
		assertEquals(-11.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -3.0, '*', 4.0);
		assertEquals(-12.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -3.0, '/', 4.0);
		assertEquals(-0.75,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -1.0, '+', -4.0);
		assertEquals(-5.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -7, '-', -4.0);
		assertEquals(-3.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -3.0, '*', -4.0);
		assertEquals(12.0,resultDouble,0);
		resultDouble = (double) testMe.invoke(tester, -3.0, '/', -4.0);
		assertEquals(0.75,resultDouble,0);	
		
		/*//Test toString
		methodName = "toString()";
		testMe = getMethodOfClass(structure.Formula.class, methodName, params);
		//Should pass
		//Shoudl fail
	 	*/		
		
	}
	/**
	 * Helper to test private methods via reflection
	 * 
	 * @param testClass The class to be tested
	 * @param methodName The method to be found
	 * @return returns the method to the user 
	 * @throws NoSuchMethodException Method was not found
	 * @throws SecurityException
	 */
	private Method getMethodOfClass(Class testClass, String methodName, Class partypes[]) 
	throws NoSuchMethodException, SecurityException{
		Method method = testClass.getDeclaredMethod(methodName, partypes);
		method.setAccessible(true);
		return method;
	}	
	
	/**
	 * Test circular references
	 */
	@Test
	public void testCircularReference() {
		String a1 = "A1 + 12";
		String b3 = "B3 - 4";
		String c4 = "C4 * 8";
		boolean exception = true;
		
		// Basic test, insert a reference to itself into a cell
		try {
			table.selectCell("A1");
			table.insertToCell(a1);
			exception = false;
		}
		catch (Exception e) {
			// TODO update these with real values
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Open sub-condition in ", e.getMessage());	
		}
		assertTrue("An exception was thrown", exception);
		exception = true;
		
		// Complex test - B3 refers to C4 and C4 refers to B3
		try {
			table.selectCell("B3");
			table.insertToCell(c4);
			table.selectCell("C4");
			table.insertToCell(b3);
			exception = false;
		}
		catch (Exception e) {
			// TODO update these with real values
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("baba", e.getMessage());	
		}
		assertTrue("An exception was thrown", exception);
	}
	
	/**
	 * Test exceptions (except for circular references, tested on their own)
	 */
	@Test
	public void testExceptions() {
		String openClause = "1+(2+3";
		String openClauseAgain = "1+2+3)";
		String invalidChars = "7& + ???";
		String doubleOp = "18 ++ 19";
		String invalidReference = "Z99 + 13";

		// Test an open clause
		try {
			tester.evaluate(openClause);
		}
		catch (Exception e) {
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Open sub-condition in " + openClause, e.getMessage());
		}
		
		// Test another open clause
		try {
			tester.evaluate(openClauseAgain);
		}
		catch (Exception e) {
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Open sub-condition in " + openClauseAgain, e.getMessage());
		}
		
		// Test invalid characters
		try {
			tester.evaluate(invalidChars);
		}
		catch (Exception e) {
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Unsupported character at position 1 : \"&\"", e.getMessage());
		}
		
		// Test double operators
		try {
			tester.evaluate(doubleOp);
		}
		catch (Exception e) {
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Invalid operation at position 4", e.getMessage());
		}
		
		// Test invalid references
		try {
			tester.evaluate(invalidReference);
		}
		catch (Exception e) {
			assertEquals("class java.lang.Exception", e.getClass().toString());
			assertEquals("Cell Z99 could not be referenced!", e.getMessage());
		}
	}
	
	
	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferences() throws Exception {
		Cell selected;
		
		// Select a cell (and check it)
		selected = table.selectCell("A2");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		table.insertToCell("3.5");
		assertEquals("Should be 3.5", "3.5", selected.getValueString());
		
		// Select another cell and assign it a value
		selected = table.selectCell("C3");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		table.insertToCell("2.5");
		assertEquals("Should be 2.5", "2.5", selected.getValueString());
				
		// Calculate the sum of A2 and C3
		assertEquals(6.0,tester.evaluate("A2+C3"),0);
		
		// Insert a reference to A2 in D1
		selected = table.selectCell("D1");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		table.insertToCell("A2");
		assertEquals("Should be 3.5", "3.5", selected.getValueString());
		
		// Insert a formula using D1 in D2
		selected = table.selectCell("D2");
		assertEquals("Should be 0", "0.0", selected.getValueString());
		table.insertToCell("D1+1");
		assertEquals("Should be 4.5", "4.5", selected.getValueString());
	}
	
	/**
	 * Testing multiplication, addition, subtraction, division
	 */
	@Test
	public void testBasicOperations() throws Exception {		
		assertEquals(29.0,tester.evaluate("12+17"),0);
		assertEquals(24.0,tester.evaluate("3*8"),0);
		assertEquals(14.0,tester.evaluate("17-3"),0);
		assertEquals(6.0,tester.evaluate("18/3"),0);
	}
	
	/**
	 * Testing negative numbers
	 */
	@Test
	public void testNegativeNumbers() throws Exception {		
		assertEquals(-6.0,tester.evaluate("-2*3"),0);
		assertEquals(-1.0,tester.evaluate("-4/4"),0);
		assertEquals(-11.0,tester.evaluate("-8-3"),0);
		assertEquals(6.0,tester.evaluate("-2+8"),0);
	}
	
	/**
	 * Testing negative numbers combined with additional operations
	 */
	@Test
	public void testNegativeNumbersPlus() throws Exception {		
		assertEquals(6.0,tester.evaluate("-2*-3"),0);
		assertEquals(1.0,tester.evaluate("-4/-4"),0);
		assertEquals(-5.0,tester.evaluate("-8--3"),0);
		assertEquals(-10.0,tester.evaluate("-2+-8"),0);
	}	
	
	/**
	 * Test polynomial operation which have multiple operations included
	 */
	@Test
	public void testPolynomialOperations() throws Exception {		
		assertEquals(22.0,tester.evaluate("(-2+5)*4+10"),0);
		assertEquals(32.0,tester.evaluate("2+5*4+10"),0);
		assertEquals(21.0,tester.evaluate("2+5+4+10"),0);
		assertEquals(-400,tester.evaluate("2*5*-4*10"),0);
		assertEquals(-16.0,tester.evaluate("(-5+1)*4"),0);
		assertEquals(2.0,tester.evaluate("(6/2)-1"),0);
		assertEquals(400.0,tester.evaluate("2*5*4*10"),0);
		assertEquals(5.0,tester.evaluate("(5+3*5)/4"),0);
		assertEquals(20.0,tester.evaluate("((5+3)*(2*5))/4"),0);
		assertEquals(2.5,tester.evaluate("(100/2/10)/2"),0);
	}
	
	/**
	 * Test spaces and brackets
	 */
	@Test
	public void testMiscOperations() throws Exception {
		assertEquals(17.0,tester.evaluate("8 + 9"),0);
		assertEquals(2.5,tester.evaluate("(2.5)"),0);
	}
	
}
