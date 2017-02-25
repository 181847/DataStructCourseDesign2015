package info.infoTool;

import basicTool.MyLogger;
import unfinishedClass.MyMember;
import unfinishedClass.Student;

public class MainCourseGetterForStudent extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container){
		if (container instanceof Student){
			return ((Student) container).getMainCourse();
		} else if (container instanceof MyMember){
			return ((MyMember) container).getStudent().getMainCourse();
		} else {
			MyLogger.logError("MainCourseGetterForStudent准备读取Info对象内存储的信息，"
					+ "但是读取的container不是Student的子类，"
					+ "无法获取信息。");
			return "";
		}
	}

}
