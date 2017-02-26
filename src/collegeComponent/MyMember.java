package collegeComponent;

import basicInterface.IIndexHolder;
import basicInterface.INameHolder;
import basicInterface.IPinyinHolder;
import basicInterface.IPositionHolder;
import collegeComponent.tool.InfoInClub;

public class MyMember implements IIndexHolder, INameHolder, IPinyinHolder, IPositionHolder {
	public InfoInClub infoInClub;
	public Student student;
	
	public MyMember(InfoInClub infoInClub, Student student){
		this.infoInClub = infoInClub;
		this.student = student;
	}

	@Override
	public void setPosition(String position) {
		infoInClub.setPosition(position);;
	}

	@Override
	public String getPosition() {
		return infoInClub.getPosition();
	}

	@Override
	public String getPinyin() {
		if (student == null){
			return "";
		}
		return student.getPinyin();
	}

	@Override
	public String getShortPinyin() {
		if (student == null){
			return "";
		}
		return student.getShortPinyin();
	}
	

	@Override
	public String getName() {
		if (student == null){
			return "";
		}
		return student.getName();
	}
	
	@Override
	public String getIndex() {
		if (student == null){
			return "";
		}
		return student.getIndex();
	}
	
	@Override
	public void setName(String name) {
		//Empty Body
	}
	
	@Override
	public void setPinyin(String pinyin) {
		//Empty Body
	}
	
	@Override
	public void setShortPinyin(String shortPinyin) {
		//Empty Body
	}
	
	@Override
	public void setIndex(String index) {
		//Empty Body
	}

	public InfoInClub getInfoInClub() {
		return infoInClub;
	}

	public void setInfoInClub(InfoInClub infoInClub) {
		this.infoInClub = infoInClub;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
