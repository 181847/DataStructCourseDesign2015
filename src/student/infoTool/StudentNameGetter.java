package student.infoTool;

import basicInterface.IInfo;
import basicTool.MyLogger;
import infoInterface.IInfoGetter;
import student.Student;

/**
 * 专门获取Student类型的名字，
 * 如果发生任何错误就返回一个空串。
 * @author 75309
 *
 */
public class StudentNameGetter implements IInfoGetter {

	@Override
	public String pickInfo(IInfo info) {
		if (info == null){
			MyLogger.log("错误：StudentTraverser准备读取info信息进行信息遍历，但是参数info为null。");
			return "";
		}
		Object container = info.getContainer();
		
		if (container == null){
			MyLogger.log("错误：StudentTraverser读取info的container为null，无法读取信息。");
			return "";
		}
		if (! (container instanceof Student)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return "";
		}
		
		Student tempContainer = (Student) container;
		
		return tempContainer.getName();
	}

}
