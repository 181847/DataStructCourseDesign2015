package testVisual;

import javax.swing.JFrame;

import collegeComponent.College;
import testSpace.Test;
import unfinishedClass.CollegeFrame;

public class TestCollegeFrame extends Test {

	public static void main(String[] args) {
		//prepare();
		
		JFrame collegeFrame = new CollegeFrame(new College());
		collegeFrame.setVisible(true);

	}

}
