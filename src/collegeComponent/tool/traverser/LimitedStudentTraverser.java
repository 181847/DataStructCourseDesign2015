package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.Student;
import info.infoTool.TimesLimitedTraverser;

/**
 * 可限制次数的学生遍历者，
 * DoubleLoopLinkedInfoSet和InfoSearchTree拥有
 * 专门为此类有限制的遍历者的遍历方法，
 * 普通遍历方法也可以实现次数限制遍历，但是专门的限制方法更节省资源，
 * 能够在Traverser被限制的瞬间跳出遍历，
 * 原本这中限制性的遍历是为了加快搜索显示速度，
 * 对于数量极大的搜索结果，先显示部分的搜索结果，
 * 当确定之后才显示全部的显示结果，
 * 但是此类还没有应用到搜索结果的表示中，因为现有的测试数据还太小，
 * 没有办法测试大量的数据时的速度。
 */
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
