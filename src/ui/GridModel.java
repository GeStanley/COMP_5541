package ui;

import javax.swing.table.AbstractTableModel;

import structure.Cell;
import structure.Table;

/**
 * This class is the object that contains the data of each cell for the JTable
 * that is the Spreadsheet.
 * 
 * @author Ankita Mishara, Geoffrey Stanley, Michael Burkat, Nicholas Reinlein,
 *         Sofiane Benaissa, Tengzhong Wen
 * 
 *         Date 31-03-2014
 */
public class GridModel extends AbstractTableModel {

	Table table;

	/**
	 * Default constructor
	 */
	public GridModel() {
		this(new Table(10, 11));
	}

	/**
	 * Constructor creates a new table
	 * 
	 * @param height
	 *            Starting number of rows
	 * @param width
	 *            Starting number of columns
	 */
	public GridModel(int height, int width) {
		this(new Table(height, width));
	}

	/**
	 * Constructor from an existing table - base constructor
	 * 
	 * @param existing
	 *            An existing table object
	 */
	public GridModel(Table existing) {
		super();
		if (existing == null) {
			table = new Table(10, 11);
		} else {
			table = existing;
			table.populate();
		}
	}

	/**
	 * Accessor for the column count
	 * 
	 * @return an int
	 */
	@Override
	public int getColumnCount() {
		return table.getWidth();
	}

	/**
	 * Accessor for the row count
	 * 
	 * @return an int
	 */
	@Override
	public int getRowCount() {
		return table.getLength();
	}

	/**
	 * Accessor for the value at x,y
	 * 
	 * @return The Cell object
	 */
	// TODO this is miss leading! it's getting a cell, not a value!
	@Override
	public String getValueAt(int row, int column) {
		return table.getCell(row, column).getValueString();
		// return table.getCell(row, column);
	}

	/**
	 * Accessor for the value at x,y
	 * 
	 * @return A string of the value in the cell
	 */
	public String getStringValueAt(int row, int column) {
		return table.getValueString(row, column);
	}

	/**
	 * Wrapper for the table.selectCell method
	 * 
	 * @param row
	 * @param column
	 */
	public Cell select(int row, int column) {
		return table.selectCell(row, column);
	}

	/**
	 * This method sets the formula of a cell at a selected location.
	 * 
	 * @param row
	 *            of a cell desired to be changed.
	 * @param column
	 *            of a cell desired to be changed.
	 * @param formula
	 *            which will be set at the desired cell.
	 */
	public String setValueAt(int row, int column, String formula) {
		if (row > table.getLength() || column > table.getWidth())
			return "Out of bounds.";

		table.selectCell(row, column);
		String c = "" + (char) ('A' + column);
		String r = "" + (row + 1);
		try {
			table.insertToCell(formula);
		} catch (Exception e) {
			table.clearSelectedCell();
			return c + r + ": " + e.getMessage();
		}

		return c + r + ": " + formula + " = " + getStringValueAt(row, column);
	}

	/**
	 * This method retrieves a table.
	 * 
	 * @return the table.
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * This method abstracts table creation.
	 */
	public void createNew() {
		table.createNew();
	}

}
