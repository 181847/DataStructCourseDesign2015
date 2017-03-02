package unfinishedClass;

import collegeComponent.Club;
import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import info.InfoWithContainer;
import infoSet.InfoSetSpecificByIndex;

public class AddClubInMyMemberTraverser extends AddTraverserForUpdateOperator {

	public AddClubInMyMemberTraverser(Club club, InfoInClub[] infosInClub) {
		super(club, infosInClub);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (check()){
			if (container instanceof MyMember){
				InfoSetSpecificByIndex clubInfoSet = ((MyMember) container)
														.getStudent()
														.getMyClubs();
				clubInfoSet.insertInfo(
						new InfoWithContainer(
								new MyClub(infosInClub[traversCount], (Club)mainObject)));
				traversCount++;
				return 1;
			}
		}
		return 0;
	}

}
