package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import collegeComponent.Student;
import info.InfoWithContainer;
import info.infoTool.AbstractTraverser;
import infoSet.SearchableInfoSet;

public class AddMemberInMyClubTraverser extends AbstractTraverser {
	public Student student;
	public InfoInClub[] infosInClub;
	public int clubCount;
	
	public AddMemberInMyClubTraverser(Student student, InfoInClub[] infosInClub){
		this.student = student;
		this.infosInClub = infosInClub;
	}
	
	@Override
	public int dealWithContainer(Object container) {
		if (check()){
			if (container instanceof MyClub){
				SearchableInfoSet memberInfoSet = ((MyClub) container)
														.getClub()
														.getMyMembers();
				memberInfoSet.insertInfo(
						new InfoWithContainer(
								new MyMember(infosInClub[clubCount], student)));
				clubCount++;
				return 1;
			}
		}
		return 0;
	}
	
	protected boolean check(){
		if (student == null || infosInClub[clubCount] == null){
			MyLogger.logError("AddMemberInMyClubTraverser中的student为null，"
					+ "或者位于 " + clubCount + "号位的infosInClub为null");
			return false;
		}
		return true;
	}

}
