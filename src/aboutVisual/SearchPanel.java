package aboutVisual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.tool.traverser.ModelTraverser;
import info.infoTool.AllTrueFilter;
import operator.SearchOperator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	public SearchPanel(SearchOperator so, String[] column, ModelTraverser traverser) {
		this.so = so;
		this.column = column;
		this.traverser = traverser;
		setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		add(controlPanel, BorderLayout.NORTH);
		
		searchTextField = new JTextField();
		controlPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearchResult();
			}
		});
		controlPanel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		resultTable = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return false;
			}
		};
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
