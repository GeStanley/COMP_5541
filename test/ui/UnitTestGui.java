package ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class UnitTestGui {
	
	Gui testGui;
	
	@Test
	public void testTableConstructor() {
		
		testGui = new Gui();
		assertNotNull(testGui);
		
	}
	
	@Test
	public void testPropertyChange() {
		fail("needs to be implemented. I need to figure out reflection to do so.");
	}
	
}
