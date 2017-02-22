package testSpace;

import unfinishedClass.Student;

public class Test {
	public static Student[] stus;
	
	public static void prepare(){
		stus = new Student[]{
				new Student("2002015", "张光和", 1, "大一", "物流工程"), 
				new Student("2002002", "张植强", 1, "大一", "动力工程"), 
				new Student("2002003", "槐心", 1, "大二", "智能理论"), 
				new Student("2002004", "王小勇", 1, "大一", "材料科学"), 
				new Student("2002005", "王历历", 1, "大三", "文化与法制"), 
				new Student("2002006", "吴孟达", 1, "大三", "计算机"), 
				new Student("2002007", "金嫣红", 2, "大二", "计算机"), 
				new Student("2002008", "刘长艳", 2, "大一", "数学理论"), 
				new Student("2002009", "许强", 1, "大三", "机械理论"), 
				new Student("20020010", "杨小鹃", 2, "大一", "文化与法制"), 
				new Student("20020011", "岳不群", 1, "大一", "体育")};
	}
}
