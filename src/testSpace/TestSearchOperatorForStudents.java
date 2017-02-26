package testSpace;

import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import operator.SearchOperator;
import operator.SearchOperatorForStudents;

public class TestSearchOperatorForStudents extends Test {
	public static void main(String[] args){
		prepare();
		College college = new College();
		
		for (Club club: clubs){
			college.addClub(club);
		}
		
		for (Student stud: stus){
			college.addStudent(stud);
		}
		
		SearchOperator so = new SearchOperatorForStudents(college);
		
		so.setSearchInfo("F");
		so.selectSearchLog(-1);
		so.deselectSearchLog(3);
		so.operate();
		so.getResult().getAllResult(allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);
	}
}
