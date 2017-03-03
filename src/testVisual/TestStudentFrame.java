package testVisual;

import javax.swing.JFrame;

import aboutVisual.ClubFrame;
import aboutVisual.CollegeFrame;
import aboutVisual.StudentFrame;
import testSpace.Test;

public class TestStudentFrame extends Test {
	public static void main(String[] args){
		prepare();
		
		JFrame frame = new ClubFrame(college, "2012003");
		frame.setVisible(true);
		
		JFrame frame1 = new CollegeFrame(college, "saveData//data", "saveData//backUp");
		frame1.setVisible(true);
		
		JFrame frame2 = new StudentFrame(college, "2002009");
		frame2.setVisible(true);
	}
}
