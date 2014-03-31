package ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * This is a table that displays the row numbers.
 * 
 * This class is the object that manages the behavior of the individual cells within the table class.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * 			Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */
public class RowTable extends JTable {

	private JTable spreadsheet;

	/**
	 * Constructor for the row table.
	 * 
	 * @param data JTable, it's required to create the proper number of row labels.
	 */
	public RowTable(JTable data) {
		spreadsheet = data;
		setFocusable(false);
		setAutoCreateColumnsFromModel(false);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(false);

		TableColumn column = new TableColumn();
		column.setHeaderValue(" ");
		addColumn(column);
		column.setCellRenderer(new RowRenderer());

		getColumnModel().getColumn(0).setPreferredWidth(50);
		setPreferredScrollableViewportSize(getPreferredSize());

	}

	/**
	 * returns the row count.
	 */
	@Override
	public int getRowCount() {
		return spreadsheet.getRowCount();
	}

	/**
	 * returns the value of a row.
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return "" + (row + 1);
	}

	/**
	 * determines if cells are editable. It's set to false for all cells.
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * A costum renderer is used here to color the cells in gray, instead of the default white.
	 * 
	 * @author mike
	 *
	 */
	private static class RowRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			if (value != null) {
				setText(value.toString());
			} else {
				setText("");
			}
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));

			return this;
		}

	}
}
