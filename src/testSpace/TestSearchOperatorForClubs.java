package testSpace;

import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import operator.SearchOperator;
import operator.SearchOperatorForClubs;

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
		
		so.setSearchInfo("");
		so.selectSearchLog(-1);
		//so.deselectSearchLog(0);
		so.operate();
		so.getResult().getAllResult(allTrueFilter).traverseInfo(clubTraverser, allTrueFilter);
	}
}
