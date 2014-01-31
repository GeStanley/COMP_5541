package ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class RowTable extends JTable {

	private JTable spreadsheet;

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

	@Override
	public int getRowCount() {
		return spreadsheet.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return "" + (row + 1);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

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
