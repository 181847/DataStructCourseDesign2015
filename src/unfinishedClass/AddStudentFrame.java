package unfinishedClass;

import aboutVisual.FrameWithCollege;
import collegeComponent.College;
import collegeComponent.Student;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 本类是用来向College中插入新学生的窗口类。
 */
public class AddStudentFrame extends FrameWithCollege {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2205342794434320229L;
	private JTextField indexField;
	private JTextField nameField;
	private JTextField mainCourseField;
	private JComboBox<String> genderBox;
	private JComboBox<String> gradeBox;
	private JLabel indexErrorLabel;
	private JLabel nameErrorLabel;
	private JLabel mainCourseErrorLabel;

	public AddStudentFrame(College college) {
		super(college);
		
		if (college == null){
			return;
		}
		
		setSize(560, 410);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		JButton btnNewButton = new JButton("添加学生");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()){
					int n = JOptionPane.showConfirmDialog(btnNewButton,
							"确认添加学生？",
							"确认窗口",
							JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (n != JOptionPane.YES_OPTION){
						//check();
						return;
					}
					
					String index = indexField.getText();
					String name = nameField.getText();
					int gender = genderBox.getSelectedIndex();
					String grade = (String) gradeBox.getSelectedItem();
					String mainCourse = mainCourseField.getText();
					
					if (1 == college.addStudent(new Student(index, name, gender, grade, mainCourse))){
						dispose();
						return;
					}
					indexErrorLabel.setText("添加学生失败！");
					nameErrorLabel.setText("添加学生失败！");
					mainCourseErrorLabel.setText("添加学生失败！");
				}
				
				
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label = new JLabel("学号：");
		
		JLabel lblNewLabel = new JLabel("姓名：");
		
		JLabel label_1 = new JLabel("性别：");
		
		JLabel label_2 = new JLabel("年级：");
		
		JLabel label_3 = new JLabel("专业：");
		
		indexField = new JTextField();
		indexField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		genderBox = new JComboBox<String>();
		genderBox.setModel(new DefaultComboBoxModel<String>(new String[] {"男", "女", "？"}));
		
		gradeBox = new JComboBox<String>();
		gradeBox.setModel(new DefaultComboBoxModel<String>(new String[] {"大一", "大二", "大三", "大四"}));
		
		mainCourseField = new JTextField();
		mainCourseField.setColumns(10);
		
		indexErrorLabel = new JLabel("New label");
		
		nameErrorLabel = new JLabel("New label");
		
		mainCourseErrorLabel = new JLabel("New label");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(indexField, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameField))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gradeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mainCourseField)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(indexErrorLabel)
						.addComponent(nameErrorLabel)
						.addComponent(mainCourseErrorLabel))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(indexField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(indexErrorLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameErrorLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(gradeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(mainCourseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainCourseErrorLabel))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		// TODO Auto-generated constructor stub
		clearErrorLabel();
	}

	
	protected void clearErrorLabel(){
		indexErrorLabel.setText("");
		nameErrorLabel.setText("");
		mainCourseErrorLabel.setText("");
	}
	
	protected boolean check(){
		boolean checkResult = true;
		String index = indexField.getText();
		String name = nameField.getText();
		String mainCourse = mainCourseField.getText();
		
		if (index.isEmpty()){
			indexErrorLabel.setText("错误！学号不能为空，请填写学号。");
			checkResult = false;
		} else if ( ! BasicStringChecker.check(index)){
			indexErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else if (null != college.getStudent(index)){
			indexErrorLabel.setText("错误！学号冲突，已存在相同学号的同学，请重新填写一个其他的学号。");
			checkResult = false;
		} else {
			indexErrorLabel.setText("");
		}
		
		if (name.isEmpty()){
			nameErrorLabel.setText("错误！名字不能为空，请填写名字。");
			checkResult = false;
		} else if ( ! BasicStringChecker.check(name)){
			nameErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else {
			nameErrorLabel.setText("");
		}
		
		if (mainCourse.isEmpty()){
			mainCourseErrorLabel.setText("错误！专业不能为空，请填写序号。");
			checkResult = false;
		} else if ( ! BasicStringChecker.check(mainCourse)){
			mainCourseErrorLabel.setText("错误！字符串中不能包含'&'字符。");
			checkResult = false;
		} else {
			mainCourseErrorLabel.setText("");
		}
		
		return checkResult;
	}
}
