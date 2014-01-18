package structure;

import structure.Table.NullCellPointer;

/**
 * @author g_stanle
 *
 */
public class Cell {
	
	
	private String formulaWithCellReference;
	private String formulaWithoutCellReference;
	private String value;
	private Table table;
	
	/**
	 * @param table
	 * @throws NullCellPointer 
	 * @throws NumberFormatException 
	 */
	public Cell(Table table) throws NumberFormatException, NullCellPointer {
		this.table = table;
		setFormula("0.0");
	}
	
	/**
	 * @param formula
	 * @throws NullCellPointer 
	 * @throws NumberFormatException 
	 */
	public void setFormula(String formula) throws NumberFormatException, NullCellPointer{
		formulaWithCellReference = formula;
		getReferenceValues();
		setValue();
	}
	
	/**
	 * @return
	 */
	public String getFormula(){
		return this.formulaWithCellReference;
	}
	
	/**
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
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
	 * 
	 */
	private void setValue() {
		Grammar.Formula form = new Grammar.Formula();
		value = form.formula(formulaWithoutCellReference);
	}

}
