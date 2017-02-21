package testSpace;

import basicTool.AllTrueFilter;
import info.InfoWithContainer;
import student.Student;
import student.infoTool.StudentNameGetter;
import student.infoTool.StudentTraverser;
import unfinishedClass.InfoSearchTree;

public class TestInfoSearchTree extends Test{
	public static void main(String[] args){
		prepare();
		InfoSearchTree ist = new InfoSearchTree('0');
		
		for (Student s:stus){
			ist.insertInfo(new InfoWithContainer(s), new StudentNameGetter());
		}
		
		ist.traverseInfo(new StudentTraverser(), new AllTrueFilter());
		ist.getInfoSetFromCharArray("abc".toCharArray(), 0).traverseInfo(new StudentTraverser(), new AllTrueFilter());
	}
}
