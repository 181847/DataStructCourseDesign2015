package collegeComponent.tool.getter;

import basicInterface.IPinyinHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractGetter;

/**
 * 获取IInfo对象内部的container（IPinyinHolder）的短拼音。
 */
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
