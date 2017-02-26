package unfinishedClass;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import collegeComponent.tool.DeregistTraverserForMyClub;
import info.infoTool.AllTrueFilter;
import infoSet.InfoSetSpecificByIndex;
import infoSet.SearchableInfoSet;
import operator.DeleteOperator;

public class ClubDeleteOperator extends DeleteOperator {

	public ClubDeleteOperator(College college) {
		super(college);
	}
	
	public ClubDeleteOperator(College college, String clubIndex){
		this(college);
		this.targetIndex = clubIndex;
	}

	/**
	 * @return
	 * 		如果目标序号不符合规范，
	 * 	就返回0；
	 * 		如果不存在指定编号的社团，
	 * 	返回-1；
	 * 		成功返回1。
	 */
	@Override
	public int operate() {
		if (check()){
			Club club = college.getClub(targetIndex);
			if (club == null){
				MyLogger.logError("ClubDeleteOperator要删除的对象不存在，"
						+ "请检查：" + targetIndex);
				return -1;
			}
			SearchableInfoSet myMembers = club.getMyMembers();
			
			DeregistTraverserForMyMember dtfmm = 
					new DeregistTraverserForMyMember(
							getCollege(), getTargetIndex());
			
			myMembers.traverseInfo(dtfmm, new AllTrueFilter());
			college.getClubInfoSet().deleteIndex(targetIndex);
			return 1;
		}
		return 0;
	}

}
