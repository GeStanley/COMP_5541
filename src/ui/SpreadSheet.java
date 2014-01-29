package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

public class SpreadSheet extends JTable implements ActionListener {

	GridModel gm;

	public SpreadSheet() {
		gm = new GridModel();
		this.setModel(gm);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setCellSelectionEnabled(true);

		getColumnModel().addColumnModelListener(columnModelListener());
		getSelectionModel().addListSelectionListener(listSelectionListener());

	}

	private ListSelectionListener listSelectionListener() {
		ListSelectionListener lsl = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("row " + e.getLastIndex());
				actionPerformed(new ActionEvent(this, editingColumn, "select"));
			}
		};
		return lsl;
	}

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
				System.out.println("column " + e.getLastIndex());
				actionPerformed(new ActionEvent(this, editingColumn, "select"));
			}
		};
		return tcml;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.firePropertyChange("select", 1, 0);
	}

}
