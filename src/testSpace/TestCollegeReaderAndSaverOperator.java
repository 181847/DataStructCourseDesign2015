package testSpace;

import collegeComponent.College;
import unfinishedClass.CollegeFrame;
import unfinishedClass.CollegeReaderAndSaverOperator;

public class TestCollegeReaderAndSaverOperator extends Test {
	public static void main(String[] arg){
		prepare();
		College newCollege = new College();
		CollegeReaderAndSaverOperator collegeOperator = 
				new CollegeReaderAndSaverOperator(newCollege);
		collegeOperator.read("data");
		
		new CollegeFrame(newCollege).setVisible(true);
	}
}
