package unfinishedClass;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.Student;
import testVisual.SaveTraverser;

/**
 * 遍历College.clubInfoSet，
 * 用来存储所有学生的基本信息。
 */
public class SaveForStudentTraverser extends SaveTraverser {

	public SaveForStudentTraverser(PrintWriter studentWriter) {
		super(studentWriter);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (container instanceof Student){
			Student student = (Student) container;
			writer.println(student.getIndex()
					+ "&" + student.getName()
					+ "&" + student.getGender()
					+ "&" + student.getGrade()
					+ "&" + student.getMainCourse());
			return 1;
		} else {
			MyLogger.logError("SaveForStudentTraverser遍历的对象不是Student类型。");
		}
		return 0;
	}

}
