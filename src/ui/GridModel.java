package ui;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import structure.Cell;
import structure.Table;

public class GridModel extends AbstractTableModel {
	
	Table table;
	
	/**
	 * Default constructor
	 */
	public GridModel() {
		this(new Table(10,11));
	}
	
	/**
	 * Constructor creates a new table
	 * 
	 * @param height Starting number of rows
	 * @param width Starting number of columns
	 */
	public GridModel(int height, int width) {
		this(new Table(height, width));
	}
	
	/**
	 * Constructor from an existing table - base constructor
	 * 
	 * @param existing An existing table object
	 */
	public GridModel(Table existing) {
		super();
		table = existing;
		table.populate();
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
	@Override
	public Object getValueAt(int row, int column) {
		return table.getCell(row, column);
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
	
	public void setValueAt(int row, int column) {
		
	}

	
}
