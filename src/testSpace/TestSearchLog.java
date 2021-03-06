package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import info.InfoWithContainer;
import infoSet.InfoSearchTree;
import infoSet.SearchLog;

public class TestSearchLog extends Test {
	public static void main(String[] args){
		prepare();
		
		SearchLog sl = new SearchLog(indexGetter);
		for (Student s:stus){
			sl.putInfo(new InfoWithContainer(s));
		}
		
		InfoSearchTree result = sl.searchInfo("2002002");
		if (result == null){
			MyLogger.log("搜索结果为空");
		} else {
			result.specialTraverseInfo(studentTraverser, allTrueFilter);
		}
		
		MyLogger.seperate("After Delete");
		sl.deleteInfo("2002001");
		sl.traverseInfo(studentTraverser, allTrueFilter);
	}
}
