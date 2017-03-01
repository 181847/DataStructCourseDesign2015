package collegeComponent.tool.traverser;

import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import collegeComponent.Student;
import info.InfoWithContainer;
import infoSet.SearchableInfoSet;
import unfinishedClass.AddTraverserForUpdateOperator;

public class AddMemberInMyClubTraverser extends AddTraverserForUpdateOperator {
	
	public AddMemberInMyClubTraverser(Student student, InfoInClub[] infosInClub){
		super(student, infosInClub);
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
								new MyMember(infosInClub[traversCount], (Student)mainObject)));
				traversCount++;
				return 1;
			}
		}
		return 0;
	}

}
