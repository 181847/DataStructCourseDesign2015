package testSpace;

import basicTool.MyLogger;
import info.InfoWithContainer;
import info.infoTool.GenderGetterForStudent;
import info.infoTool.PinyinGetter;
import info.infoTool.ShortPinyinGetter;
import infoInterface.IInfoGetter;
import infoSet.SearchResult;
import infoSet.SearchableInfoSet;
import unfinishedClass.Student;

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
