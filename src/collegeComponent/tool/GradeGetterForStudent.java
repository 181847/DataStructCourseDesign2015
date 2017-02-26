package collegeComponent.tool;

import basicTool.MyLogger;
import collegeComponent.MyMember;
import collegeComponent.Student;
import info.infoTool.AbstractGetter;

public class GradeGetterForStudent extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof Student){
			return ((Student) container).getGrade();
		} else if (container instanceof MyMember){
			return ((MyMember) container).getStudent().getGrade(); 
		} else {
			MyLogger.logError("GradeGetterForStudent准备读取Info对象内存储的信息，"
					+ "但是读取的container不是Student的子类，"
					+ "无法获取信息。");
			return "";
		}
	}
}