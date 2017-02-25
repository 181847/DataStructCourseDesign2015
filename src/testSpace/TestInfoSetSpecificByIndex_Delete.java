package testSpace;

import basicTool.MyLogger;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.IndexGetter;
import info.infoTool.NameGetter;
import info.infoTool.StudentTraverser;
import infoSet.InfoSetSpecificByIndex;
import unfinishedClass.Student;

public class TestInfoSetSpecificByIndex_Delete extends Test {

	public static void main(String[] args) {
		prepare();
		
		InfoSetSpecificByIndex issbi = new InfoSetSpecificByIndex();
		for (Student s: stus){
			issbi.insertInfo(new InfoWithContainer(s));
		}
		issbi.insertInfo(new InfoWithContainer(stus[7]));

		issbi.delete("2002015", new NameGetter(), new AllTrueFilter());
		
		MyLogger.seperate("SHOW STUDENT");
		
		issbi.traverseInfo(new StudentTraverser(), new AllTrueFilter());
	}

}
