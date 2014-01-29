package ui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import structure.Table;

public class GridModel extends AbstractTableModel {
	
	Table table;
	
	public GridModel(){
		table = new Table(10, 11);
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		
		// use table to get that value
		return 11;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// use table to get that value
		return 10;
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		
		// use table to get that value
		return 0.0;
	}
	
	public void setValueAt(int row, int column) {
		
	}

	
}
