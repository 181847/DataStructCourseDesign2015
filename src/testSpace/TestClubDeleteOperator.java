package testSpace;

import basicTool.MyLogger;
import unfinishedClass.ClubDeleteOperator;

public class TestClubDeleteOperator extends Test {

	public static void main(String[] args) {
		prepare();
		college.getClubInfoSet().traverseInfo(clubTraverser, allTrueFilter);
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);
		
		ClubDeleteOperator cdo = new ClubDeleteOperator(college, "2012003");
		cdo.operate();
		
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		
		college.getClubInfoSet().traverseInfo(clubTraverser, allTrueFilter);
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);
	}

}
