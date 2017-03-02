package unfinishedClass;

import aboutVisual.FrameWithCollege;
import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import operator.SearchOperatorForClubs;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AddClubFrame extends FrameWithCollege {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3524909688554160169L;
	private JTextField indexField;
	private JTextField nameField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	
	private JLabel dateErrorLabel;
	private JLabel nameErrorLabel;
	private JLabel indexErrorLabel;
	
	protected SimpleDateFormat dateFormat;
	protected SearchOperatorForClubs nameSearchOperatorForClubs;

	public AddClubFrame(College college) {
		super(college);
		setResizable(false);
		
		if (college == null){
			return;
		}
		setSize(600, 310);
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton confirmButton = new JButton("添加社团");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (check()){
					String index = indexField.getText();
					String name = nameField.getText();
					String year = yearField.getText();
					String month = yearField.getText();
					String day = dayField.getText();
					
					
					int n = JOptionPane.showConfirmDialog(confirmButton,
							"确认添加社团“" + name + "”？",
							"确认窗口",
							JOptionPane.YES_NO_CANCEL_OPTION);
					
					if (n != JOptionPane.YES_OPTION){
						//check();
						return;
					}
					
					if (1 == college.addClub(new Club(index, name, year + "-" + month + "-" + day))){
						dispose();
						return;
					}
					
					indexErrorLabel.setText("添加社团失败！");
					nameErrorLabel.setText("添加社团失败！");
					dateErrorLabel.setText("添加社团失败！");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addComponent(confirmButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 134, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel label = new JLabel("社团编号：");
		
		JLabel label_1 = new JLabel("社团名字：");
		
		JLabel label_2 = new JLabel("社团创建时间：");
		
		indexField = new JTextField();
		indexField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		yearField = new JTextField();
		yearField.setColumns(10);
		
		JLabel label_3 = new JLabel("年");
		
		monthField = new JTextField();
		monthField.setColumns(10);
		
		JLabel label_4 = new JLabel("月");
		
		dayField = new JTextField();
		dayField.setColumns(10);
		
		JLabel label_5 = new JLabel("日");

		
		indexErrorLabel = new JLabel("New label");
		
		nameErrorLabel = new JLabel("New label");
		
		dateErrorLabel = new JLabel("New label");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(indexField))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameField))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dayField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_5)
					.addGap(40)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(dateErrorLabel)
						.addComponent(nameErrorLabel)
						.addComponent(indexErrorLabel))
					.addContainerGap(119, Short.MAX_VALUE))
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
						.addComponent(label_1)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameErrorLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(dateErrorLabel))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		
		nameSearchOperatorForClubs = new SearchOperatorForClubs(getCollege());
		nameSearchOperatorForClubs.deselectSearchLog(-1);
		nameSearchOperatorForClubs.selectSearchLog(1);
		clearErrorLabel();
	}
	
	private void clearErrorLabel(){
		indexErrorLabel.setText("");
		nameErrorLabel.setText("");
		dateErrorLabel.setText("");
	}
	
	protected boolean check(){
		boolean checkResult = true;
		String index = indexField.getText();
		String name = nameField.getText();
		String year = yearField.getText();
		String month = yearField.getText();
		String day = dayField.getText();
		
		if (index.isEmpty()){
			indexErrorLabel.setText("错误！序号名不能为空。");
		} else if (college.getClub(index) != null){
			indexErrorLabel.setText("错误！序号名冲突。");
			checkResult = false;
		} else {
			indexErrorLabel.setText("");
		}
		
		if (name.isEmpty()){
			nameErrorLabel.setText("错误！名字为空。");
			checkResult = false;
		} else {
			nameSearchOperatorForClubs.setSearchInfo(name);
			nameSearchOperatorForClubs.operate();
			
			if (nameSearchOperatorForClubs
					.getResult()
					.getAllResult(new SameNameFilter(name))
					.isEmpty()){
				nameErrorLabel.setText("");
			} else {
				nameErrorLabel.setText("错误！名字冲突。");
				checkResult = false;
			}
		}
		
		try {
			dateFormat.parse(year + "-" + month + "-" + day);
			dateErrorLabel.setText("");
		} catch (ParseException e) {
			dateErrorLabel.setText("错误！日期格式不正确。");
			checkResult = false;
			MyLogger.logException(e);
		}
		
		return checkResult;
	}
}
