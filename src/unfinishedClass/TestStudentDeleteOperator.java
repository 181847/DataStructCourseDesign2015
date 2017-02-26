package unfinishedClass;

import basicTool.MyLogger;
import testSpace.Test;

public class TestStudentDeleteOperator extends Test {
	public static void main(String[] args) {
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
		
		ro.setPosition("会员");
		ro.setStudentIndex("2002009");
		ro.setClubIndex("2012005");
		ro.operate();
		
		MyLogger.seperate(" The Club \n\n");
		college.getClub("2012003").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		MyLogger.seperate("Another Club");
		college.getClub("2012005").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		
		StudentDeleteOperator sdo = new StudentDeleteOperator(college);
		sdo.setTargetIndex("2002008");
		sdo.operate();
		
		MyLogger.seperate(" The Club \n\n");
		college.getClub("2012003").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		MyLogger.seperate("Another Club");
		college.getClub("2012005").getMyMembers().traverseInfo(myMemberTraverser, allTrueFilter);
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);		
	}
}
