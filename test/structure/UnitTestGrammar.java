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

	/**
	 * Testing multiplication, addition, subtraction, division
	 */
	@Test
	public void testBasicOperations() {
		
		assertStatement(29.0,"12+17");
		assertStatement(24.0,"3*8");
		assertStatement(14.0,"17-3");
		assertStatement(6.0,"18/3");
	}
	
	/**
	 * Testing negative numbers
	 */
	@Test
	public void testNegativeNumbers(){		
		assertStatement(-6.0,"-2*3");
		assertStatement(-1.0,"-4/4");
		assertStatement(-11.0,"-8-3");
		assertStatement(6.0,"-2+8");
	}
	
	/**
	 * Testing negative numbers combined with additional operations
	 */
	@Test
	public void testNegativeNumbersPlus(){		
		assertStatement(6.0,"-2*-3");
		assertStatement(1.0,"-4/-4");
		assertStatement(-5.0,"-8--3");
		assertStatement(-10.0,"-2+-8");
	}	
	
	/**
	 * Test polynomial operation which have multiple operations included
	 */
	@Test
	public void testPolynomialOperations(){		
		assertStatement(22.0,"(-2+5)*4+10");
		assertStatement(32.0,"2+5*4+10");
		assertStatement(21.0,"2+5+4+10");
		assertStatement(-400,"2*5*-4*10");
		assertStatement(-16.0,"(-5+1)*4");
		assertStatement(2.0,"(6/2)-1");
		assertStatement(400.0,"2*5*4*10");
		assertStatement(5.0,"(5+3*5)/4");
		assertStatement(20.0,"((5+3)*(2*5))/4");
		assertStatement(2.5,"(100/2/10)/2");

	}
	
	/**
	 * Test spaces and brackets
	 */
	@Test
	public void testMiscOperations(){		
		assertStatement(17.0,"8 + 9");
		assertStatement(2.5,"(2.5)");
	}
	
	/**
	 * Generic public method to assert various statements
	 * @param expected value expected as a result
	 * @param String to be evaluated by Grammar.Formula. Values only no cell references.
	 */
	public void assertStatement(double expected, String given){
		Grammar.Formula testFormula = new Grammar.Formula();
		assertEquals(expected,Double.parseDouble(testFormula.formula(given)),0.01);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {		
	}

}
