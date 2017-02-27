package testSpace;

import basicTool.MyLogger;
import operator.ClubUpdateOperator;

public class TestClubUpdateOperator extends Test {
	public static void main(String[] args){
		prepare();
		showCollege();
		
		ClubUpdateOperator cuo = new ClubUpdateOperator(college, college.getClub("2012003"));
		
		cuo.getClub().setName("初心社——改");
		cuo.operate();
		
		MyLogger.newLine(12);
		
		showCollege();
	}

}
