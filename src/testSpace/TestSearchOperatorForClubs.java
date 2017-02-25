package testSpace;

import unfinishedClass.Club;
import unfinishedClass.College;
import unfinishedClass.SearchOperator;
import unfinishedClass.SearchOperatorForClubs;
import unfinishedClass.Student;

public class TestSearchOperatorForClubs extends Test {
	public static void main(String[] args){
		prepare();
		College college = new College();
		
		for (Club club: clubs){
			college.addClub(club);
		}
		
		for (Student stud: stus){
			college.addStudent(stud);
		}
		
		SearchOperator so = new SearchOperatorForClubs(college);
		
		so.setSearchInfo("201");
		so.selectSearchLog(-1);
		so.deselectSearchLog(0);
		so.operate();
		so.getResult().getAllResult(allTrueFilter).traverseInfo(clubTraverser, allTrueFilter);
	}
}
