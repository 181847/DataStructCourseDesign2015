package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.AbstractTraverser;

public class ClubTraverser extends AbstractTraverser {

	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof Club)){
			MyLogger.log("错误：ClubTraverser读取info的container的类型不是Club，无法读取信息。");
			return 0;
		}
		
		Club tempContainer = (Club) container;
		System.out.println("社团编号：" + tempContainer.getIndex() 
				+ "\t社团名字：" + tempContainer.getName()
				+ "\n名字拼音：" + tempContainer.getPinyin()
				+ "\t名字短拼音：" + tempContainer.getShortPinyin()
				+ "\n创建日期：" + tempContainer.getDate());
		MyLogger.seperate();
		return 1;
	}

}
