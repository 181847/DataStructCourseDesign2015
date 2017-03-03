package collegeComponent.tool.getter;

import basicInterface.IIndexHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

/**
 * 获取IInfo对象内部的container（IIndexHolder）的姓名。
 */
public class IndexGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof IIndexHolder){
			return ((IIndexHolder) container).getIndex();
		} else {
			MyLogger.logError("IndexGetter准备读取Info对象内存储的信息，"
					+ "但是读取的container不是IIndexHolder的子类，"
					+ "无法获取信息。");
			return "";
		}
	}
}
