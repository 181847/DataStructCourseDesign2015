package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;

/**
 * 一个Filter的抽象类，
 * 这个抽象类先对info做空指针判断，
 * 在container一定不为null的情况将对container的处理交给dealWithContainer()处理，
 * 程序猿在定义新的Filter的时候就不需要对各种非空情况进行判断了。
 */
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
