package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import info.infoTool.AbstractTraverser;

/**
 * 专门为Club.myMemberInfoSet和Student.myClubInfoSet遍历的抽象类，
 * 这个抽象类用来删除指定的社员或者参加社团对象，
 * 然后将所有被删除的社员的职位用数组存储起来，
 * 这个方法需要配合AddTraverserForUpdateOperator使用，
 * 来进行Club和Student的信息更新。
 */
public abstract class DeleteTraverserForUpdateOperator extends AbstractTraverser {
	public String deleteTargetIndex;
	public InfoInClub[] infosInClub;
	public int traversCount;
	
	public DeleteTraverserForUpdateOperator(String deleteTargetIndex, int infoInClubNum){
		this.deleteTargetIndex = deleteTargetIndex;
		infosInClub = new InfoInClub[infoInClubNum];
		traversCount = 0;
	}

	@Override
	public abstract int dealWithContainer(Object container);
	
	protected boolean check(){
		boolean checkResult = true;
		if (deleteTargetIndex == null){
			MyLogger.logError("DeleteTraverserForUpdateOperator中的deleteTargetIndex为null，"
					+ "无法删除原有信息。");
			checkResult = false; 
		} else if (deleteTargetIndex.isEmpty()){
			MyLogger.logError("UpdateTraverserForMyClubs中的deleteTargetIndex为空串，"
					+ "无法删除原有信息。");
			checkResult = false; 
		}
		return checkResult;
	}

	public String getDeleteTargetIndex() {
		return deleteTargetIndex;
	}

	public void setDeleteTargetIndex(String deleteTargetIndex) {
		this.deleteTargetIndex = deleteTargetIndex;
	}

	public InfoInClub[] getInfosInClub() {
		return infosInClub;
	}

	public void setInfosInClub(InfoInClub[] infosInClub) {
		this.infosInClub = infosInClub;
	}

	public int getTraversCount() {
		return traversCount;
	}

	public void setTraversCount(int traversCount) {
		this.traversCount = traversCount;
	}

}
