package ui;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JLabelFixture;
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
	private JLabelFixture message;

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
	    message = gui.label("message");
	    
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
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("0.0", table.selectionValue());
		pause("s");
		
		//check that value does not change when a wrong input is entered
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("3+rt");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("0.0", table.selectionValue());
		pause("s");
		
		// check that you can enter a value
		table.selectCell(TableCell.row(2).column(2));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("10");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("10.0", table.selectionValue());
		pause("s");
		
		// check that you can enter a formula
		table.selectCell(TableCell.row(3).column(3));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("A1+C3");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("10.0", table.selectionValue());
		pause("s");
		
	}
	
	@Test
	public void testCicularReference(){
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(1));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("A1+1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("2.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(2));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("B1+1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("3.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("1.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("C1 + 1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		pause("s");
		
		// Tests if a circular reference message is shown.
		assertEquals("Message: A1: Circular Reference", message.text());
		pause("s");
		
	}
	
	@Test
	public void testChangePropagation(){
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(1));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("A1+1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("2.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(2));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("B1+1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("3.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("1.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("2");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("2.0", table.selectionValue());
		pause("s");
		
		table.selectCell(TableCell.row(0).column(1));
		assertEquals("3.0", table.selectionValue());
		table.selectCell(TableCell.row(0).column(2));
		assertEquals("4.0", table.selectionValue());
		pause("s");
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
