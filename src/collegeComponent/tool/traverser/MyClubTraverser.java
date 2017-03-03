package collegeComponent.tool.traverser;

import basicTool.MyLogger;
import collegeComponent.MyClub;
import info.infoTool.AbstractTraverser;

/**
 * 遍历Student对象内部的MyClubsInfoSet，
 * 向控制台输出所有参加的社团的信息，和相应的职位信息。
 */
public class MyClubTraverser extends AbstractTraverser {

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof MyClub)){
			MyLogger.log("错误：StudentTraverser读取info的container的类型不是Student，无法读取信息。");
			return 0;
		}
		
		MyClub tempContainer = (MyClub) container;
		System.out.println("社团编号：" + tempContainer.getIndex() 
				+ "\t社团名字：" + tempContainer.getName()
				+ "\n社团名字拼音：" + tempContainer.getPinyin()
				+ "\t社团名字短拼音：" + tempContainer.getShortPinyin()
				+ "\n在社团中的职位：" + tempContainer.getPosition());
		MyLogger.seperate("****");
		return 1;
	}

}
