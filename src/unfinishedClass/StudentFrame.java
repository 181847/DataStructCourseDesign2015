package unfinishedClass;

import collegeComponent.College;
import collegeComponent.Student;
import collegeComponent.tool.traverser.ClubModelTraverser;
import collegeComponent.tool.traverser.MyClubModelTraverser;
import info.infoTool.AllTrueFilter;
import operator.SearchOperatorForClubs;
import operator.StudentDeleteOperator;
import operator.StudentUpdateOperator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import aboutVisual.FrameWithCollege;
import aboutVisual.SearchPanel;
import basicTool.MyLogger;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentFrame extends FrameWithCollege {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7616152633698215918L;

	private String index;
	private String name;
	private int gender;
	private String grade;
	private String mainCourse;
	
	private JTextField indexField;
	private JTextField nameField;
	JComboBox<String> genderBox;
	JComboBox<String> gradeBox;
	private JTextField mainCourseField;
	
	private JLabel indexErrorLabel;
	private JLabel nameErrorLabel;
	private JLabel genderErrorLabel;
	private JLabel gradeErrorLabel;
	private JLabel mainCourseErrorLabel;
	
	
	private JTable myClubsTable;
	private MyClubModelTraverser traverser;
	private Student student;
	private StudentUpdateOperator studentUpdateOperator;
	private StudentDeleteOperator studentDeleteOperator;
	public SearchPanel clubSearchPanel;
	private final String[] column = new String[]{"编号", "社团名字", "职位"};
	public final String[] clubColumn = { "社团编号", "社团名字", "创建日期"};
	private JButton button_1;
	private JPanel clubPane;
	private CardLayout cl_clubPane;

	public StudentFrame(College college, String studentIndex) {
		super(college);
		traverser = new MyClubModelTraverser();
		this.setSize(530, 600);
		student = college.getStudent(studentIndex);
		if (student == null){
			MyLogger.logError("StudentFrame没有获得Student对象，"
					+ "请检查社团编号是否正确：" + studentIndex);
			this.setVisible(false);
			return ;
		} else {
			studentUpdateOperator = new StudentUpdateOperator(college, student);
			clubSearchPanel = 
					new SearchPanel(
							new SearchOperatorForClubs(college), 
							clubColumn, 
							new ClubModelTraverser());
			studentDeleteOperator = new StudentDeleteOperator(college);
		}
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		button_1 = new JButton("参加新的社团");
		
		panel_1.add(button_1, BorderLayout.SOUTH);
		
		JButton button = new JButton("已参加社团");
		panel_1.add(button, BorderLayout.NORTH);
		
		clubPane = new JPanel();
		panel_1.add(clubPane, BorderLayout.CENTER);
		cl_clubPane = new CardLayout(5, 5);
		clubPane.setLayout(cl_clubPane);
		
		JScrollPane scrollPane = new JScrollPane();
		clubPane.add(scrollPane, "name_420315777493947");
		clubPane.add(clubSearchPanel);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubSearchPanel.showSearchResult();
				cl_clubPane.last(clubPane);
			}
		});
		
		myClubsTable = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return false;
			}
		};
		
		scrollPane.setViewportView(myClubsTable);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshMyClubTable();
			}
		});
		
		JLabel label = new JLabel("学号：");
		
		JLabel label_1 = new JLabel("姓名：");
		
		JLabel label_2 = new JLabel("性别：");
		
		JLabel label_3 = new JLabel("年级：");
		
		JLabel label_4 = new JLabel("专业：");
		
		indexField = new JTextField();
		indexField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		mainCourseField = new JTextField();
		mainCourseField.setColumns(10);
		
		gradeBox= new JComboBox<String>();
		gradeBox.setModel(new DefaultComboBoxModel<String>(new String[] {"大一", "大二", "大三", "大四"}));
		
		genderBox = new JComboBox<String>();
		genderBox.setModel(new DefaultComboBoxModel<String>(new String[] {"男", "女", "？"}));
		
		JButton btnNewButton = new JButton("确认更新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()){
					int n = JOptionPane.showConfirmDialog(null,
							"确认更新学生信息？",
							"确认窗口",
							JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (n != JOptionPane.YES_OPTION){
						//check();
						MyLogger.log("取消更新学生信息。");
						return;
					}
					index = indexField.getText();
					name = nameField.getText();
					gender = genderBox.getSelectedIndex();
					grade = (String) gradeBox.getSelectedItem();
					mainCourse = mainCourseField.getText();
					
					studentUpdateOperator.setStudent(student);
					studentUpdateOperator.setNewIndex(index);
					studentUpdateOperator.setNewName(name);
					studentUpdateOperator.setNewGender(gender);
					studentUpdateOperator.setNewGrade(grade);
					studentUpdateOperator.setNewMainCourse(mainCourse);
					
					studentUpdateOperator.operate();
					updateData();
				}
			}

			
		});
		
		mainCourseErrorLabel = new JLabel("");
		
		gradeErrorLabel = new JLabel("");
		
		genderErrorLabel = new JLabel("");
		
		nameErrorLabel = new JLabel("");
		
		indexErrorLabel = new JLabel("");
		
		JButton button_2 = new JButton("注销当前学生");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(null,
						"确认注销社团？",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				
				studentDeleteOperator.setTargetIndex(student.getIndex());
				
				if (1 == studentDeleteOperator.operate()){
					dispose();
				} else {
					MyLogger.logError("删除社团时发生错误！请勿保存文件。");
				}
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label)
									.addGap(18)
									.addComponent(indexField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addGap(18)
									.addComponent(nameField, 234, 234, 234))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addGap(18)
									.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3)
									.addGap(18)
									.addComponent(gradeBox, 0, 234, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_4)
									.addGap(18)
									.addComponent(mainCourseField, 234, 234, 234)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(nameErrorLabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(gradeErrorLabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
											.addComponent(mainCourseErrorLabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
										.addComponent(genderErrorLabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
								.addComponent(indexErrorLabel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(192)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(button_2)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(indexField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(indexErrorLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameErrorLabel))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_3))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(genderErrorLabel)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(gradeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(gradeErrorLabel))))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(mainCourseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainCourseErrorLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button_2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		updateData();
		refreshMyClubTable();
		
		myClubsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = myClubsTable.rowAtPoint(arg0.getPoint());
				int column = myClubsTable.columnAtPoint(arg0.getPoint());
				
				if (column == 0){;
					new ClubMemberControlFrame(
							college, 
							(String) myClubsTable.getValueAt(row, 0), 
							student.getIndex(), 
							(String) myClubsTable.getValueAt(row, 2) )
						.setVisible(true);;
				}
			}
		});
		
		JTable clubSearchResultTable = clubSearchPanel.getResultTable();
		clubSearchResultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = clubSearchResultTable.rowAtPoint(arg0.getPoint());
				int column = clubSearchResultTable.columnAtPoint(arg0.getPoint());
				
				if (column == 0){;
					new RegistMemberFrame(
							college, 
							(String) clubSearchResultTable.getValueAt(row, 0), 
							student.getIndex() )
						.setVisible(true);;
				}
			}
		});
	}
	
	private void updateData() {
		index = student.getIndex();
		name = student.getName();
		gender = student.getGender();
		grade = student.getGrade();
		mainCourse = student.getMainCourse();
		
		indexField.setText(index);
		nameField.setText(name);
		genderBox.setSelectedIndex(gender);
		if (grade.equals("大一")){
			gradeBox.setSelectedIndex(0);
		} else if (grade.equals("大二")){
			gradeBox.setSelectedIndex(1);
		} else if (grade.equals("大三")){
			gradeBox.setSelectedIndex(2);
		} else if (grade.equals("大四")){
			gradeBox.setSelectedIndex(3);
		} else {
			MyLogger.logError("学生年级信息读取错误，"
					+ "默认年级为大一");
			gradeBox.setSelectedIndex(0);
		}
		
		mainCourseField.setText(mainCourse);
		studentUpdateOperator.setStudent(student);
		clearErrorLabel();
	}

	private void refreshMyClubTable() {
		traverser.setModel(new DefaultTableModel(column, 0));
		student.getMyClubs().traverseInfo(traverser, new AllTrueFilter());
		myClubsTable.setModel(traverser.getModel());
		cl_clubPane.first(clubPane);
	}
	
	private boolean check(){
		int checkResult = 1;
		String changedIndex = indexField.getText();
		String changedName = nameField.getText();
		String changedMainCourse = mainCourseField.getText();
		
		if (changedIndex.equals(index)){
			indexErrorLabel.setText("序号不变");
		} else if (changedIndex.isEmpty()){
			indexErrorLabel.setText("错误！序号不能为空，请填写序号。");
			checkResult *= 0;
		} else if (null != college.getStudent(changedIndex)){
			indexErrorLabel.setText("错误！序号冲突，已存在相同序号的同学，请重新填写。");
			checkResult *= 0;
		} else {
			indexErrorLabel.setText("");
		}
		
		if (changedName.equals(name)){
			nameErrorLabel.setText("序号不变");
		} else if (changedIndex.isEmpty()){
			nameErrorLabel.setText("错误！序号不能为空，请填写序号。");
			checkResult *= 0;
		} else {
			nameErrorLabel.setText("");
		}
		
		if (changedMainCourse.equals(mainCourse)){
			mainCourseErrorLabel.setText("专业不变");
		} else if (changedMainCourse.isEmpty()){
			mainCourseErrorLabel.setText("错误！专业不能为空，请填写序号。");
			checkResult *= 0;
		} else {
			mainCourseErrorLabel.setText("");
		}
		
		return checkResult == 1;
	}
	
	private void clearErrorLabel() {
		indexErrorLabel.setText("");
		nameErrorLabel.setText("");
		genderErrorLabel.setText("");
		gradeErrorLabel.setText("");
		mainCourseErrorLabel.setText("");
	}
}
