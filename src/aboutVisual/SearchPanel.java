package aboutVisual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.tool.traverser.ModelTraverser;
import info.infoTool.AllTrueFilter;
import operator.SearchOperator;
import unfinishedClass.AbstractTableNotifier;
import unfinishedClass.TableWithNotifier;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	 * 		这个notifier会在table询问表格数据的某个具体的单位格能否编辑的时候发动，
	 * 		notifier会记下发动编辑功能的单位格的行数和列数，
	 * 		然后发动notifier内部的notify()方法，
	 * 		执行特定的代码。
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
		
		
		
		
		//showSearchResult();
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void showSearchResult() {
		String searchInfo = searchTextField.getText();
		MyLogger.log("SearchInfo: " + (searchInfo == null));
		traverser.setModel(new DefaultTableModel(column, 0));
		so.setSearchInfo(searchInfo);
		so.selectSearchLog(-1);
		so.operate();
		so.getResult()
			.getAllResult(new AllTrueFilter())
			.traverseInfo(traverser, new AllTrueFilter());
		
		resultTable.setModel(traverser.getModel());
	}
	
	public JTable getResultTable(){
		return resultTable;
	}

}
