package ui;

import java.awt.event.KeyEvent;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.timing.Pause;
import org.junit.Before;
import org.junit.Test;

public class UnitTestGuiAuto {

	private FrameFixture gui;

	@Before
	public void onSetUp() {
		Gui frame = GuiActionRunner.execute(new GuiQuery<Gui>() {
			protected Gui executeInEDT() {
				return new Gui();
			}
		});
		
		
		gui = new FrameFixture(frame);
	    gui.show();
	}

	@Test
	public void test() {
		JTextComponentFixture input = gui.textBox();
		JTableFixture table = gui.table("data");
		pause();
		table.selectCell(TableCell.row(0).column(0));
		pause();
		input.deleteText();
		pause();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		
		pause();
		
		gui.button("create").click();
	}
	
	public void pause(){
		Pause.pause(500);
		
	}


}
