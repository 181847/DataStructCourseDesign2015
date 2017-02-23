package info.infoTool;

import basicInterface.IPinyinHolder;
import basicTool.MyLogger;

public class PinyinGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof IPinyinHolder){
			return ((IPinyinHolder) container).getPinyin();
		} else {
			MyLogger.logError("PinyinGetter准备读取Info对象内存储的信息，"
					+ "但是读取的container不是IPinyinHolder的子类，"
					+ "无法获取信息。");
			return "";
		}
	}

}