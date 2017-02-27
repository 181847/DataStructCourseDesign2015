package unfinishedClass;

import javax.swing.JFrame;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import info.infoTool.AllTrueFilter;
import operator.ClubUpdateOperator;
import operator.SearchOperatorForClubs;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ClubFrame extends FrameWithCollege {
	protected Club club;
	protected final String[] myMemberColumn = {"社员学号", "姓名", "职位"};
	protected SearchPanel myMemberSearchPanel;
	protected ClubUpdateOperator clubUpdateOperator;
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 386202404159243225L;
	private JTextField indexField;
	private JTextField nameField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	
	JLabel indexErrorLabel;
	JLabel nameErrorLabel;
	JLabel dateErrorLabel;
	
	private String index;
	private String name;
	private String date;
	private String year;
	private String month;
	private String day;
	
	public ClubFrame(College college, String clubIndex){
		super(college);
		this.setSize(530, 600);
		setResizable(false);
		club = college.getClub(clubIndex);
		if (club == null){
			MyLogger.logError("ClubFrame没有获得Club对象，"
					+ "请检查社团编号是否正确：" + clubIndex);
		} else {
			myMemberSearchPanel = new SearchPanel(new SearchOperatorForMyMembers(college, club), myMemberColumn, new MyMemberModelTraverser());
			clubUpdateOperator = new ClubUpdateOperator(college, club);
		}
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		panel_1.add(myMemberSearchPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		indexField = new JTextField();
		indexField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel label = new JLabel("社团编号：");
		
		JLabel lblNewLabel = new JLabel("社团名字：");
		
		JLabel label_1 = new JLabel("社团创建时间：");
		
		yearField = new JTextField();
		yearField.setColumns(10);
		
		monthField = new JTextField();
		monthField.setColumns(10);
		
		JLabel label_2 = new JLabel("年");
		
		JLabel label_3 = new JLabel("月");
		
		dayField = new JTextField();
		dayField.setColumns(10);
		
		JLabel label_4 = new JLabel("日");
		
		JButton btnNewButton = new JButton("确定更改");
		
		
		
		indexErrorLabel = new JLabel("1");
		
		nameErrorLabel = new JLabel("1");
		
		dateErrorLabel = new JLabel("1");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(176)
							.addComponent(btnNewButton)
							.addGap(215))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(indexField, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(nameErrorLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(indexErrorLabel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_3)
									.addGap(6)
									.addComponent(dayField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateErrorLabel, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(33, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(indexField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(indexErrorLabel))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(nameErrorLabel))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(dateErrorLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 500, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		updateDataToTextField();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()){
					index = indexField.getText();
					name = nameField.getText();
					year = yearField.getText();
					month = monthField.getText();
					day = monthField.getText();
					club.setIndex(index);
					club.setName(name);
					club.setDate(year + "-" + month + "-" + day);
					clubUpdateOperator.operate();
					updateDataToTextField();
				}
			}
		});
		
	}
	
	private void updateDataToTextField() {
		index = club.getIndex();
		name = club.getName();
		date = club.getDate();
		String[] seperateDate = date.split("-");
		year = seperateDate[0];
		month = seperateDate[1];
		day = seperateDate[2];
		
		indexField.setText(index);
		nameField.setText(name);
		yearField.setText(year);
		monthField.setText(month);
		dayField.setText(day);
		
		clubUpdateOperator.setClub(club);
	}

	protected boolean check(){
		int checkResult = 1;
		String changedIndex = indexField.getText();
		String changedName = nameField.getText();
		String changedYear = yearField.getText();
		String changedMonth = monthField.getText();
		String changedDay = dayField.getText();
		
		//检查序号
		if (changedIndex.compareTo(index) == 0 ){
			indexErrorLabel.setText("序号名不变。");
		} else {
			if (changedIndex.isEmpty()){
				indexErrorLabel.setText("错误！序号名为空。");
				checkResult *= 0;
			}else if (college.getClub(changedIndex) != null){
				indexErrorLabel.setText("错误！序号名冲突。");
				checkResult *= 0;
			} else {
				indexErrorLabel.setText("");
			}
		}//if
			
			
			
		
		
		//检查名字
		if (changedName.compareTo(name) == 0 ){
			nameErrorLabel.setText("名字不变。");
		} else {
			if (changedIndex.isEmpty()){
				nameErrorLabel.setText("错误！名字为空。");
				checkResult *= 0;
			} else {
				SearchOperatorForClubs sofc = new SearchOperatorForClubs(getCollege());
				sofc.deselectSearchLog(-1);
				sofc.selectSearchLog(1);
				sofc.setSearchInfo(changedName);
				sofc.operate();
				
				if (sofc.getResult().getAllResult(new AllTrueFilter()).isEmpty()){
					nameErrorLabel.setText("");
				} else {
					nameErrorLabel.setText("错误！名字冲突。");
					checkResult *= 0;
				}
			}
		}
		
		try {
			club.getDateFormate().parse(changedYear + "-" + changedMonth + "-" + changedDay);
			dateErrorLabel.setText("");
		} catch (ParseException e) {
			dateErrorLabel.setText("错误！日期格式不正确。");
			checkResult *= 0;
			//MyLogger.logException(e);
		}
		
		return checkResult == 1;
	}
}
