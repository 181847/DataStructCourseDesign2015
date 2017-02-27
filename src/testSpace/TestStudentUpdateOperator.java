package testSpace;

import basicTool.MyLogger;
import operator.StudentUpdateOperator;

public class TestStudentUpdateOperator extends Test {

	public static void main(String[] args) {
		prepare();

		showCollege();
		
		StudentUpdateOperator suo = new StudentUpdateOperator(college, college.getStudent("2002009"));
		suo.getStudent().setGrade("研究生");
		suo.getStudent().setIndex("200150");
		suo.operate();

		MyLogger.newLine(12);

		showCollege();
		
	}

}
