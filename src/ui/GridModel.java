package ui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import structure.Table;

public class GridModel extends AbstractTableModel {
	
	Table table;
	
	public GridModel(){
		table = new Table(10, 11);
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
	
	public void setValueAt(int row, int column) {
		
	}

	
}
