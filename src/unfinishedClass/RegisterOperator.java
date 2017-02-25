package unfinishedClass;

import basicTool.MyLogger;
import info.InfoWithContainer;
import infoSet.InfoSetSpecificByIndex;
import infoSet.SearchableInfoSet;

public class RegisterOperator extends CollegeOperator {
	public String studentIndex;
	public String clubIndex;
	public String position;

	public RegisterOperator(College college) {
		super(college);
	}

	/**
	 * @return
	 * 		如果已经有相同学号的社员参加了这个社团，
	 * 		或者这个学生已经参加的相同编号的这个社团，
	 * 		返回-1；
	 * 		如果插入操作发生错误返回0；
	 * 		如果成功，返回1。
	 */
	@Override
	public int operate() {
		if (check()){
			int operateResult = 1;
			
			Student student = college.getStudent(studentIndex);
			Club club = college.getClub(clubIndex);
			InfoSetSpecificByIndex myClubs = student.getMyClubs();
			SearchableInfoSet myMembers = club.getMyMembers();
			
			if ( myMembers
					.haveIndex(studentIndex.toCharArray()) ){
				MyLogger.logError("向社团注册社员的时候，"
						+ "发现学号重复的社员，"
						+ "注册失败，请检查："
						+ "\nstudentIndex: " + studentIndex
						+ "\nclubIndex: " + clubIndex
						+ "\nposition:" + position);
				operateResult = -1;
			}//if
			
			if (myClubs
					.haveIndex(clubIndex.toCharArray()) ){
				MyLogger.logError("向学生添加社团的时候，"
						+ "发现编号重复的社团，"
						+ "注册失败，请检查："
						+ "\nstudentIndex: " + studentIndex
						+ "\nclubIndex: " + clubIndex
						+ "\nposition:" + position);
				operateResult = -1;
			}//if
			
			if (operateResult != 1){
				return -1;
			}
			
			InfoInClub infoInClub = new InfoInClub(position);
			if (1 != 
					myMembers.insertInfo( 
							new InfoWithContainer(
									new MyMember(infoInClub, student)))){
				MyLogger.logError("向社团插入成员信息是发生错误，请检查："
						+ "\nstudentIndex: " + studentIndex
						+ "\nclubIndex: " + clubIndex
						+ "\nposition:" + position);
				return 0;
			}//if
			
			if (1 != 
					myClubs.insertInfo( 
							new InfoWithContainer(
									new MyClub(infoInClub, club)))){
				MyLogger.logError("向学生插入社团信息时发生错误，请检查："
						+ "\nstudentIndex: " + studentIndex
						+ "\nclubIndex: " + clubIndex
						+ "\nposition:" + position);
				return 0;
			}//if
		}//if check
		return 1;
	}//operate()
	
	/**
	 * 这个方法用来检查内部的成员变量字符串是否正确。
	 * @return
	 * 		如果可以为社团注册学生，就返回true；
	 * 		如果有null或者空串，就返回false。
	 */
	public boolean check(){
		boolean checkResult = true;
		
		if (studentIndex == null){
			MyLogger.logError("RegisterOperator中studentIndex字符串为null，无法正常注册。");
			checkResult = false;
		} else if (studentIndex.isEmpty()){
			MyLogger.logError("RegisterOperator中studentIndex字符串为空串，无法正常注册。");
			checkResult = false;
		}
		
		if (clubIndex == null){
			MyLogger.logError("RegisterOperator中clubIndex字符串为null，无法正常注册。");
			checkResult = false;
		} else if (clubIndex.isEmpty()){
			MyLogger.logError("RegisterOperator中clubIndex字符串为空串，无法正常注册。");
			checkResult = false;
		}
		
		if (position == null){
			MyLogger.logError("RegisterOperator中表示社员职位的字符串为null，无法正常注册。");
			checkResult = false;
		} else if (position.isEmpty()){
			MyLogger.logError("RegisterOperator中表示社员职位的字符串为空串，无法正常注册。");
			checkResult = false;
		}
		
		return checkResult;
	}

	public String getStudentIndex() {
		return studentIndex;
	}

	public void setStudentIndex(String studentIndex) {
		this.studentIndex = studentIndex;
	}

	public String getClubIndex() {
		return clubIndex;
	}

	public void setClubIndex(String clubIndex) {
		this.clubIndex = clubIndex;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
