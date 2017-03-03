package collegeComponent;

import basicInterface.IPositionHolder;

/**
 * 本类实现IPositionHolder接口，
 * 专门用于记录一个社员在社团中的职位信息，
 * 目前这个信息只有职位信息，
 * 社团要记录社员的职位信息，
 * 学生要记录他在各个社团中的职位信息，
 * 这里通过一个InfoInClub对象来将社团和学生对象的职位信息建立联系，
 * 社团和学生对象内部存储InfoInClub时，
 * 是共用同一个InfoInClub的实例对象的。
 */
public class InfoInClub implements IPositionHolder {
	/**
	 * 职位信息。
	 */
	public String position;
	
	/**
	 * @param position
	 * 		职位信息。
	 */
	public InfoInClub(String position){
		this.position = position;
	}

	/**
	 * @param position
	 * 		职位信息。
	 */
	@Override
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return
	 * 		职位信息。
	 */
	@Override
	public String getPosition() {
		return position;
	}

}
