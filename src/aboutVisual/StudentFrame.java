package aboutVisual;

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
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import basicTool.AbstractTableNotifier;
import basicTool.BasicStringChecker;
import basicTool.MyLogger;
import basicTool.TableWithNotifier;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * 修改学生基本信息、修改在社团中的职位、参加新社团的功能。
 */
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
		setResizable(false);
		traverser = new MyClubModelTraverser();
		this.setSize(582, 789);
		student = college.getStudent(studentIndex);
		if (student == null){
			MyLogger.logError("StudentFrame没有获得Student对象，"
					+ "请检查社团编号是否正确：" + studentIndex);
			this.setVisible(false);
			return ;
		} else {
			updateTitle();
			studentUpdateOperator = new StudentUpdateOperator(college, student);
			clubSearchPanel = 
					new SearchPanel(
							new SearchOperatorForClubs(college), 
							clubColumn, 
							new ClubModelTraverser(),
							new AbstractTableNotifier() {
								@Override
								public boolean fire(int rowIndex, int columnIndex, JTable table){
									if (columnIndex == 0){
										JFrame registerMemberFrame = new RegistMemberFrame(
												college, 
												(String) table.getValueAt(rowIndex, 0), 
												student.getIndex() );
										registerMemberFrame.setLocationRelativeTo(table);
										registerMemberFrame.setVisible(true);;
									}
									return false;
								}
							});
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
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
		
		myClubsTable = new TableWithNotifier(new AbstractTableNotifier(){
				@Override
				public boolean fire(int rowIndex, int columnIndex, JTable table){
					if (columnIndex == 0){
						JFrame clubMemberControlFrame = new ClubMemberControlFrame(
													college, 
													(String) table.getValueAt(rowIndex, 0), 
													student.getIndex(), 
													(String) table.getValueAt(rowIndex, 2) );
						clubMemberControlFrame.setLocationRelativeTo(table);
						clubMemberControlFrame.setVisible(true);;
					}//if
					return false;
				}//fire()
			});//myClubsTable
		
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
					int n = JOptionPane.showConfirmDialog(btnNewButton,
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
				int n = JOptionPane.showConfirmDialog(button_2,
						"确认注销学生？",
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
									.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
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
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(indexErrorLabel, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(nameErrorLabel, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(genderErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(gradeErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(mainCourseErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(192)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
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
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_3))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(genderErrorLabel)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
	}
	
	private void updateTitle() {
		this.setTitle("\"" + student.getName() + "\"");
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
		updateTitle();
	}

	private void refreshMyClubTable() {
		traverser.setModel(new DefaultTableModel(column, 0));
		student.getMyClubs().traverseInfo(traverser, new AllTrueFilter());
		myClubsTable.setModel(traverser.getModel());
		cl_clubPane.first(clubPane);
	}
	
	private boolean check(){
		boolean checkResult = true;
		String changedIndex = indexField.getText();
		String changedName = nameField.getText();
		String changedMainCourse = mainCourseField.getText();
		
		if (changedIndex.equals(index)){
			indexErrorLabel.setText("序号不变");
		} else if ( ! BasicStringChecker.check(changedIndex)){
			indexErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else if (changedIndex.isEmpty()){
			indexErrorLabel.setText("错误！序号不能为空，请填写序号。");
			checkResult = false;
		} else if (null != college.getStudent(changedIndex)){
			indexErrorLabel.setText("错误！序号冲突，已存在相同序号的同学，请重新填写。");
			checkResult = false;
		} else {
			indexErrorLabel.setText("");
		}//if
		
		if (changedName.equals(name)){
			nameErrorLabel.setText("名字不变");
		} else if ( ! BasicStringChecker.check(changedName)){
			nameErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else if (changedName.isEmpty()){
			nameErrorLabel.setText("错误！名字不能为空，请填写名字。");
			checkResult = false;
		} else {
			nameErrorLabel.setText("");
		}//if
		
		if (changedMainCourse.equals(mainCourse)){
			mainCourseErrorLabel.setText("专业不变");
		} else if ( ! BasicStringChecker.check(changedMainCourse)){
			mainCourseErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else if (changedMainCourse.isEmpty()){
			mainCourseErrorLabel.setText("错误！专业不能为空，请填写序号。");
			checkResult = false;
		} else {
			mainCourseErrorLabel.setText("");
		}//if
		
		return checkResult;
	}
	
	private void clearErrorLabel() {
	}
}
