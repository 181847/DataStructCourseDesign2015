package unfinishedClass;

import collegeComponent.Club;
import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import info.InfoWithContainer;
import infoSet.InfoSetSpecificByIndex;

/**
 * 这个遍历者的遍历对象是Club.myMembersInfoSet对象，
 * 用来传入这个club对象和一连串的infosInClub，
 * 将这个club对象重新与每个infosInClub重新结合为MyClub对象，
 * 然后插入每个myMember.student.myClubInfoSet，
 * 通过这个Traverser来更新参加这个社团的社员内部的社团序号信息。
 */
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
