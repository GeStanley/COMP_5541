package structure;

import java.util.Map;

import javax.swing.JOptionPane;

/**
 * 	This class handles the clipboard functions; copy, cut and paste.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * 			Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

public class ClipboardControl {
	private static Cell clipboardCell;
	private static Table currentTable;
	private static int[] origCoordinates = new int[2];
	private Formula formula = new Formula();
	private static Map<Integer,int[]> newFormulaRefMap;

	
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
			if(copyMe.getFormula().charAt(0) !='@'){
				origCoordinates = currentTable.findCell(copyMe);
				newFormulaRefMap = formula.getRelativeFormula(copyMe, origCoordinates);
			}			
			
			if (copyMe != null){
				clipboardCell = new Cell(copyMe);
			}
		}
	}
	
	/**
	 * Cuts the currently selected Cell. The Cell is reset.
	 */
	public void cut(){
		
		if (currentTable.isCellSelected()){
			Cell resetOriginal = currentTable.getSelectedCell();
			copy();
			resetOriginal.resetCell();
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
				if(clipboardCell.getFormula().charAt(0)!='@'){
					String formula = this.formula.setRelativeFormula(clipboardCell,newFormulaRefMap,pasteToAddress);
					Cell cell = new Cell(currentTable, formula, clipboardCell.getCellFormat());
					currentTable.setCell(pasteToAddress[0], pasteToAddress[1], cell);	
				}else{
					currentTable.setCell(pasteToAddress[0], pasteToAddress[1], clipboardCell);
				}
				
				
			} catch (MyInvalidRelativeFormulaReference myException) {
				JOptionPane.showMessageDialog(null,myException.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}