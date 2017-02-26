package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.MyMember;

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
