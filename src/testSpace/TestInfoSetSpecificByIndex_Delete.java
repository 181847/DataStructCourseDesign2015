package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.traverser.StudentTraverser;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import infoSet.InfoSetSpecificByIndex;

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
