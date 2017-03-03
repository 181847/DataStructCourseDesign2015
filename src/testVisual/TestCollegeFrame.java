package testVisual;

import javax.swing.JFrame;

import aboutVisual.CollegeFrame;
import collegeComponent.College;
import testSpace.Test;

public class TestCollegeFrame extends Test {

	public static void main(String[] args) {
		//prepare();
		
		JFrame collegeFrame = new CollegeFrame(new College(), "saveData//data", "saveData//backup");
		collegeFrame.setVisible(true);

	}

}
