package collegeComponent.tool.getter;

import basicInterface.IDateHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

/**
 * 获取IInfo对象内部的container（IDateHolder）的日期。
 */
public class DateGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (! (container instanceof IDateHolder)){
			MyLogger.log("DateGetterForClub读取info的container的类型不是Club，无法读取信息。");
			return "";
		}
		
		return ( (IDateHolder) container).getDate();
	}

}
