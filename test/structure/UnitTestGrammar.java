package structure;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import structure.Grammar.Formula;

/**
 * @author Nick
 * This Junit class does a unit test on the Grammar class 
 * to ensure that there are no errors using it as a
 * 'black box' for grammar
 */
public class UnitTestGrammar {
	
	Grammar.Formula tester;

	/**
	 * Tests all the private methods in the Grammar.Formula class sequentially
	 */
	@Test
	public void testAllPrivateMethods() throws Exception{	
		String currentResult;
		
		currentResult = testAGrammarPrivateMethod("addition", "5p5");
		assertEquals(10.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("subtraction", "7m5");
		assertEquals(2.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("multiplication", "7u5");
		assertEquals(35.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("division", "80d4");
		assertEquals(20.0,Double.parseDouble(currentResult),0);

		currentResult = testAGrammarPrivateMethod("calculate", "5p5");
		assertEquals(10.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("calculate", "7m5");
		assertEquals(2.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("calculate", "7u5");
		assertEquals(35.0,Double.parseDouble(currentResult),0);
		
		currentResult = testAGrammarPrivateMethod("calculate", "80d4");
		assertEquals(20.0,Double.parseDouble(currentResult),0);		
		
	}
	
	/**
	 * Tests a single private method in the grammar class
	 * @param methodName name of the private method to verify
	 * @param formula the formula input, should be based on formula
	 * class-style input such as "5p5" for "5+5"
	 * @return returns the result after evaluation for assertion
	 * @throws Exception
	 */
	private String testAGrammarPrivateMethod(String methodName, 
			String formula) throws Exception{
		Grammar.Formula testPrivate = new Grammar.Formula();
		Method testMe = getMethodOfClass(Grammar.Formula.class, methodName);
		String result = (String) testMe.invoke(testPrivate, formula);
		return result;
	}	
	
	private Method getMethodOfClass(Class testClass, String methodName) 
			throws NoSuchMethodException, SecurityException{
		Method method = testClass.getDeclaredMethod(methodName, String.class);
		method.setAccessible(true);
		return method;
	}


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
