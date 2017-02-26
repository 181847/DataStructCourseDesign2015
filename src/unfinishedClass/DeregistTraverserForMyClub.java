package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.AbstractTraverser;

public class DeregistTraverserForMyClub extends AbstractTraverser {
	DeregisterOperator dro;
	
	public DeregistTraverserForMyClub(College college, String studentIndex){
		dro = new DeregisterOperator(college);
		dro.setStudentIndex(studentIndex);
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

	public void setStudentIndex(String studentIndex){
		dro.setStudentIndex(studentIndex);
	}
}
