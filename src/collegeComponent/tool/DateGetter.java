package collegeComponent.tool;

import basicInterface.IDateHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

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
