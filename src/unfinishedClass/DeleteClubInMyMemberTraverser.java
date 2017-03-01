package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import info.infoTool.AbstractTraverser;
import infoInterface.IInfo;
import infoSet.InfoSetSpecificByIndex;
import infoSet.SearchableInfoSet;

/**
 * 遍历对象Club中的MyMember，
 * 将这个club从member中全部删除，
 * member在club中的职位等信息用一个数组暂时存储。
 * @author 75309
 *
 */
public class DeleteClubInMyMemberTraverser extends DeleteTraverserForUpdateOperator {

	public DeleteClubInMyMemberTraverser(String clubIndex, int infoInClubNum) {
		super(clubIndex, infoInClubNum);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (check()){
			if (container instanceof MyMember){
				InfoSetSpecificByIndex clubInfoSet = ((MyMember) container)
														.getStudent()
														.getMyClubs();
				IInfo[] deleteResult = clubInfoSet
											.deleteIndex(deleteTargetIndex)
											.toInfoArray();
				if (deleteResult.length == 0){
					MyLogger.logError("DeleteClubInMyMemberTraverser暂时删除社团信息时，"
							+ "没有发现指定编号的社团，"
							+ "信息删除失败。");
					return 0;
				} else if (deleteResult.length > 1){
					MyLogger.logError("DeleteClubInMyMemberTraverser暂时删除社团信息时，"
							+ "发现重复编号的社团，"
							+ "信息删除失败，"
							+ "重新将这些重复编号的社团放回原来的位置。");
					for (IInfo memberInfo: deleteResult){
						clubInfoSet.insertInfo(memberInfo);
					}
				} else {
					Object myClub = deleteResult[0].getContainer();
					if (myClub instanceof MyClub){
						infosInClub[traversCount] = ((MyClub)myClub).getInfoInClub();
						traversCount ++;
						return 1;
					} else {
						MyLogger.logError("DeleteClubInMyMemberTraverser无法读取MyMember所参加的MyClub"
								+ "中的InfoInClub，clubInfoSet存储的类型不是MyClub类型。");
						return 0;
					}
				}
			}
		}
		return 0;
	}
	

}
