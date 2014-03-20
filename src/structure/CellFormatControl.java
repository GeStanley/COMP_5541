package structure;

import structure.Cell.Format;

public class CellFormatControl {
	
	private static Table currentTable;
	
	public CellFormatControl(Table table){
		currentTable = table;
	}	
	
	public void setCellFormat(Format cellFormat){
		currentTable.getSelectedCell().setCellFormat(cellFormat);		
	}

}
