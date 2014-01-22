package structure;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Nick
 * This Junit class does a unit test on the Grammar class 
 * to ensure that there are no errors using it as a
 * 'black box' for grammar
 */
public class UnitTestGrammar {
	
	Grammar.Formula tester;

	/**
	 * Testing multiplication, addition, subtraction, division
	 */
	@Test
	public void testBasicOperations() throws Exception {
		tester = new Grammar.Formula();
		
		assertEquals(29.0,tester.formula("12+17"),0);
		assertEquals(24.0,tester.formula("3*8"),0);
		assertEquals(14.0,tester.formula("17-3"),0);
		assertEquals(6.0,tester.formula("18/3"),0);
	}
	
	/**
	 * Testing negative numbers
	 */
	@Test
	public void testNegativeNumbers() throws Exception {
		tester = new Grammar.Formula();
		
		assertEquals(-6.0,tester.formula("-2*3"),0);
		assertEquals(-1.0,tester.formula("-4/4"),0);
		assertEquals(-11.0,tester.formula("-8-3"),0);
		assertEquals(6.0,tester.formula("-2+8"),0);
	}
	
	/**
	 * Testing negative numbers combined with additional operations
	 */
	@Test
	public void testNegativeNumbersPlus() throws Exception {
		tester = new Grammar.Formula();
		
		assertEquals(6.0,tester.formula("-2*-3"),0);
		assertEquals(1.0,tester.formula("-4/-4"),0);
		assertEquals(-5.0,tester.formula("-8--3"),0);
		assertEquals(-10.0,tester.formula("-2+-8"),0);
	}	
	
	/**
	 * Test polynomial operation which have multiple operations included
	 */
	@Test
	public void testPolynomialOperations() throws Exception {
		tester = new Grammar.Formula();
		
		assertEquals(22.0,tester.formula("(-2+5)*4+10"),0);
		assertEquals(32.0,tester.formula("2+5*4+10"),0);
		assertEquals(21.0,tester.formula("2+5+4+10"),0);
		assertEquals(-400,tester.formula("2*5*-4*10"),0);
		assertEquals(-16.0,tester.formula("(-5+1)*4"),0);
		assertEquals(2.0,tester.formula("(6/2)-1"),0);
		assertEquals(400.0,tester.formula("2*5*4*10"),0);
		assertEquals(5.0,tester.formula("(5+3*5)/4"),0);
		assertEquals(20.0,tester.formula("((5+3)*(2*5))/4"),0);
		assertEquals(2.5,tester.formula("(100/2/10)/2"),0);

	}
	
	/**
	 * Test spaces and brackets
	 */
	@Test
	public void testMiscOperations() throws Exception {
		tester = new Grammar.Formula();
		
		assertEquals(17.0,tester.formula("8 + 9"),0);
		assertEquals(2.5,tester.formula("(2.5)"),0);
	}
	

}
