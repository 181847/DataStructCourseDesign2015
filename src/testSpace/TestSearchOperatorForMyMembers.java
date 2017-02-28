package testSpace;

import basicTool.MyLogger;
import operator.ClubPositionOperator;
import operator.DeregisterOperator;
import operator.SearchOperatorForMyMembers;
import operator.StudentUpdateOperator;

public class TestSearchOperatorForMyMembers extends Test {
	public static void main(String[] args){
		prepare();
		//Test.showCollege();
		SearchOperatorForMyMembers sofmm = new SearchOperatorForMyMembers(college, "2012005");
		sofmm.setSearchInfo("");
		sofmm.selectSearchLog(-1);
		sofmm.operate();
		sofmm.getResult().getAllResult(allTrueFilter).traverseInfo(myMemberTraverser, allTrueFilter);
		
		/**
		ClubPositionOperator cpo = new ClubPositionOperator(college);
		cpo.setClubIndex("2012005");
		cpo.setStudentIndex("2002009");
		cpo.setPosition("秘书");
		cpo.operate();
		
		sofmm.operate();
		sofmm.getResult().getAllResult(allTrueFilter).traverseInfo(myMemberTraverser, allTrueFilter);
		*/
		
		StudentUpdateOperator suo = new StudentUpdateOperator(college);
		suo.setStudent(college.getStudent("2002009"));
		suo.setNewIndex("200200978");
		suo.operate();
		
		MyLogger.seperate("After Change Index");
		sofmm.operate();
		sofmm.getResult().getAllResult(allTrueFilter).traverseInfo(myMemberTraverser, allTrueFilter);
		
		DeregisterOperator dro = new DeregisterOperator(college);
		dro.setClubIndex("2012005");
		dro.setStudentIndex("200200978");
		dro.operate();
		
		MyLogger.seperate("After Dregist");
		sofmm.operate();
		sofmm.getResult().getAllResult(allTrueFilter).traverseInfo(myMemberTraverser, allTrueFilter);
		
	}

}
