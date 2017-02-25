package unfinishedClass;

import basicInterface.IIndexHolder;
import basicInterface.INameHolder;
import basicInterface.IPinyinHolder;
import basicInterface.IPositionInClub;

public class MyMember implements IIndexHolder, INameHolder, IPinyinHolder, IPositionInClub {
	public InfoInClub infoInClub;
	public Student student;

	@Override
	public void setPosition(String position) {
		infoInClub = new InfoInClub(position);
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

}
