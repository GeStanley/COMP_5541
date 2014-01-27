package structure;

/**
 * This class is the object that manages the behavior of the individual cells within the table class.
 * 
 * @author g_stanle
 *
 */
public class Cell {
	
	private double value;
	private Table table;
	private Formula formula;
	
	/**
	 * Constructor method
	 * 
	 * @param table The table this cell is in
	 */
	public Cell(Table table) {
		this(table, "0.0");
	}
	
	/**
	 * Constructor method with an existing value
	 * 
	 * @param table The table this cell is in
	 * @param val The value
	 */
	public Cell(Table table, double val) {
		this(table, ""+val);
	}

	/**
	 * Constructor method with an existing formula
	 * 
	 * @param table The table this cell is in
	 * @param formula The formula
	 */
	public Cell(Table table, String formula) {
		this.table = table;
		this.formula = new Formula(formula, table);
		try {
			value = this.formula.evaluate();
		}
		catch (Exception e) {
			value = 0.0;
		}
	}
	
	/**
	 * This method sets the formula of the cells.
	 * 
	 * @param formula A String representation of the formula to be calculated. It can contain references to
	 * other cells in the form 'char' digit. Eg: A1 or B10
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public void setFormula(String formula) throws NumberFormatException, Exception {
		this.formula = new Formula(formula, table);
		value = this.formula.evaluate();
	}
	
	/**
	 * Returns the string representation of the formula contained within the cell.
	 * 
	 * @return
	 */
	public String getFormula(){
		return this.formula.formula();
	}
	
	/**
	 * Returns the value calculated from the formula in the cell.
	 * 
	 * @return
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Force recalculation of a value while getting it
	 * 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public double getValue(boolean recalculate) throws NumberFormatException, Exception {
		if (recalculate)
			value = formula.evaluate();
		return value;
	}
	
	/**
	 * Returns the value as a string, useful for replacing in formulas
	 */
	public String getValueString() {
		return value + "";
	}
	
	/**
	 * Override tostring method
	 */
	public String toString() {
		return formula.formula() + "=" + value;
	}
	
}
