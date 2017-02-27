package testVisual;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import operator.SearchOperatorForClubs;
import testSpace.Test;
import unfinishedClass.ClubModelTraverser;
import unfinishedClass.SearchPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestSearchPanel extends JFrame {
	public SearchPanel searchPanel;
	public TestSearchPanel() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		searchPanel = new SearchPanel(new SearchOperatorForClubs(Test.college),
										new String[]{
											"社团编号", "社团名字", "创建日期"
														},
										new ClubModelTraverser());
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
