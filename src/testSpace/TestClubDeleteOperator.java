package testSpace;

import basicTool.MyLogger;
import operator.ClubDeleteOperator;
import operator.ClubUpdateOperator;

public class TestClubDeleteOperator extends Test {

	public static void main(String[] args) {
		prepare();
		college.getClubInfoSet().traverseInfo(clubTraverser, allTrueFilter);
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);
		
		
		
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		MyLogger.newLine();
		
		ClubUpdateOperator cuo = new ClubUpdateOperator(college, college.getClub("2012003"));
		
		cuo.setNewIndex("201200345");
		cuo.operate();
		
		ClubDeleteOperator cdo = new ClubDeleteOperator(college, "201200345");
		cdo.operate();
		
		college.getClubInfoSet().traverseInfo(clubTraverser, allTrueFilter);
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);
	}

}
