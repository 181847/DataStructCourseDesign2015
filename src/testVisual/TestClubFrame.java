package testVisual;

import javax.swing.JFrame;

import testSpace.Test;
import unfinishedClass.ClubFrame;
import unfinishedClass.CollegeFrame;

public class TestClubFrame extends Test{
	public static void main(String[] args){
		prepare();

		JFrame frame = new ClubFrame(college, "2012003");
		frame.setVisible(true);

		JFrame frame1 = new CollegeFrame(college);
		frame1.setVisible(true);
	}
}
