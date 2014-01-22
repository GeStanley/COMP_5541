package structure;

import structure.Table.NullCellPointer;

/**
 * This class is the object that manages the behavior of the individual cells within the table class.
 * 
 * @author g_stanle
 *
 */
public class Cell {
	
	
	private String formulaWithCellReference;
	private String formulaWithoutCellReference;
	private String value;
	private Table table;
	
	/**
	 * Constructor method
	 * 
	 * @param table
	 * @throws NullCellPointer 
	 * @throws NumberFormatException 
	 */
	public Cell(Table table) throws NumberFormatException, NullCellPointer {
		this.table = table;
		setFormula("0.0");
	}
	
	/**
	 * This method sets the formula of the cells.
	 * 
	 * @param formula A String representation of the formula to be calculated. It can contain references to
	 * other cells in the form 'char' digit. Eg: A1 or B10
	 * @throws NullCellPointer 
	 * @throws NumberFormatException 
	 */
	public void setFormula(String formula) throws NumberFormatException, NullCellPointer{
		formulaWithCellReference = formula;
		getReferenceValues();
		setValue();
	}
	
	/**
	 * Returns the string representation of the formula contained within the cell.
	 * 
	 * @return
	 */
	public String getFormula(){
		return this.formulaWithCellReference;
	}
	
	/**
	 * Returns the value calculated from the formula in the cell.
	 * 
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Retrieve values from cells referenced in the formula String.
	 * 
	 * @throws NullCellPointer 
	 * @throws NumberFormatException 
	 * 
	 */
	private void getReferenceValues() throws NumberFormatException, NullCellPointer{
		
		formulaWithoutCellReference = formulaWithCellReference;
		
		for(int i=0;i<formulaWithCellReference.length();i++){
			char current = formulaWithCellReference.charAt(i);
			
			int offset=0;
			
			if(Character.getNumericValue(current)>9&&Character.getNumericValue(current)<36){
				while(current!='+'&&current!='-'&&current!='*'&&current!='/')
					offset++;
					
				Cell referencedCell = table.getCell(Integer.parseInt(formulaWithCellReference.substring(i+1, i+offset)), current);
				
				formulaWithoutCellReference = formulaWithoutCellReference.replace(referencedCell.getValue(),formulaWithCellReference.substring(i, i+offset));
			}
		}
	}
	
	/**
	 * This is a private method because the value is determined once a formula is entered, values retrieved from referenced cells
	 * and the formula parsed by the grammar class.
	 */
	private void setValue() {
		Grammar.Formula form = new Grammar.Formula();
		value = form.formula(formulaWithoutCellReference);
	}
	
	public void setValue(int v){
		value = String.valueOf(v);
	}

}
