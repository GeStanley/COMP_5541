package ui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SpreadSheet extends JTable implements ListSelectionListener{

	GridModel gm;
	
	public SpreadSheet() {
		gm = new GridModel();
		this.setModel(gm);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("change");
	}
	
	
	
}
