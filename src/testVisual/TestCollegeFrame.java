package testVisual;

import javax.swing.JFrame;

import testSpace.Test;
import unfinishedClass.CollegeFrame;
import unfinishedClass.CollegeFrameDemo;

public class TestCollegeFrame extends Test {

	public static void main(String[] args) {
		prepare();
		
		JFrame collegeFrame = new CollegeFrame(college);
		collegeFrame.setVisible(true);

	}

}
