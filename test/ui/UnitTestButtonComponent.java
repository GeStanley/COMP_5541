package ui;

import static org.junit.Assert.*;

import javax.swing.JFileChooser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class UnitTestButtonComponent {

	
	ButtonComponent buttonComponent;
	JFileChooser JFC;
	
	@Before
	public void before() {
		buttonComponent = new ButtonComponent();
		JFC = new JFileChooser();
		
		
	}
	
	@After
	public void after() {
		buttonComponent = null;
		JFC = null;
		
		
	}
	
	@Test
	public void testGetFileLocation() {
		try{
			ButtonComponent buttonComponent1= buttonComponent;
  		    JFileChooser JFC = new JFileChooser();
		    String loca1,loca2,directory,directory1;
		
		
		    loca1=buttonComponent1.getFileLocation("Save");
		    directory = JFC.getCurrentDirectory().toString();
		    //System.out.println( directory );
		    loca2=buttonComponent1.getFileLocation("Open");
		    directory1 = JFC.getCurrentDirectory().toString();
		    assertEquals("Should be ",loca1,loca2);
		    assertEquals("Should be ",directory1,directory);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
		
	}

}
