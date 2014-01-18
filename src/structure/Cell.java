package structure;

public class Cell {
	
	
	private String formulaWithCellReference;
	private String formulaWithoutCellReference;
	private String value;
	
	public Cell() {
		setFormula("0.0");
		getReferenceValues();
		setValue();
	}
	
	public void setFormula(String formula){
		formulaWithCellReference = formula;
	}
	
	public String getFormula(){
		return this.formulaWithCellReference;
	}
	
	public String getValue() {
		return this.value;
	}
	
	private void getReferenceValues(){
		//TODO do the parsing of the string and then get value from other cell.
	}
	
	private void setValue() {
		Grammar.Formula form = new Grammar.Formula();
		value = form.formula(formulaWithoutCellReference);
	}

}
