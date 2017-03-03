package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.MyClub;
import info.infoTool.AbstractTraverser;
import infoInterface.IInfo;
import infoSet.SearchableInfoSet;

/**
 * 暂时废除这个类，这个类原本只用于StudentUpdateOperator中，
 * 现在StudentUpdateOperator中由DeleteMemberInMyClubTraverser和
 * AddMemberInMyClubTraverser配合使用来达到原来的效果。
 * 废除原因：
 * 更新学生信息需要同样更新这个学生参加的所有社团，
 * 社团中按照学生的相关信息生成对应的搜索索引，
 * 但是先前更新学生信息是由Operator的外部实施，
 * 更新社团中的搜索索引的时候是先将这个社员从myMembersInfoSet中删除，
 * 然后立马插入myMembersInfoSet中，
 * 希望通过重新插入的方法来更新搜索索引，
 * 但是在myMembersInfoSet中删除的时候会有问题，
 * 因为删除的时候需要删除循环链表中的节点以及搜索目录中的记录，
 * 删除循环链表中的Student对象需要通过registArea 和 老的学生的序号获取这个的学生对象，
 * 由于之前在外部事先保存了一个Student修改之前的学号，
 * 所以 registArea（以序号为唯一区分的区域，用于检查序号冲突，保证没有重复序号的对象）
 * 还是能够找到这个被修改的Student对象，
 * 然而在名字搜索目录……等其他搜索目录中就没有这么幸运了，
 * 在搜索目录中删除对象的前提是这个对象的信息没有被修改过，
 * 因为搜索目录（比如说名字搜索目录）为了快速删除对象，
 * 需要通过这个对象的指定信息（比如说Student对象的名字）来定位索引的位置，
 * 然后将这个位置的指定序号的对象删除，
 * 但是我们之前在外部可能修改了对象的名字，这个时候由于没有保存先前的名字，
 * 搜索目录获得的索引的位置是一个不存在的位置，导致原来位置的索引无法删除,
 * 于是原来的搜索索引就会永久存在，无法删除，
 * 因为没有修改之前的信息，也就无法定位索引的位置。
 */
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
				}
				return 1;
				/**
				 * else {
					if (1 != memberInfoSet.insertInfo(deleteResult[0])){
						MyLogger.logError("UpdateTraverserForMyClubs更新社员信息时，"
								+ "插入信息失败，"
								+ "请勿保存文件。");
						return 0;
					}
					return 1;
				}
				*/
				
				
				
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
