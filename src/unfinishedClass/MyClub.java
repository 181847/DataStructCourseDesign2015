package unfinishedClass;

import basicInterface.IIndexHolder;
import basicInterface.INameHolder;
import basicInterface.IPinyinHolder;
import basicInterface.IPositionHolder;

public class MyClub implements IIndexHolder, INameHolder, IPinyinHolder, IPositionHolder{
	/**
	 * 在社团中的职位。
	 */
	public InfoInClub infoInClub;
	
	/**
	 * 参加的社团。
	 */
	public Club club;
	
	public MyClub(String position, Club club){
		infoInClub = new InfoInClub(position);
		this.club = club;
	}
	
	public void setPosition(String position){
		infoInClub.setPosition(position);
	}
	
	public String getPosition(){
		return infoInClub.getPosition();
	}
	
	public void setClub(Club club){
		this.club = club;
	}
	
	public Club getClub(){
		return club;
	}

	/**
	 * 获取成员club的拼音名字。
	 * @return
	 * 		如果club为null的话，
	 * 		返回值为空串；
	 * 		如果club不为空，
	 * 		就返回club的拼音名字。
	 */
	@Override
	public String getPinyin() {
		if (club == null){
			return "";
		}
		return club.getPinyin();
	}

	/**
	 * 获取成员club的短拼音名字。
	 * @return
	 * 		如果club为null的话，
	 * 		返回值为空串；
	 * 		如果club不为空，
	 * 		就返回club的短拼音名字。
	 */
	@Override
	public String getShortPinyin() {
		if (club == null){
			return "";
		}
		return club.getShortPinyin();
	}
	
	/**
	 * 获取成员club的名字。
	 * @return
	 * 		如果club为null的话，
	 * 		返回值为空串；
	 * 		如果club不为空，
	 * 		就返回club的名字。
	 */
	@Override
	public String getName() {
		if (club == null){
			return "";
		}
		return club.getName();
	}

	/**
	 * 获取成员club的序号。
	 * @return
	 * 		如果club为null的话，
	 * 		返回值为空串；
	 * 		如果club不为空，
	 * 		就返回club的序号。
	 */
	@Override
	public String getIndex() {
		if (club == null){
			return "";
		}
		return club.getIndex();
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
	public void setName(String name) {
		//Empty Body
	}

	/**
	 * 空方法，无任何作用。
	 */
	@Override
	public void setIndex(String index) {
		//Empty Body
	}
}
