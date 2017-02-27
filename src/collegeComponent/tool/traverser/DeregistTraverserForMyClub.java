package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.MyClub;

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
