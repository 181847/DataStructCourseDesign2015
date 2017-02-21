package student.infoTool;

import basicInterface.IInfo;
import basicTool.MyLogger;
import infoInterface.IInfoTraverser;
import student.Student;

public class StudentTraverser implements IInfoTraverser {

	@Override
	public int traverserInfo(IInfo info) {
		if (info == null){
			MyLogger.log("错误：StudentTraverser准备读取info信息进行信息遍历，但是参数info为null。");
			return 0;
		}
		Object container = info.getContainer();
		
		if (container == null){
			MyLogger.log("错误：StudentTraverser读取info的container为null，无法读取信息。");
			return 0;
		}
		if (! (container instanceof Student)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return 0;
		}
		
		Student tempContainer = (Student) container;
		System.out.println("学生学号：" + tempContainer.getIndex() 
				+ "\t学生姓名：" + tempContainer.getName());
		return 1;
	}

}
