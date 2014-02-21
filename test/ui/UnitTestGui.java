package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import org.junit.Test;

public class UnitTestGui {
	
	Gui testGui;
	
	@Test
	public void testTableConstructor() {
		
		testGui = new Gui();
		assertNotNull(testGui);
		
	}
	
	@Test
	public void testPropertyChange() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException {
		
		testGui = new Gui();
		
		//simple formula
		Gui.spreadsheet.setRowSelectionInterval(1, 1);
		Gui.spreadsheet.setColumnSelectionInterval(2, 2);
		
		testGui.inputLine.setText("2+3");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("5.0",Gui.spreadsheet.getValueAt(1, 2).toString());

		//no input
		Gui.spreadsheet.setRowSelectionInterval(2, 2);
		Gui.spreadsheet.setColumnSelectionInterval(2, 2);
		
		testGui.inputLine.setText("");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("0.0",Gui.spreadsheet.getValueAt(2, 2).toString());
		
		//null case
		Gui.spreadsheet.setRowSelectionInterval(3, 3);
		Gui.spreadsheet.setColumnSelectionInterval(3, 3);
		
		testGui.inputLine.setText(null);
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("0.0",Gui.spreadsheet.getValueAt(2, 2).toString());
		
		//exception case
		Gui.spreadsheet.setRowSelectionInterval(3, 3);
		Gui.spreadsheet.setColumnSelectionInterval(3, 3);
		
		testGui.inputLine.setText("abc");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("input error",testGui.inputLine.getMsg());
		assertEquals("0.0",Gui.spreadsheet.getValueAt(2, 2).toString());
	}
	
}
