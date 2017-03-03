package main;

import aboutVisual.CollegeFrame;
import collegeComponent.College;

public class CollegeMain {

	public static void main(String[] args) {
		new CollegeFrame(new College(), "saveData//data", "saveData//backup")
			.setVisible(true);
	}

}
