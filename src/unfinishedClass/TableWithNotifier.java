package unfinishedClass;

import javax.swing.JTable;

public class TableWithNotifier extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6528216721053831456L;
	/**
	 * 当table的某个单元格被询问是否需要被编辑的时候，
	 * 会将当前单元格的行数列数以及table自身设置到tableNotifier内部，
	 * 然后又tableNotifier自身的fire()方法来对指定单元格的相应事件定义功能，
	 * 最后返回tableNotifier的isCellEditable()方法决定当前这个单元格是否能够被发动。
	 */
	public AbstractTableNotifier tableNotifier;
	
	public TableWithNotifier(AbstractTableNotifier tableNotifier){
		this.tableNotifier  = tableNotifier;
	}
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return tableNotifier.fire(rowIndex, columnIndex, this);
	}
}
