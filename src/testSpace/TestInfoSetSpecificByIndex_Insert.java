package testSpace;

import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.StudentTraverser;
import unfinishedClass.InfoSetSpecificByIndex;

public class TestInfoSetSpecificByIndex_Insert extends Test {
	public static void main(String[] args){
		prepare();
		
		InfoSetSpecificByIndex issbi = new InfoSetSpecificByIndex();
		System.out.println(issbi.insertInfo(new InfoWithContainer(stus[0])));
		System.out.println(issbi.insertInfo(new InfoWithContainer(stus[0])));
		
		issbi.traverseInfo(new StudentTraverser(), new AllTrueFilter());
	}

}