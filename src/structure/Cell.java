package structure;

/**
 * This class is the object that manages the behavior of the individual cells within the table class.
 * 
 * @author g_stanle
 *
 */
public class Cell {
	
	
	private String formula;
	private double value;
	private Table table;
	private Formula parser;
	
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
		this.parser = table.getParser();
		setFormula(formula);
	}
	
	/**
	 * This method sets the formula of the cells.
	 * 
	 * @param formula A String representation of the formula to be calculated. It can contain references to
	 * other cells in the form 'char' digit. Eg: A1 or B10
	 */
	public void setFormula(String formula) {
		this.formula = formula;
		evalValue();
	}
	
	/**
	 * Returns the string representation of the formula contained within the cell.
	 * 
	 * @return
	 */
	public String getFormula(){
		return this.formula;
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
	 * Returns the value as a string, useful for replacing in formulas
	 */
	public String getValueString() {
		return value + "";
	}
	
	/**
	 * This is a private method because the value is determined once a formula is entered, values retrieved from referenced cells
	 * and the formula parsed by the grammar class.
	 */
	private void evalValue() {
		try {
			value = parser.evaluate(formula);
		}
		catch (Exception e) {
			e.printStackTrace();
			value = 0;
		}
	}

}
