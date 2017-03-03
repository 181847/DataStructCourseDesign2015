package collegeComponent.tool.getter;

import basicInterface.INameHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

/**
 * 获取IInfo对象内部的container（INameHolder）的姓名。
 */
public class NameGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof INameHolder){
			return ((INameHolder) container).getName();
		} else {
			MyLogger.logError("NameGetter准备读取Info对象内存储的信息，"
					+ "但是读取的container不是INameHolder的子类，"
					+ "无法获取信息。");
			return "";
		}
	}
}
