package unfinishedClass;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.Student;

public class StudentModelTraverser extends ModelTraverser {
	public StudentModelTraverser(Object[] studentColumn){
		super(studentColumn);
	}

	public StudentModelTraverser(DefaultTableModel model) {
		super(model);
	}
	
	public StudentModelTraverser(){
		super(new DefaultTableModel());
	}

	@Override
	public int dealWithContainer(Object arg0) {
		if (arg0 instanceof Student){
			Student student = (Student) arg0;
			model.addRow(new Object[]{
					student.getIndex(), 
					student.getName(),
					student.getGender() == 1? "男": "女",
					student.getGrade(),
					student.getMainCourse()});
			return 1;
		} else {
			MyLogger.logError("StudentModelTraverser遍历的container不是Student类型"
					+ "无法获取学生信息。");
			return 0;
		}
	}
}
