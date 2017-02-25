package testSpace;

import info.infoTool.AllTrueFilter;
import info.infoTool.GenderGetterForStudent;
import info.infoTool.GradeGetterForStudent;
import info.infoTool.IndexGetter;
import info.infoTool.MainCourseGetterForStudent;
import info.infoTool.NameGetter;
import info.infoTool.StudentTraverser;
import unfinishedClass.Club;
import unfinishedClass.ClubTraverser;
import unfinishedClass.Student;
import unfinishedClass.College;
import unfinishedClass.MyClubTraverser;
import unfinishedClass.MyMemberTraverser;

public class Test {
	public static Student[] stus;
	public static Club[] clubs;
	
	public static College college;
	
	public static StudentTraverser studentTraverser;
	public static ClubTraverser clubTraverser;
	
	public static NameGetter nameGetter;
	public static IndexGetter indexGetter;
	public static GenderGetterForStudent genderGetter;
	public static GradeGetterForStudent gradeGetter;
	public static MainCourseGetterForStudent mainCourseGetter;
	public static AllTrueFilter allTrueFilter;
	public static MyMemberTraverser myMemberTraverser;
	public static MyClubTraverser myClubTraverser;
	
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
		
		
		clubs = new Club[]{
				new Club("2012002", "吉他俱乐部", "2011-5-7"),
				new Club("2012003", "初心社", "2014-12-4"),
				new Club("2012004", "诗歌协会", "2008-7-24"),
				new Club("2012005", "动漫社", "2009-8-7"),
				new Club("2012008", "机器人俱乐部", "2015-4-5"),
				new Club("2012015", "足球会社", "2007-4-19"),
				new Club("201200111", "瑜伽馆", "2008-10-30"),
				new Club("20120021", "篮球社", "2012-12-21"),
				new Club("2012220", "3D工厂", "2016-4-16"),
							};
		
		college = new College();
		
		for (Club club: clubs){
			college.addClub(club);
		}
		
		for (Student stud: stus){
			college.addStudent(stud);
		}
		
		prepareVariables();
	}

	public static void prepareVariables() {
		studentTraverser = new StudentTraverser();
		clubTraverser = new ClubTraverser();
		
		nameGetter = new NameGetter();
		indexGetter = new IndexGetter();
		genderGetter = new GenderGetterForStudent();
		gradeGetter = new GradeGetterForStudent();
		mainCourseGetter = new MainCourseGetterForStudent();
		allTrueFilter = new AllTrueFilter();
		myMemberTraverser = new MyMemberTraverser();
		myClubTraverser = new MyClubTraverser();
	}
}
