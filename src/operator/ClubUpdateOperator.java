package operator;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;

public class ClubUpdateOperator extends UpdateOperator {
	public Club club;
	public String newIndex = null;
	public String newName = null;
	public String newDate = null;

	public ClubUpdateOperator(College college, Club club) {
		super(college);
		setClub(club);
	}
	
	public ClubUpdateOperator(College college){
		super(college);
	}

	/**
	 * 将内部的社团对象发生的信息更新反馈到college中，
	 * 并不会反馈到学生对象中，因为学生参加的社团数量比较少，
	 * 只是利用带有序号检查功能的双重循环链表的形式进行存储，
	 * 不会有搜索目录，也就无需进行信息的更新。
	 */
	@Override
	public int operate() {
		if (club == null){
			MyLogger.logError("ClubUpdateOperator设置的社团对象为null，信息更新失败。");
			return 0;
		}
		if (originalIndex == null || originalIndex.isEmpty()){
			MyLogger.logError("ClubUpdateOperator中的社团编号信息为null或者空串，信息更新失败。");
			return 0;
		}
		college.deleteClub(originalIndex);
		updateClubInfo();
		college.addClub(club);
		return 0;
	}
	
	public void setClub(Club club) {
		if (club == null){
			MyLogger.logError("ClubUpdateOperator设置的社团对象为null，设置失败。");
			return;
		}
		this.originalIndex = club.getIndex();
		this.club = club;
		newIndex = null;
		newName = null;
		newDate = club.getDate();
	}
	
	public Club getClub(){
		return club;
	}
	
	public void setNewIndex(String index){
		newIndex = index;
	}
	
	public void setNewName(String name){
		newName = name;
	}
	
	public void setNewDate(String date){
		newDate = date;
	}
	
	private void updateClubInfo(){
		if (newIndex != null){
			club.setIndex(newIndex);
		}
		if (newName != null){
			club.setName(newName);
		}
		club.setDate(newDate);
	}
}
