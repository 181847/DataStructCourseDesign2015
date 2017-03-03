package collegeComponent.tool.traverser;

import collegeComponent.InfoInClub;
import collegeComponent.MyClub;
import collegeComponent.MyMember;
import collegeComponent.Student;
import info.InfoWithContainer;
import infoSet.SearchableInfoSet;

/**
 * 遍历对象：Student内部的myClubs集合（表示该学生参加的所有社团）。
 * 将student和infosInClub数组中的每个单例结合成为一个MyMember对象，
 * 然后插入一个Club对象中，
 * 这个遍历者必须配合DeleteMemberInMyClubTraverser使用。
 * 这个遍历者主要用于StudentUpdateOperator。
 * @author 75309
 *
 */
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
