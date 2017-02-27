package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import collegeComponent.tool.getter.GenderGetterForStudent;
import collegeComponent.tool.getter.PinyinGetter;
import collegeComponent.tool.getter.ShortPinyinGetter;
import info.InfoWithContainer;
import infoInterface.IInfoGetter;
import infoSet.SearchableInfoSet;

public class TestSearchableInfoSet_Delete extends Test {

	public static void main(String[] args) {
		prepare();
		
		SearchableInfoSet sis = new SearchableInfoSet(new IInfoGetter[]{indexGetter, 
																			nameGetter, 
																			new PinyinGetter(),
																			new ShortPinyinGetter(),
																			new GenderGetterForStudent()});
		
		for (Student s:stus){
			sis.insertInfo(new InfoWithContainer(s));
		}
		
		sis.deleteIndex("2002002");
		
		sis.delete("M", genderGetter, allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);

		MyLogger.seperate("After Delete");
		sis.search("M").getAllResult(allTrueFilter).traverseInfo(studentTraverser, allTrueFilter);
	}

}
