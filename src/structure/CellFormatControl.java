package structure;

import structure.Cell.Format;

/**
 * This class manages the format control of a cell.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * 			Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */
public class CellFormatControl {
	
	private static Table currentTable;
	
	public CellFormatControl(Table table){
		currentTable = table;
	}	
	
	public void setCellFormat(Format cellFormat){
		currentTable.getSelectedCell().setCellFormat(cellFormat);		
	}

}
