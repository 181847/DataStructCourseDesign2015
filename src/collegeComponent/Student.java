package collegeComponent;

import basicInterface.IInfoSet;
import basicTool.RegistObjectWithPinyin;
import infoSet.InfoSetSpecificByIndex;

public class Student extends RegistObjectWithPinyin {
	/**
	 * 性别：
	 * 1 - 男；
	 * 2 - 女。
	 */
	public int gender;
	
	/**
	 * 年级。
	 */
	public String grade;
	
	/**
	 * 专业。
	 */
	public String mainCourse;
	
	/**
	 * 所有参加了的社团对象，
	 * 由于一个人能参加的社团并不会很多，
	 * 所以这个参加社团信息直接由一个简单的InfoSetSpecificByIndex存储。
	 */
	public InfoSetSpecificByIndex myClubsInfoSet;

	public Student(String index, String name, int gender, String grade, String mainCourse) {
		super(index, name);
		this.gender = gender;
		this.grade = grade;
		this.mainCourse = mainCourse;
		myClubsInfoSet = new InfoSetSpecificByIndex();
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMainCourse() {
		return mainCourse;
	}

	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}
	
	/**
	 * 获取参加社团的集合。
	 * @return
	 * 		当前学生参加的社团的集合。
	 */
	public InfoSetSpecificByIndex getMyClubs(){
		return myClubsInfoSet;
	}
}
