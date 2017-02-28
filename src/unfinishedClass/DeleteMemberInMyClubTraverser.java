package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import info.infoTool.AbstractGetter;
import info.infoTool.AbstractTraverser;
import infoInterface.IInfo;
import infoSet.SearchableInfoSet;

public class DeleteMemberInMyClubTraverser extends AbstractTraverser {
	public String originalIndex;
	public InfoInClub[] infosInClub;
	public int clubCount;
	
	public DeleteMemberInMyClubTraverser(String originalIndex, int infosInClubNum){
		this.originalIndex = originalIndex;
		infosInClub = new InfoInClub[infosInClubNum];
		clubCount = 0;
	}
	
	

	@Override
	public int dealWithContainer(Object container) {
		if (check()){
			if (container instanceof MyClub){
				SearchableInfoSet memberInfoSet = ((MyClub) container)
														.getClub()
														.getMyMembers();
				IInfo[] deleteResult = memberInfoSet
											.deleteIndex(originalIndex)
											.toInfoArray();
				if (deleteResult.length == 0){
					MyLogger.logError("UpdateTraverserForMyClubs更新社员信息时，"
							+ "没有发现指定学号的学生，"
							+ "信息更新失败。");
					return 0;
				} else if (deleteResult.length > 1){
					MyLogger.logError("UpdateTraverserForMyClubs更新社员信息时，"
							+ "发现重复学号的学生，"
							+ "信息更新失败。");
					for (IInfo memberInfo: deleteResult){
						memberInfoSet.insertInfo(memberInfo);
					}
				} else {
					Object myMember = deleteResult[0].getContainer();
					if (myMember instanceof MyMember){
						infosInClub[clubCount] = ((MyMember)myMember).getInfoInClub();
						clubCount ++;
						return 1;
					} else {
						MyLogger.logError("DeleteMemberInMyClubTraverser无法读取MyClub的MyMember中的InfoInClub。");
						return 0;
					}
				}
			}
		}
		return 0;
	}

	protected boolean check(){
		boolean checkResult = true;
		if (originalIndex == null){
			MyLogger.logError("UpdateTraverserForMyClubs中的originalIndex为null，无法执行社员信息更新。");
			checkResult = false; 
		} else if (originalIndex.isEmpty()){
			MyLogger.logError("UpdateTraverserForMyClubs中的originalIndex为空串，无法执行社员信息更新。");
			checkResult = false; 
		}
		return checkResult;
	}
	
	public InfoInClub[] getInfosInClub() {
		return infosInClub;
	}

	public void setInfosInClub(InfoInClub[] infosInClub) {
		this.infosInClub = infosInClub;
	}

	public int getClubCount() {
		return clubCount;
	}

	public void setClubCount(int clubCount) {
		this.clubCount = clubCount;
	}
}
