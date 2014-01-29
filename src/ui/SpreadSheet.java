package ui;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SpreadSheet extends JTable implements ListSelectionListener{

	GridModel model;
	
	/**
	 * Default Constructor
	 */
	public SpreadSheet() {
		this(new GridModel());
	}
	
	/**
	 * Constructor from an existing model
	 * 
	 * @param gm
	 */
	public SpreadSheet(GridModel gm) {
		super(gm);
		model = gm;
		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
	}
	
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("change (" + getSelectedColumn() + ", " + getSelectedRow() + ")");
	}
	
	
	
}
