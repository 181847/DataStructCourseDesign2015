package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import info.infoTool.AbstractTraverser;

/**
 * 此类专门用于更新Club信息或者Student信息的时候，
 * 将Club或者Student重新插入相应的myClubs和myMembers中的。
 * @author 75309
 *
 */
public abstract class AddTraverserForUpdateOperator extends AbstractTraverser {
	public Object mainObject;
	public InfoInClub[] infosInClub;
	public int traversCount;
	
	public AddTraverserForUpdateOperator(Object mainObject, InfoInClub[] infosInClub){
		this.mainObject = mainObject;
		this.infosInClub = infosInClub;
	}

	@Override
	public abstract int dealWithContainer(Object container);

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

	protected boolean check(){
		if (mainObject == null || infosInClub[traversCount] == null){
			MyLogger.logError("AddTraverserForUpdateOperator中的mainObject为null，"
					+ "或者位于 " + traversCount + "号位的infosInClub为null");
			return false;
		}
		return true;
	}
}
