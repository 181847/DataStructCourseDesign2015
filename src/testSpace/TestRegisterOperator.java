package testSpace;

import basicTool.MyLogger;
import operator.RegisterOperator;

public class TestRegisterOperator extends Test {
	public static void main(String[] args){
		prepare();
		
		RegisterOperator ro = new RegisterOperator(college);
		ro.setPosition("会员");
		ro.setStudentIndex("2002015");
		ro.setClubIndex("2012003");
		ro.operate();
		

		ro.setPosition("会员");
		ro.setStudentIndex("2002009");
		ro.setClubIndex("2012003");
		ro.operate();

		ro.setPosition("会长");
		ro.setStudentIndex("2002005");
		ro.setClubIndex("2012003");
		ro.operate();

		ro.setPosition("会长");
		ro.setStudentIndex("20020011");
		ro.setClubIndex("2012005");
		ro.operate();
		
		college.getClub("2012003").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		
		MyLogger.seperate("Another Club");
		college.getClub("2012005").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		
		MyLogger.seperate("Student Clubs\n\n\n");
		MyLogger.seperate();
		MyLogger.seperate();
		college.getStudent("2002015").getMyClubs().traverseInfo(myClubTraverser, allTrueFilter);
		MyLogger.seperate("Another Student");
		college.getStudent("2002009").getMyClubs().traverseInfo(myClubTraverser, allTrueFilter);
		MyLogger.seperate("Another Student");
		college.getStudent("2002005").getMyClubs().traverseInfo(myClubTraverser, allTrueFilter);
		MyLogger.seperate("Another Student");
		college.getStudent("20020011").getMyClubs().traverseInfo(myClubTraverser, allTrueFilter);
		
	}
}
