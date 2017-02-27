package testSpace;

import collegeComponent.Student;
import collegeComponent.tool.traverser.StudentTraverser;
import info.DoubleLinkedInfo;
import info.infoTool.AllTrueFilter;
import infoSet.DoubleLoopLinkedInfoSet;

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
