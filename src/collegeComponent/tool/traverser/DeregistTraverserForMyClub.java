package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.MyClub;

/**
 * 让Student对象退出所有参加的社团，
 * 遍历的对象是Student内部的myClubsInfoSet集合，
 * 将这个学生参加的所有社团注销，
 * 这个Traverser一般用于StudentDeleteOperator中删除学生对象。
 */
public class DeregistTraverserForMyClub extends DeregistTraverser {
	
	public DeregistTraverserForMyClub(College college, String studentIndex){
		super(college);
		setStudentIndex(studentIndex);
	}
	
	public DeregistTraverserForMyClub(College college){
		super(college);
	}
	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof MyClub)){
			MyLogger.log("DeregistTraverserForMyClub读取info的container的类型不是MyClub，无法读取信息。");
			return 0;
		}
		dro.setClubIndex( ((MyClub) container).getIndex() );
		if (dro.operate() != 1){
			MyLogger.logError("DeregistTraverserForMyClub注销社员失败。");
			return 0;
		}
		return 1;
	}
}
