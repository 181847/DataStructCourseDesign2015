package testSpace;

import info.Info;
import unfinishedClass.AllTrueFilter;
import unfinishedClass.InfoSet;
import unfinishedClass.Student;
import unfinishedClass.StudentTraverser;

public class testInfoSet {
	public static void main(String[] args){
		Student[] stus = new Student[]{new Student("a", "1"), 
				new Student("b", "2"), 
				new Student("c", "3")};
		
		InfoSet iset = new InfoSet();
		for (Student s: stus){
			iset.insertInfo(new Info(s));
		}
		
		iset.traverse(new StudentTraverser(), new AllTrueFilter());
	}
}
