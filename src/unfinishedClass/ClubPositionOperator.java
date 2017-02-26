package unfinishedClass;

import basicTool.MyLogger;
import info.InfoWithContainer;
import infoInterface.IInfo;
import infoSet.SearchableInfoSet;

public class ClubPositionOperator extends RegisterOperator {

	public ClubPositionOperator(College college) {
		super(college);
	}
	
	/**
	 * @return
	 * 		如果学号，社团编号，职位不符合要求，
	 * 	返回0；
	 * 		如果不存在对应的社团，
	 * 	返回-1；
	 * 		如果指定学号的学生并没有在这个社团中有记录，
	 * 		或者有几个相同学号的学生参加了这个社团，
	 * 	返回-2；
	 * 		如果从社团成员集合中获取的对象不是MyMember类型，
	 * 	返回-3；
	 * 		如果成功，返回1。
	 */
	@Override
	public int operate(){
		if (check()){
			Club club = college.getClub(clubIndex);
			if (club == null){
				MyLogger.logError("向社团注册社员的时候，"
						+ "未能发现指定编号的社团，"
						+ "注册失败，请检查：");
				showLogger();
				return -1;
			}
			
			SearchableInfoSet myMembers = club.getMyMembers();
			IInfo[] members = myMembers.getIndex(studentIndex).toInfoArray();
			if (members.length == 0){
				MyLogger.logError("ClubPositionOperator发现社团中没有指定学号的社团成员，"
						+ "设置职位失败，请检查：");
				showLogger();
				return -2;
			} else if (members.length > 1){
				MyLogger.logError("ClubPositionOperator发现社团存在重复学号的社团成员，"
						+ "设置职位失败，请检查：");
				showLogger();
				return -2;
			}
			
			Object theMember = members[0].getContainer();
			if (theMember instanceof MyMember){
				((MyMember) theMember).setPosition(position);
				myMembers.deleteIndex(studentIndex);
				myMembers.insertInfo(new InfoWithContainer(theMember));
			} else {
				MyLogger.logError("ClubPositionOperator从社团中获取指定学号的社团成员的时候，"
						+ "发现对象类型不是MyMember，"
						+ "设置职位失败，请检查：");
				showLogger();
				return -3;
			}
			return 1;
		}//if check
		return 0;
	}

}
