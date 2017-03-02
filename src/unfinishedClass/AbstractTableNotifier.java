package unfinishedClass;

import javax.swing.JTable;

public abstract class AbstractTableNotifier {
	/**
	 * 行数。
	 */
	public int rowIndex;
	
	/**
	 * 列数
	 */
	public int columnIndex;
	
	public JTable table;
	
	public AbstractTableNotifier(){
		
	}

	public abstract void fire();
	
	public abstract boolean isCellEditable();

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable talbe) {
		this.table = talbe;
	}



}
