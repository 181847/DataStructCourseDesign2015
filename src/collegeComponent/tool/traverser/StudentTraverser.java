package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.Student;
import info.infoTool.AbstractTraverser;
import info.infoTool.AllTrueFilter;

/**
 * 遍历StudentInfoSet，
 * 向控制台输出所有学生的信息，
 * 包括所有参加的社团的信息和职位的信息。
 */
public class StudentTraverser extends AbstractTraverser {

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
		
		MyLogger.seperate("********* S T U D E N T **************");
		System.out.println("学生学号：" + tempContainer.getIndex() 
				+ "\t学生姓名：" + tempContainer.getName()
				+ "\n姓名拼音：" + tempContainer.getPinyin()
				+ "\t姓名短拼音：" + tempContainer.getShortPinyin()
				+ "\n性别：" + gender
				+ "\t\t\t年级：" + tempContainer.getGrade()
				+ "\n专业：" + tempContainer.getMainCourse());
		MyLogger.seperate("MyClubs");
		tempContainer.getMyClubs().traverseInfo(new MyClubTraverser(), new AllTrueFilter());
		MyLogger.seperate("MyClubs");
		MyLogger.seperate("********* S T U D E N T **************");
		return 1;
	}

}
