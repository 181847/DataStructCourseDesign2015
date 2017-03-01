package unfinishedClass;

import collegeComponent.College;
import collegeComponent.tool.traverser.ClubModelTraverser;
import collegeComponent.tool.traverser.StudentModelTraverser;
import operator.SearchOperatorForClubs;
import operator.SearchOperatorForStudents;
import javax.swing.JPanel;
import javax.swing.JTable;

import aboutVisual.FrameWithCollege;
import aboutVisual.SearchPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSwitch, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(btnSwitch)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel studentsAndClubPanel = new JPanel();
		studentsAndClubPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
