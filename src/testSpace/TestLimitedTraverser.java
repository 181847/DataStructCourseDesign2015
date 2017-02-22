package testSpace;

import basicTool.AllTrueFilter;
import basicTool.MyLogger;
import info.InfoWithContainer;
import student.Student;
import student.infoTool.LimitedStudentTraverser;
import student.infoTool.StudentNameGetter;
import unfinishedClass.InfoSearchTree;

public class TestLimitedTraverser extends Test {

	public static void main(String[] args) {
		prepare();
		
		InfoSearchTree ist = new InfoSearchTree('\0');
		for (Student s: stus){
			ist.insertInfo(new InfoWithContainer(s), 
							new StudentNameGetter());
		}
		
		ist.limitedTraverseInfo(new LimitedStudentTraverser(3), new AllTrueFilter());
		
		MyLogger.seperate();
		ist.deleteSpecific("acd".toCharArray(), 0, "acd", new StudentNameGetter(), new AllTrueFilter());
		ist.limitedTraverseInfo(new LimitedStudentTraverser(45), new AllTrueFilter());
	}

}
