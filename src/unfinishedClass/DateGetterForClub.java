package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

public class DateGetterForClub extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (! (container instanceof Student)){
			MyLogger.log("DateGetterForClub读取info的container的类型不是Club，无法读取信息。");
			return "";
		}
		
		return ( (Club) container).getDate();
	}

}
