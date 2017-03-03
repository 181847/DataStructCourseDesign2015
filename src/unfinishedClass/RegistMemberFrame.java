package unfinishedClass;

import aboutVisual.FrameWithCollege;
import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import info.infoTool.AllTrueFilter;
import operator.RegisterOperator;
import operator.SearchOperator;
import operator.SearchOperatorForMyMembers;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 注册社员的窗口类。
 */
public class RegistMemberFrame extends FrameWithCollege {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6980831906560948523L;
	protected String clubIndex;
	protected String studentIndex;
	
	protected RegisterOperator registerOperator;
	protected SearchOperator searchOperatorForClubMembers;
	private JTextField positionField;
	JLabel positionErrorLabel;

	public RegistMemberFrame(College college, String clubIndex, String studentIndex) {
		super(college);
		setResizable(false);
		this.clubIndex = clubIndex;
		this.studentIndex = studentIndex;
		Club club = college.getClub(clubIndex);
		Student student = college.getStudent(studentIndex);
		
		if (club == null || student == null){
			MyLogger.logError("RegistMemberFrame创建时，社团编号或学生编号有误，ClubMemberControlFrame创建失败。");
			return;
		}
		
		registerOperator = new RegisterOperator(college);
		registerOperator.setClubIndex(clubIndex);
		registerOperator.setStudentIndex(studentIndex);
		
		setSize(670, 270);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		positionField = new JTextField();
		positionField.setColumns(10);
		
		JButton button = new JButton("参加社团");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (check()){
					
					int n = JOptionPane.showConfirmDialog(button,
							"确认参加社团“" + club.getName() + "”？",
							"确认窗口",
							JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (n != JOptionPane.YES_OPTION){
						//check();
						return;
					}
					
					registerOperator.setPosition(positionField.getText());
					registerOperator.operate();
					
					dispose();
				}
			}
		});
		
		positionErrorLabel = new JLabel("");
		
		JLabel label_4 = new JLabel("职位：");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(positionField, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
								.addComponent(positionErrorLabel)))
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(positionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4))
							.addGap(13)
							.addComponent(positionErrorLabel, GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
							.addContainerGap(34, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		JLabel label = new JLabel("社团编号：" + club.getIndex());
		
		JLabel label_1 = new JLabel("社团名字：" + club.getName());
		
		JLabel label_2 = new JLabel("学生名字：" + student.getIndex());
		
		JLabel label_3 = new JLabel("学生学号：" + student.getName());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label_2))
					.addGap(221))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGap(28))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		searchOperatorForClubMembers = new SearchOperatorForMyMembers(college, club);
		searchOperatorForClubMembers.deselectSearchLog(-1);
		searchOperatorForClubMembers.selectSearchLog(7);
		searchOperatorForClubMembers.setSearchInfo("会长");
		
	}
	
	private boolean check(){
		if (college
				.getClub(clubIndex)
				.getMyMembers()
				.getIndex(studentIndex)
				.isEmpty()){
			
			String changedPosition = positionField.getText();
			if ( ! BasicStringChecker.check(changedPosition)){
				positionErrorLabel.setText("错误！字符串中不能包含'&'字符。");
				return false;
				
			} else if (changedPosition.isEmpty()){
				positionErrorLabel.setText("错误！职位不能为空");
				return false;
				
			} else if (changedPosition.equals("会长")){
				searchOperatorForClubMembers.operate();
				if ( searchOperatorForClubMembers
										.getResult()
										.getAllResult(new SameNameFilter("会长"))
										.isEmpty() ){
					
					positionErrorLabel.setText("");
					return true;
					
				} else {
					positionErrorLabel.setText("这个社团已存在会长，请指定一个其他的职位");
					return false;
				}//if
				
			} else {
				positionErrorLabel.setText("");
				return true;
				
			}//if
			
		} else {
			positionErrorLabel.setText("已经参加了这个社团，请不要重复参加同一个社团。");
			return false;
			
		}
	}
}
