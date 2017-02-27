package unfinishedClass;

import javax.swing.JFrame;

import collegeComponent.College;
import info.infoTool.AllTrueFilter;
import operator.SearchOperatorForClubs;
import operator.SearchOperatorForStudents;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.sun.xml.internal.ws.api.Component;

import basicTool.MyLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CollegeFrameDemo extends JFrame {
	protected College college;
	protected SearchOperatorForClubs searchOperatorFC;
	protected SearchOperatorForStudents searchOperatorFS;
	public final String[] clubColumn = { "社团编号", "社团名字", "创建日期"};
	public final String[] studentColumn = {"学号", "姓名", "性别", "年级", "专业"};
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8496767234452948614L;
	private JTextField studentSearchTextField;
	private JTable studentTable;
	private JTextField clubSearchTextField;
	private JTable clubTable;

	public CollegeFrameDemo(College college){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.college = college;
		searchOperatorFC = new SearchOperatorForClubs(college);
		searchOperatorFS = new SearchOperatorForStudents(college);
		
		JPanel panel = new JPanel();
		JPanel studentsAndClubPanel = new JPanel();
		CardLayout cardLayout = new CardLayout(5, 5);
		JButton btnSwithc = new JButton("Switch");
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel studentPanel = new JPanel();
		JScrollPane studentScrollPane = new JScrollPane();
		studentTable = new JTable();
		JPanel clubPanel = new JPanel();
		JPanel clubControlPanel = new JPanel();
		clubSearchTextField = new JTextField();
		JButton searchClubButton = new JButton("SearchClub");
		JScrollPane scrollPane = new JScrollPane();
		clubTable = new JTable();
		clubTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = clubTable.rowAtPoint(e.getPoint());
				Object clickedObject = clubTable.getValueAt(row, 0);
				MyLogger.log("Club Table Click On Row: " + row);
				MyLogger.log("Club Table Click On Index: " + clickedObject);
				
			}
		});
		clubTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.
		
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(studentsAndClubPanel, BorderLayout.CENTER);
		
		studentsAndClubPanel.setLayout(cardLayout);
		
		btnSwithc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.next(studentsAndClubPanel);
			}
		});
		
		panel.add(btnSwithc);
		studentsAndClubPanel.add(studentPanel, "name_320390599985461");
		studentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel studentControlPanel = new JPanel();
		studentPanel.add(studentControlPanel, BorderLayout.NORTH);
		studentSearchTextField = new JTextField();
		
		studentControlPanel.add(studentSearchTextField);
		studentSearchTextField.setColumns(10);
		
		JButton btnSearchstudent = new JButton("SearchStudent");
		
		studentControlPanel.add(btnSearchstudent);
		
		
		studentPanel.add(studentScrollPane, BorderLayout.CENTER);
		
		studentScrollPane.setViewportView(studentTable);
		
		studentsAndClubPanel.add(clubPanel, "name_320604695805784");
		clubPanel.setLayout(new BorderLayout(0, 0));
		
		clubPanel.add(clubControlPanel, BorderLayout.NORTH);
		
		clubControlPanel.add(clubSearchTextField);
		clubSearchTextField.setColumns(10);
		
		clubControlPanel.add(searchClubButton);
		
		clubPanel.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(clubTable);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
				
			}
		});
		getContentPane().add(btnClose, BorderLayout.SOUTH);
		
		searchClubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showClubSearchResult();
			}
		});
		
		btnSearchstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStudentSearchResult();
			}
		});
		
		clubSearchTextField.getDocument().addDocumentListener(new DocumentListener(){
 
            public void changedUpdate(DocumentEvent e) {
                showClubSearchResult();
            }
 
            public void insertUpdate(DocumentEvent e) {
                //差入时响应
            	showClubSearchResult();
            }
 
            public void removeUpdate(DocumentEvent e) {
                //删除时响应
            	showClubSearchResult();
            }});
		
		studentSearchTextField.getDocument().addDocumentListener(new DocumentListener(){
			 
            public void changedUpdate(DocumentEvent e) {
            	showStudentSearchResult();
            }
 
            public void insertUpdate(DocumentEvent e) {
                //差入时响应
            	showStudentSearchResult();
            }
 
            public void removeUpdate(DocumentEvent e) {
                //删除时响应
            	showStudentSearchResult();
            }});
		
		showClubSearchResult();
		showStudentSearchResult();
		
		
	}
	
	private void showClubSearchResult() {
		String searchInfo = clubSearchTextField.getText();
		MyLogger.log("SearchInfo: " + (searchInfo == null));
		ClubModelTraverser traverser = new ClubModelTraverser(clubColumn);
		searchOperatorFC.setSearchInfo(searchInfo);
		searchOperatorFC.selectSearchLog(-1);
		searchOperatorFC.operate();
		searchOperatorFC
			.getResult()
			.getAllResult(new AllTrueFilter())
			.traverseInfo(traverser, new AllTrueFilter());
		
		clubTable.setModel(traverser.getModel());
	}
	
	private void showStudentSearchResult(){
		String searchInfo = studentSearchTextField.getText();
		MyLogger.log("SearchInfo: " + (searchInfo == null));
		StudentModelTraverser traverser = new StudentModelTraverser(studentColumn);
		
		searchOperatorFS.setSearchInfo(searchInfo);
		searchOperatorFS.selectSearchLog(-1);
		searchOperatorFS.operate();
		searchOperatorFS
			.getResult()
			.getAllResult(new AllTrueFilter())
			.traverseInfo(traverser, new AllTrueFilter());
		
		studentTable.setModel(traverser.getModel());
	}
}
