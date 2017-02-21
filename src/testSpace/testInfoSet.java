package testSpace;

import basicTool.AllTrueFilter;
import info.DoubleLinkedInfo;
import student.Student;
import student.infoTool.StudentTraverser;
import unfinishedClass.DoubleLoopLinkedInfoSet;

public class TestInfoSet extends Test{
	public static void main(String[] args){
		prepare();
		
		DoubleLoopLinkedInfoSet iset = new DoubleLoopLinkedInfoSet();
		for (Student s: stus){
			iset.insertInfo(new DoubleLinkedInfo(s));
		}
		
		iset.traverseInfo(new StudentTraverser(), new AllTrueFilter());
	}
}
