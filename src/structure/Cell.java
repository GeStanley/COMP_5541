package structure;

import java.util.ArrayList;

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
	private ArrayList<String> refs;
	
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
		this(table, "" + val);
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
			this.formula = new Formula("0.0", table);
			this.value = 0.0;
		}
	}
	
	public void resetCell() {
		this.formula = new Formula("0.0", table);
		this.value = 0.0;
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
		if (formula == null) // Treat an empty formula as zero
			formula = "0.0"; 
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
	 * Return the formula object contained in cell.
	 * 
	 * @return
	 */
	public Formula getFormulaObject(){
		return formula;
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
	 * Override toString method to return the value
	 */
	public String toString() {
		return "" + value;
	}
	
	/**
	 * Getter for a string representation of the formula.
	 */
	public String formulaString() {
		return formula.formula();
	}

	/**
	 * Cells are equal if their formulas and values are equal
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)  // The same object
			return true;
		if (obj == null) 
			return false;
		if (obj.getClass() != getClass())  // Not the same class
			return false;
		Cell other = (Cell) obj; // Cast obj to cell
		
		//System.out.println(" Compare " + this + " to " + other);
		if (other.getFormula().equals(getFormula()) 
				&& other.getValue() == getValue())
			return true;
		else
			return false;
	}
	
	/**
	 * Retrieves the list of references contained in a formula
	 * 
	 * @return list of references in a formula
	 */
	public ArrayList<String> getRefs(){
		refs = formula.getReferences();
		return refs;
	}
	

	
}
