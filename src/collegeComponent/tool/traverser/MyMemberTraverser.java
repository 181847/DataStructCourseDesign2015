package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.MyMember;
import info.infoTool.AbstractTraverser;

/**
 * 遍历Club对象内部的MyClubsInfoSet，
 * 向控制台输出所有参加的社团的信息，和相应的职位信息。
 */
public class MyMemberTraverser extends AbstractTraverser {

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof MyMember)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return 0;
		}
		
		MyMember tempContainer = (MyMember) container;
		System.out.println("社员学号：" + tempContainer.getIndex() 
				+ "\t社员名字：" + tempContainer.getName()
				+ "\n社员名字拼音：" + tempContainer.getPinyin()
				+ "\t社员名字短拼音：" + tempContainer.getShortPinyin()
				+ "\n社员在社团中的职位：" + tempContainer.getPosition());
		MyLogger.seperate("****");
		return 1;
	}

}
