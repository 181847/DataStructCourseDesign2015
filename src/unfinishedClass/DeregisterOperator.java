package unfinishedClass;

import basicTool.MyLogger;

public class DeregisterOperator extends CollegeOperator {
	public String studentIndex;
	public String clubIndex;

	public DeregisterOperator(College college) {
		super(college);
	}

	@Override
	public int operate() {
		if (check()){
			if (college
					.getClub(clubIndex)
					.getMyMembers()
					.deleteIndex(studentIndex)
					.isEmpty()){
				MyLogger.logError("DeregisterOperator注销社团成员的时候"
						+ "没能从社团中正确将学生从记录中删除，请检查："
						+ "studentIndex：" + studentIndex
						+ "clubIndex：" + clubIndex);
			}
			if (college
					.getStudent(studentIndex)
					.getMyClubs()
					.deleteIndex(clubIndex)
					.isEmpty()){
				MyLogger.logError("DeregisterOperator注销社团成员的时候"
						+ "没能从学生中正确将参加的社团从记录中删除，请检查："
						+ "\nstudentIndex：" + studentIndex
						+ "\nclubIndex：" + clubIndex);
			}
			return 1;
		}
		return 0;
	}

	private boolean check() {
		boolean checkResult = true;
		if (studentIndex == null){
			MyLogger.logError("DeregisterOperator注销社团成员的时候"
						+ "社员学号为null，请指定退社社员的学号。");
			checkResult = false;
		} else if (studentIndex.isEmpty()){
			MyLogger.logError("DeregisterOperator注销社团成员的时候"
					+ "社员学号为空串，请指定退社社员的学号。");
		checkResult = false;
		}
		if (clubIndex == null){
			MyLogger.logError("DeregisterOperator注销社团成员的时候"
						+ "社团编号为null，请指定退社 社团编号。");
			checkResult = false;
		} else if (clubIndex.isEmpty()){
			MyLogger.logError("DeregisterOperator注销社团成员的时候"
					+ "社团编号为空串，请指定退社社团编号。");
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

	
}
