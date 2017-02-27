package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import collegeComponent.tool.traverser.StudentTraverser;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.IndexGetter;
import infoSet.InfoSearchTree;

public class TestInfoSearchTree extends Test{
	public static void main(String[] args){
		prepare();
		InfoSearchTree ist = new InfoSearchTree('0');
		
		for (Student s:stus){
			ist.insertInfo(new InfoWithContainer(s), new IndexGetter());
		}
		
		ist.traverseInfo(new StudentTraverser(), new AllTrueFilter());
		ist.getSoloInfoSet("2002005".toCharArray(), 0).traverseInfo(new StudentTraverser(), new AllTrueFilter());
		
		MyLogger.seperate("SEARCH_TREE");
		ist.getTree("2002001".toCharArray(), 0).specialTraverseInfo(new StudentTraverser(), new AllTrueFilter());
	}
}
