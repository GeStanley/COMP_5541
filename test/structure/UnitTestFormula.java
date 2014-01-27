package structure;
import static org.junit.Assert.*;

import org.junit.Test;
import structure.*;

/**
 * This Junit class does a unit test on the Grammar class 
 * to ensure that there are no errors using it as a
 * 'black box' for grammar
 * 
 * @author Nick
 */
public class UnitTestFormula {
	
	Formula tester;
	Table table;

	/**
	 * Test cell referencing
	 */
	@Test
	public void testCellReferences() throws Exception {
		table = new Table(5,5);
		tester = new Formula(null, table);
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
	//@Test
	public void testBasicOperations() throws Exception {
		tester = new Formula();
		
		assertEquals(29.0,tester.evaluate("12+17"),0);
		assertEquals(24.0,tester.evaluate("3*8"),0);
		assertEquals(14.0,tester.evaluate("17-3"),0);
		assertEquals(6.0,tester.evaluate("18/3"),0);
	}
	
	/**
	 * Testing negative numbers
	 */
	//@Test
	public void testNegativeNumbers() throws Exception {
		tester = new Formula();
		
		assertEquals(-6.0,tester.evaluate("-2*3"),0);
		assertEquals(-1.0,tester.evaluate("-4/4"),0);
		assertEquals(-11.0,tester.evaluate("-8-3"),0);
		assertEquals(6.0,tester.evaluate("-2+8"),0);
	}
	
	/**
	 * Testing negative numbers combined with additional operations
	 */
	//@Test
	public void testNegativeNumbersPlus() throws Exception {
		tester = new Formula();
		
		assertEquals(6.0,tester.evaluate("-2*-3"),0);
		assertEquals(1.0,tester.evaluate("-4/-4"),0);
		assertEquals(-5.0,tester.evaluate("-8--3"),0);
		assertEquals(-10.0,tester.evaluate("-2+-8"),0);
	}	
	
	/**
	 * Test polynomial operation which have multiple operations included
	 */
	//@Test
	public void testPolynomialOperations() throws Exception {
		tester = new Formula();
		
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
	//@Test
	public void testMiscOperations() throws Exception {
		tester = new Formula();
		
		assertEquals(17.0,tester.evaluate("8 + 9"),0);
		assertEquals(2.5,tester.evaluate("(2.5)"),0);
	}
	
}
