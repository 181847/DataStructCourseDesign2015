package aboutVisual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import basicTool.AbstractTableNotifier;
import basicTool.TableWithNotifier;
import collegeComponent.tool.traverser.ModelTraverser;
import info.infoTool.AllTrueFilter;
import operator.SearchOperator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 一个搜索面板，
 * 主要的内部组件是：
 * 文本框：接受搜索信息；
 * 按钮：手动搜索，或者更新搜索信息；
 * JTable：显示搜索结果。
 */
public class SearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6342938097939446042L;
	private JTextField searchTextField;
	private JTable resultTable;
	private String[] column;
	private SearchOperator so;
	private ModelTraverser traverser;
	private JButton searchButton;
	
	/**
	 * 创建一个搜索面板。
	 * @param so
	 * 		搜索面板要用到的SearchOperator，
	 * 		SearchOperator内部包含数据对象、搜索方法和搜索结果，
	 * 		搜索面板通过调用SearchOperator的operat()方法来执行搜索，
	 * 		并通过SearchOperator获取搜索的结果。
	 * @param column
	 * 		用于显示在搜索面板当中的列标签，
	 * 		可以是一个String数组，例如{"名字", "性别", "年级"};
	 * @param traverser
	 * 		这个Traverser用于遍历SearchOperator的搜搜索结果，
	 * 		然后转换成对应的用于talbe显示的model，
	 * 		这个traverser由外部定义，但是要求生成的model的column必须和这个
	 * 		构造方法的colum数组相匹配。
	 * @param notifier
	 * 		这个类型的类需要重载一个方法：
	 * 		boolean fire(int rowIndex, int columnIndex, JTable table)，
	 * 		这个搜索面板中的JTable的一个询问单元格能否编辑的方法被改为
	 * 		返回执行这个fire()方法的返回值，
	 * 		程序猿可以自己重载fire()方法来对表格的不同部位的点击事件进行响应。
	 */
	public SearchPanel(SearchOperator so,
			String[] column,
			ModelTraverser traverser, 
			AbstractTableNotifier tableNotifier) {
		this.so = so;
		this.column = column;
		this.traverser = traverser;
		setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		add(controlPanel, BorderLayout.NORTH);
		
		searchTextField = new JTextField();
		controlPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearchResult();
			}
		});
		controlPanel.add(searchButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		resultTable = new TableWithNotifier(tableNotifier);
		scrollPane.setViewportView(resultTable);
		
		searchTextField.getDocument().addDocumentListener(new DocumentListener(){
 
            public void changedUpdate(DocumentEvent e) {
            	showSearchResult();
            }
 
            public void insertUpdate(DocumentEvent e) {
                //差入时响应
            	showSearchResult();
            }
 
            public void removeUpdate(DocumentEvent e) {
                //删除时响应
            	showSearchResult();
            }});
	}

	/**
	 * 获取搜索按钮组件。
	 * @return
	 * 		搜索按钮组件。
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * 根据当前文本框中的信息搜索，
	 * 将搜索结果反映到JTable上。
	 */
	public void showSearchResult() {
		String searchInfo = searchTextField.getText();
		traverser.setModel(new DefaultTableModel(column, 0));
		so.setSearchInfo(searchInfo);
		so.selectSearchLog(-1);
		so.operate();
		so.getResult()
			.getAllResult(new AllTrueFilter())
			.traverseInfo(traverser, new AllTrueFilter());
		
		resultTable.setModel(traverser.getModel());
	}
	
	/**
	 * 返回搜索面板中的JTable组件。
	 * @return
	 */
	public JTable getResultTable(){
		return resultTable;
	}

}
