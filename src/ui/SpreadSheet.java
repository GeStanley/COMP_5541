package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import structure.Cell;
import structure.Table;


/**
 * This class is the face of the spreadsheet, it displays the grid of cells
 * 
 * @author Mike
 *
 */
public class SpreadSheet extends JTable implements ActionListener {

	GridModel gm;
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
				if (! e.getValueIsAdjusting() ) {
					row = getSelectedRow();
					System.out.println("row " + row);
					
					actionPerformed(new ActionEvent(this, editingColumn, "select"));
				}
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
				if (! e.getValueIsAdjusting() ) {
					
					column = getSelectedColumn();
			        
					System.out.println("column " + column);
					
					actionPerformed(new ActionEvent(this, editingColumn, "select"));
				}
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
	
	/**
	 * Is a method which verifies if a cell is selected at that moment.
	 * 
	 * @return true if a cell is selected, and false if no cell is selected.
	 */
	public boolean isSelected() {
		if ( row >= 0 & column >= 0 )
			return true;
		else
			return false;
	}
	
	
	/**
	 * This method sets the formula of a cell.
	 * 
	 * @param formula the formula that the cell will be set to.
	 */
	public void setFormulaOfSelectedCell(String formula) {
		gm.setValueAt(row, column, formula);
	}
	
	/**
	 * Will return a table for the saveing and loading features.
	 * 
	 * @return a table.
	 */
	public Table getTable() {
		return gm.getTable();
	}
	
	/**
	 * abstracts the create new function.
	 */
	public void createNew() {
		gm.createNew();
	}
	
}
