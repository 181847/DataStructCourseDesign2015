package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import info.infoTool.AbstractTraverser;

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
