package structure;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * 	This class handles the clipboard functions; copy, cut and paste.
 * 
 * @author Nick Reinlein
 *
 */

public class ClipboardControl {
	private static Cell clipboardCell;
	private static Table currentTable;
	
	/**
	 * This sets up the current Table
	 * 
	 * @param table The current Table object of the spreadsheet
	 */
	public ClipboardControl(Table table){
		currentTable = table;
	}
	
	/**
	 * Copies the currently selected Cell
	 */
	public void copy(){
		if (currentTable.isCellSelected()){
			Cell copyMe = currentTable.getSelectedCell();
			if (copyMe != null){
				clipboardCell = new Cell(copyMe);
			}
		}
	}
	
	/**
	 * Cuts the currently selected Cell. The Cell is reset.
	 */
	public void cut(){
		copy();
		if (currentTable.isCellSelected()){
			currentTable.getSelectedCell().resetCell();
		}
	}
	
	/**
	 * Copies the stored Cell data to the currently selected Cell.
	 */
	public void paste(){
		if (currentTable.isCellSelected() && clipboardCell != null){
			try {
				int[] pasteToAddress = new int[2];
				pasteToAddress = currentTable.findCell(currentTable.getSelectedCell()); 
				currentTable.setCell(pasteToAddress[0], pasteToAddress[1], clipboardCell);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}