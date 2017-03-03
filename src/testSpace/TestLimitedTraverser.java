package testSpace;

import basicTool.MyLogger;
import collegeComponent.Student;
import collegeComponent.tool.getter.IndexGetter;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.traverser.LimitedStudentTraverser;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import infoSet.InfoSearchTree;

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
		ist.specialDelete("20020011".toCharArray(), 0, "岳不群", new NameGetter(), new AllTrueFilter());
		ist.limitedTraverseInfo(new LimitedStudentTraverser(45), new AllTrueFilter());
	}

}
