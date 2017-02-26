package collegeComponent.tool;

import basicTool.MyLogger;
import collegeComponent.Student;
import info.infoTool.AbstractTraverser;

public class StudentTraverser extends AbstractTraverser {

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof Student)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return 0;
		}
		
		Student tempContainer = (Student) container;
		System.out.println("学生学号：" + tempContainer.getIndex() 
				+ "\t学生姓名：" + tempContainer.getName()
				+ "\n姓名拼音：" + tempContainer.getPinyin()
				+ "\t姓名短拼音：" + tempContainer.getShortPinyin()
				+ "\n性别：" + (tempContainer.getGender() == 1? "男" : "女")
				+ "\t\t\t年级：" + tempContainer.getGrade()
				+ "\n专业：" + tempContainer.getMainCourse());
		MyLogger.seperate();
		return 1;
	}

}
