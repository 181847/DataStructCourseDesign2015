package testSpace;

import student.Student;

public class Test {
	public static Student[] stus;
	
	public static void prepare(){
		stus = new Student[]{
				new Student("abc", "1"), 
				new Student("acd", "2"), 
				new Student("abe", "3")};
	}
}
