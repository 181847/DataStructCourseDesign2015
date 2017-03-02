package unfinishedClass;

import javax.swing.JTable;

public abstract class AbstractTableNotifier {
	public abstract boolean fire(int rowIndex, int columnIndex, JTable table);
}
