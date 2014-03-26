package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionEvent;

import org.junit.Test;

public class UnitTestGui {
	
	
	Gui testGui;
	
	@Test
	public void testTableConstructor() {
		
		testGui = new Gui();
		assertNotNull(testGui);
		
	}
	
	@Test
	public void testInputPropertyChange() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException {
		
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
		
		assertEquals("Message: D4: Cell abc could not be referenced!",testGui.inputLine.getMsg());
		assertEquals("0.0",Gui.spreadsheet.getValueAt(2, 2).toString());
	}
	
	@Test
	public void testSelectPropertyChange(){
		testGui = new Gui();
		int count = 1 ;
		
		//simple usage
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				Gui.spreadsheet.setRowSelectionInterval(i, i);
				Gui.spreadsheet.setColumnSelectionInterval(j, j);
				testGui.inputLine.setText(Integer.toString(count));
				testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
				count++;
			}
		}
		
		count=1;
		
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				Gui.spreadsheet.setRowSelectionInterval(i, i);
				Gui.spreadsheet.setColumnSelectionInterval(j, j);
				
				assertEquals(Integer.toString(count),testGui.inputLine.input.getText());
				
				count++;
			}
		}
		
		//simple formula
		Gui.spreadsheet.setRowSelectionInterval(3, 3);
		Gui.spreadsheet.setColumnSelectionInterval(3, 3);
		testGui.inputLine.setText("2+3");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("2+3",testGui.inputLine.input.getText());
		
		
		//blank case
		Gui.spreadsheet.setRowSelectionInterval(7, 7);
		Gui.spreadsheet.setColumnSelectionInterval(7, 7);
		testGui.inputLine.setText("");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		assertEquals("0.0",testGui.inputLine.input.getText());
		
		//null case
		Gui.spreadsheet.setRowSelectionInterval(8, 8);
		Gui.spreadsheet.setColumnSelectionInterval(8, 8);
		testGui.inputLine.setText(null);
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("0.0",testGui.inputLine.input.getText());
		
		//exception case
		Gui.spreadsheet.setRowSelectionInterval(2, 2);
		Gui.spreadsheet.setColumnSelectionInterval(2, 2);
		testGui.inputLine.setText("abc");
		testGui.inputLine.actionPerformed(new ActionEvent(testGui.inputLine,ActionEvent.ACTION_PERFORMED,""));
		
		assertEquals("0.0",testGui.inputLine.input.getText());
	}
	
	@Test
	public void testSavePropertyChange(){
		//TODO implement this test.
		//challenge is to fire property change event on the gui level and interpret if the save functionality is properly triggered
	}
	
	@Test
	public void testLoadPropertyChange(){
		//TODO implement this test.
		//same as above
	}
	
	@Test
	public void testCreateNewPropertyChange(){
		//TODO implement this test.
		//same as above
	}
	
	
	
	@Test
	public void openFile(){
		
		
		
	}

	
	
	
}
