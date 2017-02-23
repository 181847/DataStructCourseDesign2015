package testSpace;

import basicInterface.IInfoSet;
import basicTool.MyLogger;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.NameGetter;
import unfinishedClass.InfoSetSpecificByIndex;
import unfinishedClass.Student;

public class TestInfoSetSpecificByIndex_DeleteIndex extends Test{

	public static void main(String[] args) {
		prepare();
		
		InfoSetSpecificByIndex issbi = new InfoSetSpecificByIndex();
		for (Student s: stus){
			issbi.insertInfo(new InfoWithContainer(s));
		}
		issbi.insertInfo(new InfoWithContainer(stus[7]));
		

		MyLogger.seperate("BeforeDelete");
		issbi.traverseInfo(studentTraverser, allTrueFilter);

		issbi.delete("2002015", new NameGetter(), new AllTrueFilter());
		IInfoSet deleteSet = issbi.deleteIndex("2002002");
		
		MyLogger.seperate("AfterDelete");
		issbi.traverseInfo(studentTraverser, allTrueFilter);
		
		MyLogger.seperate("The Info had Been Deleted");
		deleteSet.traverseInfo(studentTraverser, allTrueFilter);
	}

}
