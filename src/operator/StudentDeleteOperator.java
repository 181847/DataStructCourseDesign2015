package operator;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.Student;
import collegeComponent.tool.traverser.DeregistTraverserForMyClub;
import info.infoTool.AllTrueFilter;
import infoSet.InfoSetSpecificByIndex;

public class StudentDeleteOperator extends DeleteOperator {

	public StudentDeleteOperator(College college) {
		super(college);
	}
	
	public StudentDeleteOperator(College college, String studentIndex){
		this(college);
		this.targetIndex = studentIndex;
	}

	/**
	 * @return
	 * 		如果目标序号不符合规范，
	 * 	就返回0；
	 * 		如果不存在指定序号的学生，
	 * 	返回-1；
	 * 		成功返回1。
	 */
	@Override
	public int operate() {
		if (check()){
			Student student = college.getStudent(targetIndex);
			if (student == null){
				MyLogger.logError("StudentDeleteOperator要删除的对象不存在，"
						+ "请检查：" + targetIndex);
				return -1;
			}
			InfoSetSpecificByIndex myClubs = student.getMyClubs();
			
			DeregistTraverserForMyClub dtfmc = 
					new DeregistTraverserForMyClub(
							getCollege(), getTargetIndex());
			
			myClubs.traverseInfo(dtfmc, new AllTrueFilter());
			college.getStudentInfoSet().deleteIndex(targetIndex);
			return 1;
		}
		return 0;
	}
	
	
}
