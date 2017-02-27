package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.MyClub;
import info.infoTool.AbstractTraverser;
import infoInterface.IInfo;
import infoSet.SearchableInfoSet;

public class UpdateTraverserForMyClubs extends AbstractTraverser {
	public String originalIndex;
	
	public UpdateTraverserForMyClubs(String originalIndex){
		this.originalIndex = originalIndex;
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
					if (1 != memberInfoSet.insertInfo(deleteResult[0])){
						MyLogger.logError("UpdateTraverserForMyClubs更新社员信息时，"
								+ "插入信息失败，"
								+ "请勿保存文件。");
						return 0;
					}
					return 1;
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

}
