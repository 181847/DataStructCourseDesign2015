package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.MyMember;

/**
 * 注销社团中所有社员，
 * 遍历的对象是Club内部的myMembersInfoSet集合，
 * 将这个社团的所有社员从这个社团中注销，
 * 这个Traverser一般用于ClubDeleteOperator中删除学生对象。
 */
public class DeregistTraverserForMyMember extends DeregistTraverser {

	public DeregistTraverserForMyMember(College college, String clubIndex) {
		super(college);
		setClubIndex(clubIndex);
	}
	
	public DeregistTraverserForMyMember(College college) {
		super(college);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof MyMember)){
			MyLogger.log("DeregistTraverserForMyMember读取info的container的类型不是MyMember，无法读取信息。");
			return 0;
		}
		dro.setStudentIndex( ((MyMember) container).getIndex() );
		if (dro.operate() != 1){
			MyLogger.logError("DeregistTraverserForMyClub注销社团中成员失败。");
			return 0;
		}
		return 1;
	}

}
