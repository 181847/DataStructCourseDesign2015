package testSpace;

import basicTool.AllTrueFilter;
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
		
		ist.limitedTraverseInfo(new LimitedStudentTraverser(2), new AllTrueFilter());

	}

}
