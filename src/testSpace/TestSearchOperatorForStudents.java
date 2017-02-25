package testSpace;

import unfinishedClass.Club;
import unfinishedClass.College;
import unfinishedClass.SearchOperator;
import unfinishedClass.SearchOperatorForStudents;
import unfinishedClass.Student;

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
		so.deselectSearchLog(4);
		so.operate();
		so.getResult().getAllResult(allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);
	}
}