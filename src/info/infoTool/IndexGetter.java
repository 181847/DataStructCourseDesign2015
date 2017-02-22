package info.infoTool;

import basicInterface.IIndexHolder;
import basicTool.MyLogger;

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
