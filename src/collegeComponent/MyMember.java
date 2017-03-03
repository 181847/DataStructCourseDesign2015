package collegeComponent;

import basicInterface.IIndexHolder;
import basicInterface.INameHolder;
import basicInterface.IPinyinHolder;
import basicInterface.IPositionHolder;

/**
 * 存储于Club内部的社员对象，
 * 每个MyMember对象由一个InfoInClub对象
 * 和一个Student对象组成。
 */
public class MyMember implements IIndexHolder, INameHolder, IPinyinHolder, IPositionHolder {
	/**
	 * 在社团中的职位。
	 */
	public InfoInClub infoInClub;
	
	/**
	 * 社员的学生对象。
	 */
	public Student student;
	
	public MyMember(InfoInClub infoInClub, Student student){
		this.infoInClub = infoInClub;
		this.student = student;
	}

	/**
	 * 设置职位，
	 * 这个方法会调用内部的infoInClub的setPosition()方法。
	 * @param position
	 * 		职位信息。
	 */
	@Override
	public void setPosition(String position) {
		infoInClub.setPosition(position);;
	}
	
	/**
	 * 获取职位。
	 */
	@Override
	public String getPosition() {
		return infoInClub.getPosition();
	}

	/**
	 * 获取学生的名字拼音。
	 */
	@Override
	public String getPinyin() {
		if (student == null){
			return "";
		}
		return student.getPinyin();
	}

	/**
	 * 获取学生的名字短拼音。
	 */
	@Override
	public String getShortPinyin() {
		if (student == null){
			return "";
		}
		return student.getShortPinyin();
	}
	
	/**
	 * 获取学生的名字。
	 */
	@Override
	public String getName() {
		if (student == null){
			return "";
		}
		return student.getName();
	}
	
	/**
	 * 获取学生的学号。
	 */
	@Override
	public String getIndex() {
		if (student == null){
			return "";
		}
		return student.getIndex();
	}
	
	/**
	 * 空方法，无任何作用。
	 */
	@Override
	public void setName(String name) {
		//Empty Body
	}

	/**
	 * 空方法，无任何作用。
	 */
	@Override
	public void setPinyin(String pinyin) {
		//Empty Body
	}

	/**
	 * 空方法，无任何作用。
	 */
	@Override
	public void setShortPinyin(String shortPinyin) {
		//Empty Body
	}

	/**
	 * 空方法，无任何作用。
	 */
	@Override
	public void setIndex(String index) {
		//Empty Body
	}

	/**
	 * 空方法，无任何作用。
	 */
	public InfoInClub getInfoInClub() {
		return infoInClub;
	}

	/**
	 * 空方法，无任何作用。
	 */
	public void setInfoInClub(InfoInClub infoInClub) {
		this.infoInClub = infoInClub;
	}

	/**
	 * 空方法，无任何作用。
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 空方法，无任何作用。
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
}
