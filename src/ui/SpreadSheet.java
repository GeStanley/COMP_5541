package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import structure.Cell;


/**
 * This class is the face of the spreadsheet, it displays the grid of cells
 * 
 * @author Mike
 *
 */
public class SpreadSheet extends JTable implements ActionListener {

	GridModel gm;
	Cell selectedCell;
	int row = -1;
	int	column = -1;
	
	/**
	 * Constructor for the class, it sets up the table with the GridModel
	 * Sets the selection mode, and adds listeners for column and row selection change
	 */
	public SpreadSheet() {
		gm = new GridModel();
		this.setModel(gm);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setCellSelectionEnabled(true);

		getColumnModel().addColumnModelListener(columnModelListener());
		getSelectionModel().addListSelectionListener(listSelectionListener());

	}

	/**
	 * This method is called when the row of the selected cell changes
	 * It triggers an action that will eventually notify the parent class 
	 * of the change to the row and give its new value.
	 * 
	 * @return a ListSelectionListener
	 */
	private ListSelectionListener listSelectionListener() {
		ListSelectionListener lsl = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				row = e.getLastIndex();
				System.out.println("row " + row);
				actionPerformed(new ActionEvent(this, editingColumn, "select"));
			}
		};
		return lsl;
	}

	/**
	 * This method is called when the column of the selected cell changes
	 * It triggers an action that will eventually notify the parent class
	 * of the change to the row and give its new value.
	 * 
	 * @return a TableColumnModelListener
	 */
	private TableColumnModelListener columnModelListener() {
		// TODO Auto-generated method stub
		TableColumnModelListener tcml = new TableColumnModelListener() {

			@Override
			public void columnAdded(TableColumnModelEvent e) {
			}

			@Override
			public void columnMarginChanged(ChangeEvent e) {
			}

			@Override
			public void columnMoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
				column = e.getLastIndex();
				System.out.println("column " + column);
				actionPerformed(new ActionEvent(this, editingColumn, "select"));
			}
		};
		return tcml;
	}

	/**
	 * This method fires a propertyChange, in order to inform the parent class of the
	 * change in selected cell, it sends it's formula as a parameter, to be inserted to the
	 * input line.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( row >= 0 & column >= 0){
			Cell c = (Cell) gm.getValueAt(row, column);
			System.out.println(c.formulaString());
			this.firePropertyChange("select", null, c.formulaString());
		}
	}
}
