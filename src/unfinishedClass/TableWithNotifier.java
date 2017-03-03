package unfinishedClass;

import javax.swing.JTable;

/**
 * 本类继承于JTable，
 * 内部增加一个自创建的成员AbstractTableNotifier，
 * 这个成员在初始化的时候被指定。
 * 重载一个方法，
 * boolean isCellEditable(int rowIndex, int columnIndex);
 * 这个方法会在表格每次被点击的时候用来判断当前单元格能否被编辑，
 * 重载这个方法之后，
 * 这个方法返回调用AbstractTableNotifier.fire(int rowIndex, int columnIndex, JTable table)
 * 的返回值，
 * 如此一来程序员可以通过重载AbstractTableNotifier的方法，
 * 来定制表格不同位置的单元格被点击时的响应事件。
 */
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
