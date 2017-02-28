package unfinishedClass;

import javax.swing.JFrame;

import collegeComponent.College;
import operator.SearchOperatorForClubs;
import operator.SearchOperatorForStudents;
import javax.swing.JPanel;
import javax.swing.JTable;

import basicTool.MyLogger;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class CollegeFrame extends FrameWithCollege {
	public SearchPanel clubSearchPanel;
	public SearchPanel studentSearchPanel;
	public final String[] clubColumn = { "社团编号", "社团名字", "创建日期"};
	public final String[] studentColumn = {"学号", "姓名", "性别", "年级", "专业"};
	
	public CollegeFrame(College college) {
		super(college);
		setSize(500, 600);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnSwitch = new JButton("Switch");
		
		panel.add(btnSwitch);
		
		JPanel studentsAndClubPanel = new JPanel();
		getContentPane().add(studentsAndClubPanel, BorderLayout.CENTER);
		studentsAndClubPanel.setLayout(new CardLayout(0, 0));
		clubSearchPanel = 
				new SearchPanel(
						new SearchOperatorForClubs(college), 
						clubColumn, 
						new ClubModelTraverser());
		studentSearchPanel = 
				new SearchPanel(
						new SearchOperatorForStudents(college), 
						studentColumn, 
						new StudentModelTraverser());
		CardLayout cardLayout = new CardLayout(5, 5);
		studentsAndClubPanel.setLayout(cardLayout);
		studentsAndClubPanel.add(clubSearchPanel, BorderLayout.CENTER);
		studentsAndClubPanel.add(studentSearchPanel, BorderLayout.CENTER);
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(studentsAndClubPanel);
			}
		});
		
		JTable studentTable = studentSearchPanel.getResultTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = studentTable.rowAtPoint(arg0.getPoint());
				int column = studentTable.columnAtPoint(arg0.getPoint());
				
				if (column == 0){;
					new StudentFrame(college, 
							(String) studentTable.getValueAt(row, column))
					.setVisible(true);
				}
			}
		});
		
		JTable clubTable = clubSearchPanel.getResultTable();
		clubTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = studentTable.rowAtPoint(arg0.getPoint());
				int column = studentTable.columnAtPoint(arg0.getPoint());
				
				if (column == 0){;
					new ClubFrame(college, 
							(String) clubTable.getValueAt(row, column))
					.setVisible(true);
				}
			}
		});
		
		studentSearchPanel.showSearchResult();
		clubSearchPanel.showSearchResult();
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8496767234452948614L;

}
