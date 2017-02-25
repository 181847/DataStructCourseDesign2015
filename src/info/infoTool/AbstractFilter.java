package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;

public abstract class AbstractFilter implements IInfoFilter {

	public abstract boolean dealWithContainer(Object container);

	/**
	 * 读取info对象并调用dealWithContainer来处理信息，
	 * @return 
	 * 		如果info为null或者container为null的话，返回false。
	 */
	@Override
	public boolean check(IInfo info) {
		if (info == null){
			MyLogger.logError("AbstractFilter准备提取当前信息体的信息，但是信息体本身为null，"
					+ "不可读取空指针，提取信息失败。");
			return false;
		}
		Object container = info.getContainer();
		if (container == null){
			MyLogger.logError("AbstractFilter准备提取当前信息体的信息，"
					+ "但是读取出来的container为null，"
					+ "从container获取信息失败。");
			return false;
		}
		return dealWithContainer(container);
	}
}
