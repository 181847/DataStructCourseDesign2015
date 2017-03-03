package collegeComponent;

import basicTool.RegistObjectWithPinyin;
import infoSet.InfoSetSpecificByIndex;

/**
 * 学生对象类型，父类是RegisObjectWithPinyin，
 * 内部用一个int型来存储性别0-男、1-女、2-其他，
 * 用字符串类型存储年级和专业，
 * 用InfoSetSpecificByIndex来存储这个人参加的所有社团，
 * 这种类型的InfoSet只提供了一个检查序号冲突的方法，
 * 没有搜索目录，
 * 这是因为一个人能够参加的社团数量一般不会很多，
 * 所以不需要创建搜索目录进行大规模的搜索，
 * 一眼就能看到全部的社团。
 */
public class Student extends RegistObjectWithPinyin {
	/**
	 * 性别：
	 * 0 - 男；
	 * 1 - 女；
	 * 2 - ?.
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
	 * 这个人参加的所有社团，
	 * 这种类型的InfoSet（InfoSetSpecificByIndex）只提供了一个检查序号冲突的方法，
	 * 没有搜索目录，
	 * 这是因为一个人能够参加的社团数量一般不会很多，
	 * 所以不需要创建搜索目录进行大规模的搜索，
	 * 一眼就能看到全部的社团。
	 */
	public InfoSetSpecificByIndex myClubsInfoSet;

	/**
	 * @param index
	 * 		学号。
	 * @param name
	 * 		名字。
	 * @param gender
	 * 		性别,
	 * 		0-男、1-女、2-其他。
	 * @param grade
	 * 		年级。
	 * @param mainCourse
	 * 		专业。
	 */
	public Student(String index, String name, int gender, String grade, String mainCourse) {
		super(index, name);
		this.gender = gender;
		this.grade = grade;
		this.mainCourse = mainCourse;
		myClubsInfoSet = new InfoSetSpecificByIndex();
	}

	/**
	 * 获取性别，
	 * @return
	 * 		0-男、1-女、2-其他。
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * 设置性别。
	 * @param gender
	 * 		0-男、1-女、2-其他。
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return
	 * 		年级。
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 * 		年级。
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return
	 * 		专业。
	 */
	public String getMainCourse() {
		return mainCourse;
	}

	/**
	 * @param mainCourse
	 * 		专业。
	 */
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
