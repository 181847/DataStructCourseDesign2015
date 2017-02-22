package student.infoTool;

import basicInterface.IInfo;
import basicTool.MyLogger;
import student.Student;
import unfinishedClass.TimesLimitedTraverser;

public class LimitedStudentTraverser extends TimesLimitedTraverser {

	/**
	 * 设置可以遍历的最大次数。
	 * @param maxTraverseTimes 可以遍历的最大次数。
	 */
	public LimitedStudentTraverser(int maxTraverseTimes) {
		super(maxTraverseTimes);
	}

	@Override
	public int dealWithInfo(IInfo info) {
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
