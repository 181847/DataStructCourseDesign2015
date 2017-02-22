package testSpace;

import info.DoubleLinkedInfo;
import info.infoTool.AllTrueFilter;
import info.infoTool.StudentTraverser;
import infoSet.DoubleLoopLinkedInfoSet;
import unfinishedClass.Student;

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
