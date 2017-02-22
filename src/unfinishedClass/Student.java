package unfinishedClass;

import basicTool.RegistObjectWithPinyin;

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

	public Student(String index, String name, int gender, String grade, String mainCourse) {
		super(index, name);
		this.gender = gender;
		this.grade = grade;
		this.mainCourse = mainCourse;
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
}
