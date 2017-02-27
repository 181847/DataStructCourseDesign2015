package testVisual;

import javax.swing.JFrame;

import testSpace.Test;
import unfinishedClass.ClubFrame;
import unfinishedClass.CollegeFrame;

public class TestClubFrame {
	public static void main(String[] args){
		Test.prepare();
		
		JFrame frame = new ClubFrame(Test.college, "2012003");
		frame.setVisible(true);
		
		JFrame frame1 = new CollegeFrame(Test.college);
		frame1.setVisible(true);
	}
}
