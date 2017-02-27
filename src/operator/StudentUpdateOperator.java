package operator;

import basicTool.MyLogger;
import collegeComponent.College;
import collegeComponent.Student;
import collegeComponent.tool.traverser.UpdateTraverserForMyClubs;
import info.infoTool.AllTrueFilter;

public class StudentUpdateOperator extends UpdateOperator {
	public Student student;

	public StudentUpdateOperator(College college, Student student) {
		super(college);
		setStudent(student);
	}
	
	public StudentUpdateOperator(College college){
		super(college);
	}

	/**
	 * 将内部的学生对象发生的信息更新反馈到college和club中。
	 * （不包括社团的职位信息）
	 */
	@Override
	public int operate() {
		if (student == null){
			MyLogger.logError("StudentUpdateOperator设置的学生对象为null，信息更新失败。");
			return 0;
		}
		if (originalIndex == null || originalIndex.isEmpty()){
			MyLogger.logError("StudentUpdateOperator中的学号信息为null或者空串，信息更新失败。");
			return 0;
		}
		college.deleteStudent(originalIndex);
		college.addStudent(student);
		student
			.getMyClubs()
			.traverseInfo(
					new UpdateTraverserForMyClubs(originalIndex), 
					new AllTrueFilter());
		return 0;
	}

	public Student getStudent() {
		return student;
	}

	/**
	 * 设置信息更新的学生对象。
	 * @param student
	 * 		将要更新信息的学生对象。
	 */
	public void setStudent(Student student) {
		if (student == null){
			MyLogger.logError("StudentUpdateOperator设置的学生对象为null，设置失败。");
			return;
		}
		this.originalIndex = student.getIndex();
		this.student = student;
	}

}
