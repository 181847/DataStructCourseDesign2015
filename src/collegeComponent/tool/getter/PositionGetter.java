package collegeComponent.tool.getter;

import basicInterface.IPositionHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

/**
 * 获取IInfo对象内部的container（IPositionHolder）的职位。
 */
public class PositionGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof IPositionHolder){
			return ((IPositionHolder) container).getPosition();
		}
		
		MyLogger.logError("PositiongetterForPositionInClub准备读取Info对象内存储的信息，"
				+ "但是读取的container不是IPositionInClub的子类，"
				+ "无法获取信息。");
		return "";
	}

}
