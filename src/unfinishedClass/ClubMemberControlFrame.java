package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import info.infoTool.AllTrueFilter;
import operator.ClubPositionOperator;
import operator.DeregisterOperator;
import operator.SearchOperator;
import operator.SearchOperatorForMyMembers;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import aboutVisual.FrameWithCollege;

import java.awt.Color;

public class ClubMemberControlFrame extends FrameWithCollege {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093856738623201003L;
	
	protected String clubIndex;
	protected String studentIndex;
	public String position;

	protected SearchOperator searchOperatorForClubMembers;
	protected ClubPositionOperator clubPositionOperator;
	protected DeregisterOperator deregisterOperator;
	protected JTextField positionField;
	protected JLabel positionErrorLabel;
	protected JLabel currentPositionLabel;

	public ClubMemberControlFrame(College college, String clubIndex, String studentIndex, String position){
		super(college);
		this.clubIndex = clubIndex;
		this.studentIndex = studentIndex;
		this.position = position;
		
		setSize(670, 270);

		Club club = college.getClub(clubIndex);
		Student student = college.getStudent(studentIndex);
		
		if (club == null || student == null){
			MyLogger.logError("ClubMemberControlFrame创建时，社团编号或学生编号有误，ClubMemberControlFrame创建失败。");
			return;
		}
		
		positionErrorLabel = new JLabel("");
		
		JButton button = new JButton("修改社员职位");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()){
					String changedPosition = positionField.getText();
					
					int n = JOptionPane.showConfirmDialog(null,
							"确认修改职位信息？",
							"确认窗口",
							JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (n != JOptionPane.YES_OPTION){
						//check();
						return;
					}
					
					clubPositionOperator.setPosition(changedPosition);
					if (clubPositionOperator.operate() == 1){
						freshPositionLabel(changedPosition);
					}
				}
			}

			
		});
		
		positionField = new JTextField();
		positionField.setColumns(10);
		
		JButton button_1 = new JButton("退社");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null,
						"确认退出“" + club.getName() + "”社团？",
						"确认窗口",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (n != JOptionPane.YES_OPTION){
					//check();
					return;
				}
				
				if (1 == deregisterOperator.operate()){
					dispose();
				} else {
					positionErrorLabel.setText("错误！退出社团失败，可能造成信息不完全，请不要保存当前文件。");
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(positionField, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(positionErrorLabel, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
							.addGap(75))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(positionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(positionErrorLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(button)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
		);
		
		JLabel clubIndexLabel = new JLabel("社团编号：" + club.getIndex());
		
		JLabel clubNameLabel = new JLabel("社团名字：" + club.getName());
		
		JLabel studentIndexLabel = new JLabel("社员学号：" + student.getIndex());
		
		JLabel studentNameLabel = new JLabel("社员名字：" + student.getName());
		
		currentPositionLabel = new JLabel();
		
		freshPositionLabel(position);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(clubNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(clubIndexLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
							.addGap(58)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(studentIndexLabel)
								.addComponent(studentNameLabel))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(currentPositionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(338))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(studentIndexLabel)
							.addGap(18)
							.addComponent(studentNameLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(clubIndexLabel)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(clubNameLabel)))
					.addGap(18)
					.addComponent(currentPositionLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		
		
		clubPositionOperator = new ClubPositionOperator(college);
		clubPositionOperator.setClubIndex(clubIndex);
		clubPositionOperator.setStudentIndex(studentIndex);
		
		deregisterOperator = new DeregisterOperator(college);
		deregisterOperator.setClubIndex(clubIndex);
		deregisterOperator.setStudentIndex(studentIndex);
		
		searchOperatorForClubMembers = new SearchOperatorForMyMembers(college, club);
		searchOperatorForClubMembers.deselectSearchLog(-1);
		searchOperatorForClubMembers.selectSearchLog(7);
		searchOperatorForClubMembers.setSearchInfo("会长");
		
	}
	
	private boolean check(){
		String changedPosition = positionField.getText();
		if (changedPosition.equals(position)){
			positionErrorLabel.setText("职位不变");
			return false;
		} else if (positionField.getText().isEmpty()){
			positionErrorLabel.setText("错误！职位不能为空");
			return false;
		} else if (changedPosition.equals("会长")){
			searchOperatorForClubMembers.operate();
			if ( searchOperatorForClubMembers
					.getResult()
					.getAllResult(new AllTrueFilter())
					.isEmpty() ){
				positionErrorLabel.setText("");
				return true;
			} else {
				positionErrorLabel.setText("这个社团已存在会长，请指定一个其他的职位");
				return false;
			}
		} else {
			positionErrorLabel.setText("");
			return true;
		}
	}

	private void freshPositionLabel(String changedPosition) {
		position = changedPosition;
		currentPositionLabel.setText("该社员当前职位：" + position);
	}
}
