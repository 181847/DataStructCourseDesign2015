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
			
			String gender;
			switch( student.getGender() ){
			case 0:
				gender = "男";
				break;
			case 1:
				gender = "女";
				break;
			default:
				gender = "?";
				break;
			}
			
			model.addRow(new Object[]{
					student.getIndex(), 
					student.getName(),
					gender,
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
