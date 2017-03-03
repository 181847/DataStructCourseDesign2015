package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import infoInterface.IInfo;
import infoSet.SearchableInfoSet;
import unfinishedClass.DeleteTraverserForUpdateOperator;

/**
 * 此类专门用于更新Student的信息的时候，
 * 从新对这个Student所参加的社团中的社员信息，
 * 删除操作，并且用InfoInClub数组存储所有参加的社团中的职位信息。
 * 这个遍历者必须配合AddMemberInMyClubTraverser使用。
 * 这个遍历者主要用于StudentUpdateOperator当中。
 * @author 75309
 *
 */
public class DeleteMemberInMyClubTraverser extends DeleteTraverserForUpdateOperator {
	
	public DeleteMemberInMyClubTraverser(String memberIndex, int infoInClubNum){
		super(memberIndex, infoInClubNum);
	}
	
	

	@Override
	public int dealWithContainer(Object container) {
		if (check()){
			if (container instanceof MyClub){
				SearchableInfoSet memberInfoSet = ((MyClub) container)
														.getClub()
														.getMyMembers();
				IInfo[] deleteResult = memberInfoSet
											.deleteIndex(deleteTargetIndex)
											.toInfoArray();
				if (deleteResult.length == 0){
					MyLogger.logError("DeleteMemberInMyClubTraverser暂时删除社员信息时，"
							+ "没有发现指定学号的学生，"
							+ "信息删除失败。");
					return 0;
				} else if (deleteResult.length > 1){
					MyLogger.logError("UpdateTraverserForMyClubs暂时删除社员信息时，"
							+ "发现重复学号的学生，"
							+ "信息删除失败。");
					for (IInfo memberInfo: deleteResult){
						memberInfoSet.insertInfo(memberInfo);
					}
				} else {
					Object myMember = deleteResult[0].getContainer();
					if (myMember instanceof MyMember){
						infosInClub[traversCount] = ((MyMember)myMember).getInfoInClub();
						traversCount ++;
						return 1;
					} else {
						MyLogger.logError("DeleteMemberInMyClubTraverser无法读取MyClub的MyMember"
								+ "中的InfoInClub，container不是MyMember类型。");
						return 0;
					}
				}
			}
		}
		return 0;
	}

	
}
