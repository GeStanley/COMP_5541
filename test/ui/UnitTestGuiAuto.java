package ui;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JFileChooserFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests the Gui behaviour of the program, by using an automated testing
 * tool called FEST. 
 * 
 * @author Ankita Mishara, Geoffrey Stanley, Michael Burkat,
 * @author Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 *         Date 31-03-2014
 */

public class UnitTestGuiAuto {

	private FrameFixture gui;
	private JTextComponentFixture input;
	private JTableFixture table;
	private JButtonFixture create;
	private JButtonFixture save;
	private JButtonFixture load;
	private JLabelFixture message;
	private String workingDir;

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
		create = gui.button("create");
		save = gui.button("save");
		load = gui.button("load");
		message = gui.label("message");
		workingDir = System.getProperty("user.dir");

	}

	@After
	public void tearDown() {
		// pause("ll");
		gui.cleanUp();
	}

	@Test
	public void testCellInputs() {

		// check that value does not change when input is null
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("0.0", table.selectionValue());
		pause("s");

		// check that value does not change when a wrong input is entered
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
	public void testCicularReference() {
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
	public void testChangePropagation() {
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
	public void testSaveToFile() {

		// check if the file exists prior to test and delete it if it does.
		String saveFile = workingDir + "\\save.csv";
		System.out.println("savefile : " + saveFile);
		File file = new File(saveFile);
		if (file.exists()) {
			file.delete();
		}
		assertFalse(file.exists()); // check that the file was deleted or not
									// present

		// enter a value in the table
		table.selectCell(TableCell.row(0).column(0));
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());

		save.click();
		JFileChooserFixture fileChooser = gui.fileChooser();
		fileChooser.selectFile(file);
		pause("ll");
		fileChooser.approve();

		assertTrue(file.exists());

		pause("ll");

	}

	@Test
	public void testCreateNew() {
		table.selectCell(TableCell.row(0).column(0));
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());

		create.click();
		assertEquals("0.0", table.selectionValue());
	}

	@Test
	public void testLoadFromFile() {
		// check if the file exists prior to test and delete it if it does.
		String loadFile = workingDir + "\\data\\loadTest.csv";
		System.out.println("loadFile : " + loadFile);
		File file = new File(loadFile);
		assertTrue(file.exists()); // check that the file is present.

		// load the file
		load.click();
		JFileChooserFixture fileChooser = gui.fileChooser();
		fileChooser.selectFile(file);
		pause("l");
		fileChooser.approve();
		pause("l");

		// test that the file was loaded
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("1.0", table.selectionValue());

		table.selectCell(TableCell.row(1).column(1));
		assertEquals("2.0", table.selectionValue());

		table.selectCell(TableCell.row(2).column(2));
		assertEquals("3.0", table.selectionValue());

		table.selectCell(TableCell.row(3).column(3));
		assertEquals("6.0", table.selectionValue());
	}

	@Test
	public void testCopyPasteValue() {
		// copy a value
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(1).column(1));
		table.selectCell(TableCell.row(0).column(0));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_C)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(18).column(18));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_V)
				.releaseKey(KeyEvent.VK_CONTROL);

		// TODO re-selection shouldn't be needed.
		table.selectCell(TableCell.row(17).column(18));
		table.selectCell(TableCell.row(18).column(18));
		pause("ll");
		assertEquals("1.0", table.selectionValue());
	}

	@Test
	public void testCopyPasteFormula() {
		// copy a formula
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(10).column(4));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("A1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(10).column(5));
		table.selectCell(TableCell.row(10).column(4));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_C)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(5).column(16));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_V)
				.releaseKey(KeyEvent.VK_CONTROL);

		// TODO re-selection shouldn't be needed.
		table.selectCell(TableCell.row(5).column(15));
		table.selectCell(TableCell.row(5).column(16));
		pause("ll");
		assertEquals("1.0", table.selectionValue());

	}

	@Test
	public void testCutPasteValue() {
		// cut a value
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(1).column(1));
		table.selectCell(TableCell.row(0).column(0));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_X)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(18).column(18));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_V)
				.releaseKey(KeyEvent.VK_CONTROL);

		// TODO re-selection shouldn't be needed.
		table.selectCell(TableCell.row(17).column(18));
		table.selectCell(TableCell.row(18).column(18));
		assertEquals("1.0", table.selectionValue());
		pause("s");
		table.selectCell(TableCell.row(0).column(0));
		assertEquals("0.0", table.selectionValue());

		pause("s");
	}

	@Test
	public void testCutPasteFormula() {
		// cut a formula
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("1");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(10).column(4));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("B2");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("1.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(10).column(5));
		table.selectCell(TableCell.row(10).column(4));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_X)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(5).column(16));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_V)
				.releaseKey(KeyEvent.VK_CONTROL);

		// TODO re-selection shouldn't be needed.
		table.selectCell(TableCell.row(5).column(15));
		table.selectCell(TableCell.row(5).column(16));
		pause("ll");
		assertEquals("1.0", table.selectionValue()); // cheking if the pasted
														// cell contains the
														// value expected

		table.selectCell(TableCell.row(10).column(4));
		assertEquals("0.0", table.selectionValue()); // checking if the cell
														// that was cut from is
														// empty
		pause("ll");

		table.selectCell(TableCell.row(1).column(1));
		assertEquals("1.0", table.selectionValue()); // checking if the original
														// cell still has the
														// original value

	}

	@Test
	public void testCellFormatChange() {
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("0.0", table.selectionValue());
		pause("s");
		input.deleteText();
		input.enterText("5");
		input.pressAndReleaseKeys(KeyEvent.VK_ENTER);
		assertEquals("5.0", table.selectionValue());
		pause("s");

		table.selectCell(TableCell.row(0).column(0));
		table.selectCell(TableCell.row(1).column(1));
		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_2)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");

		table.selectCell(TableCell.row(0).column(0));
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("$5.00", table.selectionValue()); // check for money format

		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_3)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(0).column(0));
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("5E0", table.selectionValue()); // check for scientific
														// format

		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_4)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(0).column(0));
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("5", table.selectionValue()); // check for integer
													// format

		table.pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_1)
				.releaseKey(KeyEvent.VK_CONTROL);
		pause("s");
		table.selectCell(TableCell.row(0).column(0));
		table.selectCell(TableCell.row(1).column(1));
		assertEquals("5.0", table.selectionValue()); // check for default
														// format
	}

	public void pause(String s) {
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
