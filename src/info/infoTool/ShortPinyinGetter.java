package info.infoTool;

import basicInterface.IPinyinHolder;
import basicTool.MyLogger;

public class ShortPinyinGetter extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof IPinyinHolder){
			return ((IPinyinHolder) container).getShortPinyin();
		} else {
			MyLogger.logError("ShortPinyinGetter准备读取Info对象内存储的信息，"
					+ "但是读取的container不是IPinyinHolder的子类，"
					+ "无法获取信息。");
			return "";
		}
	}

}
