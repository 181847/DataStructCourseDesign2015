package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import collegeComponent.tool.GenderGetterForStudent;
import collegeComponent.tool.PinyinGetter;
import collegeComponent.tool.ShortPinyinGetter;
import info.InfoWithContainer;
import infoInterface.IInfoGetter;
import infoSet.SearchResult;
import infoSet.SearchableInfoSet;

public class TestSearchableInfoSet extends Test {
	public static void main(String[] args){
		prepare();
		
		SearchableInfoSet sis = new SearchableInfoSet(new IInfoGetter[]{indexGetter, 
																			nameGetter, 
																			new PinyinGetter(),
																			new ShortPinyinGetter(),
																			new GenderGetterForStudent()});
		
		for (Student s:stus){
			sis.insertInfo(new InfoWithContainer(s));
		}
		
		SearchResult sr = sis.search("wx");
		sr.getAllResult(allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);
		
		MyLogger.seperate("Search Male");
		sr = sis.search("M");
		sr.getAllResult(allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);
	}
}
