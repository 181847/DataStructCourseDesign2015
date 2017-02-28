package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.Student;
import info.infoTool.TimesLimitedTraverser;

public class LimitedStudentTraverser extends TimesLimitedTraverser {

	/**
	 * 设置可以遍历的最大次数。
	 * @param maxTraverseTimes 可以遍历的最大次数。
	 */
	public LimitedStudentTraverser(int maxTraverseTimes) {
		super(maxTraverseTimes);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof Student)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return 0;
		}
		
		Student tempContainer = (Student) container;
		
		String gender;
		switch( tempContainer.getGender() ){
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
		
		System.out.println("学生学号：" + tempContainer.getIndex() 
				+ "\t学生姓名：" + tempContainer.getName()
				+ "\n姓名拼音：" + tempContainer.getPinyin()
				+ "\t姓名短拼音：" + tempContainer.getShortPinyin()
				+ "\n性别：" + gender
				+ "\t年级：" + tempContainer.getGrade()
				+ "\n专业：" + tempContainer.getMainCourse());
		MyLogger.seperate();
		return 1;
	}

}
