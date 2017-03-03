package unfinishedClass;

import javax.swing.JTable;

/**
 * 本类用于TableWithNotifier当中，
 * 每当TableWithNotifier的一个单元格被点击，
 * 就会调用本抽象类的fire()方法，
 * 并且告知被点击的单元格的行数、列数以及Table自身，
 * 程序员可以自己修改这个fire()方法来自定义表格不同位置被点击时的响应事件，
 * 返回值用来通知Table当前这个单元格是否能被编辑，
 * 在本工程当中，一般不需要通过表格修改数据，所以这里返回值一般为false。
 */
public abstract class AbstractTableNotifier {
	public abstract boolean fire(int rowIndex, int columnIndex, JTable table);
}
