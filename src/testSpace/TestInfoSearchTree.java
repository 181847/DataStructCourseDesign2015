package testSpace;

import basicTool.MyLogger;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.IndexGetter;
import info.infoTool.StudentTraverser;
import infoSet.InfoSearchTree;
import unfinishedClass.Student;

public class TestInfoSearchTree extends Test{
	public static void main(String[] args){
		prepare();
		InfoSearchTree ist = new InfoSearchTree('0');
		
		for (Student s:stus){
			ist.insertInfo(new InfoWithContainer(s), new IndexGetter());
		}
		
		ist.traverseInfo(new StudentTraverser(), new AllTrueFilter());
		ist.getSpecificInfoSet("2002005".toCharArray(), 0).traverseInfo(new StudentTraverser(), new AllTrueFilter());
		
		MyLogger.seperate("SEARCH_TREE");
		ist.getTree("2002001".toCharArray(), 0).soloTraverseInfo(new StudentTraverser(), new AllTrueFilter());
	}
}
