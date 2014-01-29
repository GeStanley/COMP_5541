package ui;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import structure.Table;

public class SpreadsheetGrid extends AbstractTableModel{
	
	Table table;
	
	public SpreadsheetGrid(){
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
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		// use table to get that value
		return 0.0;
	}
	
}
