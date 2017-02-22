package testSpace;

import basicTool.MyLogger;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.IndexGetter;
import info.infoTool.LimitedStudentTraverser;
import info.infoTool.NameGetter;
import infoSet.InfoSearchTree;
import unfinishedClass.Student;

public class TestLimitedTraverser extends Test {

	public static void main(String[] args) {
		prepare();
		
		InfoSearchTree ist = new InfoSearchTree('\0');
		for (Student s: stus){
			ist.insertInfo(new InfoWithContainer(s), 
							new IndexGetter());
		}
		
		ist.limitedTraverseInfo(new LimitedStudentTraverser(3), new AllTrueFilter());
		
		MyLogger.seperate();
		ist.deleteSpecific("20020011".toCharArray(), 0, "岳不群", new NameGetter(), new AllTrueFilter());
		ist.limitedTraverseInfo(new LimitedStudentTraverser(45), new AllTrueFilter());
	}

}
