package ui;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestStress {
	
	
	private FrameFixture gui;
	private JTextComponentFixture input;
	private JTableFixture table;

	@Before
	public void onSetUp() {
		Gui frame = GuiActionRunner.execute(new GuiQuery<Gui>() {
			protected Gui executeInEDT() {
				return new Gui();
			}
		});

		// create and display the frame
		gui = new FrameFixture(frame);
		gui.show(new Dimension(750, 500));

		// create all the objects required for testing.
		input = gui.textBox();
		table = gui.table("data");
	}

	@After
	public void tearDown() {
		// pause("ll");
		gui.cleanUp();
	}

	@Test
	public void testStress() {
		char col = 'A';
		int ic = 0;
		
		int test = 1;
		
		for (int i = 0; i < 20; i++) {
			ic = 0;
			for (int j = 0; j < 20; j++) {
				table.selectCell(TableCell.row(i).column(j));
				input.deleteText();
				
				if (i == 0 && j == 0) {
					input.enterText("1");
				} else if (j == 0) {
					String s = "" + String.valueOf((char)(col + 19)) + i + " + 1";
					input.enterText(s);
				
				} else {
					String s = "" + String.valueOf((char)(col + ic)) + (i+1) + " + 1";
					input.enterText(s);
					ic++;
				}
				
				input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
				
				assertEquals(""+test+".0", table.selectionValue()); // check if still computing the value properly
				test++;
			}
		}
		
	}

}
