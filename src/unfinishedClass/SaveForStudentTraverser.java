package unfinishedClass;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.Student;
import testVisual.SaveTraverser;

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
