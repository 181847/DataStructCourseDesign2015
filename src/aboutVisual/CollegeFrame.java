package aboutVisual;

import collegeComponent.College;
import collegeComponent.tool.traverser.ClubModelTraverser;
import collegeComponent.tool.traverser.StudentModelTraverser;
import operator.CollegeReaderAndSaverOperator;
import operator.SearchOperatorForClubs;
import operator.SearchOperatorForStudents;
import javax.swing.JPanel;
import javax.swing.JTable;

import basicTool.AbstractTableNotifier;
import basicTool.MyLogger;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 这个窗口类用来管理所有的社团和学生对象。
 */
public class CollegeFrame extends FrameWithCollege {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7105419762506000032L;


	protected CollegeReaderAndSaverOperator readerAndSaverOperator;
	

	public String dateFolderPath;
	public String backupPath;
	public SearchPanel clubSearchPanel;
	public SearchPanel studentSearchPanel;
	public final String[] clubColumn = { "社团编号", "社团名字", "创建日期"};
	public final String[] studentColumn = {"学号", "姓名", "性别", "年级", "专业"};
	
	/**
	 * @param college
	 * 		一个新的College对象。
	 * @param dateFolderPath
	 * 		用来读取所有社团、学生信息的文件夹路径。
	 * @param backupPath
	 * 		用来备份这个新窗口初始状态下的数据的文件夹路径，
	 * 		请注意这个文件夹路径下名为Club.dat/Student.dat/Club_Student.dat的文件
	 * 		将会被重写覆盖，请注意保护自己的文件。
	 */
	public CollegeFrame(College college, String dateFolderPath, String backupPath) {
		super(college);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setResizable(false);
		if (college == null){
			return;
		}
		if (dateFolderPath == null){
			MyLogger.logError("创建CollegeFrame失败，文件读取路径为空。");
			return;
		}
		if (backupPath == null){
			MyLogger.logError("创建CollegeFrame失败，文件备份路径为空。");
			return;
		}
		this.dateFolderPath = dateFolderPath;
		this.backupPath = backupPath;
		
		setTitle("学院");
		setSize(500, 600);
		readerAndSaverOperator = new CollegeReaderAndSaverOperator(college);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		ButtonGroup tglbtnGroup = new ButtonGroup();
		JToggleButton tglbtnNewToggleButton = new JToggleButton("社团");
		
		tglbtnNewToggleButton.setSelected(true);
		tglbtnGroup.add(tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("学生");
		tglbtnGroup.add(tglbtnNewToggleButton_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnNewToggleButton, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnNewToggleButton_1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnNewToggleButton)
						.addComponent(tglbtnNewToggleButton_1))
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
						new ClubModelTraverser(),
						new AbstractTableNotifier() {
							@Override
							public boolean fire(int rowIndex, int columnIndex, JTable table){
									if (columnIndex == 0){
										JFrame clubFrame = new ClubFrame(college, 
												(String) table.getValueAt(rowIndex, columnIndex));
										
										clubFrame.setLocationRelativeTo(table);
										clubFrame.setVisible(true);
									}//if
									return false;
								}//fire()
							});//AbstractTableNotifier
		
		studentSearchPanel = 
				new SearchPanel(
						new SearchOperatorForStudents(college), 
						studentColumn, 
						new StudentModelTraverser(),
						new AbstractTableNotifier(){
							@Override
							public boolean fire(int rowIndex, int columnIndex, JTable table){
								if (columnIndex == 0){
									JFrame studentFrame = new StudentFrame(college, 
											(String) table.getValueAt(rowIndex, columnIndex));
									
									studentFrame.setLocationRelativeTo(table);
									studentFrame.setVisible(true);
								}//if
								return false;
							}//fire()
						});//AbstractTableNotifier
		CardLayout cardLayout = new CardLayout(5, 5);
		studentsAndClubPanel.setLayout(cardLayout);
		studentsAndClubPanel.add(clubSearchPanel, BorderLayout.CENTER);
		studentsAndClubPanel.add(studentSearchPanel, BorderLayout.CENTER);
		
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.first(studentsAndClubPanel);
			}
		});
		
		tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.last(studentsAndClubPanel);
			}
		});
		
		
		
		JButton button = new JButton("添加学生");
		studentSearchPanel.add(button, BorderLayout.SOUTH);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame addStudentFrame = new AddStudentFrame(college);
				addStudentFrame.setLocationRelativeTo(button);
				addStudentFrame.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("添加社团");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame addClubFrame = new AddClubFrame(college);
				addClubFrame.setLocationRelativeTo(btnNewButton_2);
				addClubFrame.setVisible(true);
			}
		});
		clubSearchPanel.add(btnNewButton_2, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		
		
		readerAndSaverOperator.read(dateFolderPath);
		//第一次打开College窗口会把data在backUp中做一个备份
		readerAndSaverOperator.save(backupPath);
		
		studentSearchPanel.getSearchButton().setText("搜索学生");
		clubSearchPanel.getSearchButton().setText("搜索社团");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("读取");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int n = JOptionPane.showConfirmDialog(tglbtnNewToggleButton,
						"确认读取本地文件中的信息？当前未保存的内容可能丢失。\n你可以在\"" + backupPath +  "\"文件夹中找到 打开本次“学院”窗口时 所使用数据的 备份。",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				readerAndSaverOperator.read(dateFolderPath);
				studentSearchPanel.showSearchResult();
				clubSearchPanel.showSearchResult();
			}
		});
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("保存");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(tglbtnNewToggleButton,
						"确认保存目前的信息？\n你可以在\"" + backupPath +  "\"文件夹中找到 打开本次“学院”窗口时 所使用数据的 备份。",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				
				readerAndSaverOperator.save(dateFolderPath);
			}
		});
		mnNewMenu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("退出");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(tglbtnNewToggleButton,
						"确定退出？退出当前学院窗口后将无法再保存修改。",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				
				dispose();
				System.exit(0);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(tglbtnNewToggleButton_1,
						"确定退出？退出当前学院窗口后将无法再保存修改。",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				
				dispose();
				System.exit(0);
			}
		});
		
		
		mnNewMenu.add(menuItem_2);
		
		studentSearchPanel.showSearchResult();
		clubSearchPanel.showSearchResult();
		
		
	}

}
