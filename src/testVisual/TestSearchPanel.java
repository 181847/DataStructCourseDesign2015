package testVisual;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import aboutVisual.SearchPanel;
import collegeComponent.tool.traverser.ClubModelTraverser;
import operator.SearchOperatorForClubs;
import testSpace.Test;
import unfinishedClass.AbstractTableNotifier;
import unfinishedClass.ClubFrame;

public class TestSearchPanel extends JFrame {
	public SearchPanel searchPanel;
	public TestSearchPanel() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		searchPanel = new SearchPanel(new SearchOperatorForClubs(Test.college),
										new String[]{
											"社团编号", "社团名字", "创建日期"
														},
										new ClubModelTraverser(),
										new AbstractTableNotifier() {
											@Override
											public boolean fire(int rowIndex, int columnIndex, JTable table){
													if (columnIndex == 0){
														JFrame clubFrame = new ClubFrame(Test.college, 
																(String) table.getValueAt(rowIndex, columnIndex));
														
														clubFrame.setLocationRelativeTo(table);
														clubFrame.setVisible(true);
													}//if
													return false;
												}//fire()
											});//AbstractTableNotifier
		searchPanel.getResultTable().setEnabled(true);
		this.getContentPane().add(searchPanel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8756477348360760366L;
	
	public static void main(String[] args){
		Test.prepare();
		JFrame frame = new TestSearchPanel();
		frame.setVisible(true);
	}
}
