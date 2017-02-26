package testSpace;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;

public class TestCollege extends Test {

	public static void main(String[] args) {
		prepare();
		College college = new College();
		
		for (Club club: clubs){
			college.addClub(club);
		}
		
		for (Student stud: stus){
			college.addStudent(stud);
		}
		
		college.getClubInfoSet().traverseInfo(clubTraverser, allTrueFilter);
		
		MyLogger.seperate("Student Info");
		college.getStudentInfoSet().traverseInfo(studentTraverser, allTrueFilter);

	}

}
