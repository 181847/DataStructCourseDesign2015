package collegeComponent.tool;

import basicTool.MyLogger;
import collegeComponent.Club;
import info.infoTool.AbstractTraverser;
import info.infoTool.AllTrueFilter;

public class ClubTraverser extends AbstractTraverser {

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof Club)){
			MyLogger.log("错误：ClubTraverser读取info的container的类型不是Club，无法读取信息。");
			return 0;
		}
		
		Club tempContainer = (Club) container;
		
		MyLogger.seperate("********* C L U B **************");
		System.out.println("社团编号：" + tempContainer.getIndex() 
				+ "\t社团名字：" + tempContainer.getName()
				+ "\n名字拼音：" + tempContainer.getPinyin()
				+ "\t名字短拼音：" + tempContainer.getShortPinyin()
				+ "\n创建日期：" + tempContainer.getDate());
		MyLogger.seperate("MyMember");
		tempContainer.getMyMembers().traverseInfo(new MyMemberTraverser(), new AllTrueFilter());
		MyLogger.seperate("MyMember");
		MyLogger.seperate("********* C L U B **************");
		MyLogger.newLine();
		return 1;
	}

}
