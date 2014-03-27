package ui;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestGuiAuto {

	private FrameFixture gui;
	private JTextComponentFixture input;
	private JTableFixture table;
	private JButtonFixture create;
	private JButtonFixture save;
	private JButtonFixture saveAs;
	private JButtonFixture load;
	private JTableFixture cell;

	@Before
	public void onSetUp() {
		Gui frame = GuiActionRunner.execute(new GuiQuery<Gui>() {
			protected Gui executeInEDT() {
				return new Gui();
			}
		});
		
		// create and display the frame
		gui = new FrameFixture(frame);
	    gui.show();
	    
	    // create all the objects required for testing.
	    input = gui.textBox();
	    table = gui.table("data");
	    create = gui.button("create");
	    save = gui.button("save");
	    saveAs = gui.button("saveAs");
	    load = gui.button("load");
	    
	}
	
	@After
	public void tearDown() {
		//pause("ll");
		gui.cleanUp();
	}
	

		
		
		
	
	@Test
	public void testCellInputs(){
		
		//check that value does not change when input is null
		table.selectCell(TableCell.row(0).column(0));
		System.out.println(table.selectionValue());
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		input.deleteText();
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		
		//check that value does not change when a wron input is entered
		table.selectCell(TableCell.row(1).column(1));
		System.out.println(table.selectionValue());
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		input.deleteText();
		input.enterText("3+rt");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		
		// check that you can enter a value
		table.selectCell(TableCell.row(2).column(2));
		System.out.println(table.selectionValue());
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		input.deleteText();
		input.enterText("10");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("10.0", table.selectionValue());
		pause("ll");
		
		// check that you can enter a formula
		table.selectCell(TableCell.row(3).column(3));
		System.out.println(table.selectionValue());
		assertEquals("0.0", table.selectionValue());
		pause("ll");
		input.deleteText();
		input.enterText("A1+C3");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("10.0", table.selectionValue());
		pause("ll");
		
	}
	
	@Test
	public void testCicularReference(){
		//TODO implement this test.
	}
	
	@Test
	public void testChangePropagation(){
		//TODO implement this test.
	}
	
	@Test
	public void testSaveToFile(){
		//TODO implement this test.
	}
	
	
	@Test
	public void testCreateNew(){
		//TODO implement this test.
	}
	
	public void testLoadFromFile(){
		//TODO implement this test.
	}
	
	@Test
	public void testCopyPaste(){
		//TODO implement this test.
	}
	
	@Test
	public void testCutPaste(){
		//TODO implement this test.
	}
	
	@Test
	public void testCellFormatChange(){
		//TODO implement this test.
	}
	
	
	
	public void pause(String s){
		switch (s) {
		case "s":
			Pause.pause(500);
			break;
		case "l":
			Pause.pause(1000);
			break;
		case "ll":
			Pause.pause(2000);
			break;
		}
	}


}
