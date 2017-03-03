package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoTraverser;

/**
 * 一个Traverser的抽象类，
 * 这个抽象类先对info做空指针判断，
 * 在container一定不为null的情况将对container的处理交给dealWithContainer()处理，
 * 程序猿在定义新的Traverser的时候就不需要对各种非空情况进行判断了。
 */
public abstract class AbstractTraverser implements IInfoTraverser {
	
	public abstract int dealWithContainer(Object container);

	/**
	 * 读取info对象并调用dealWithContainer来处理信息，
	 * @return 
	 * 		如果info为null的话，记录错误信息，并直接返回0。
	 * 		如果container为null的话，返回-1。
	 */
	@Override
	public int traverse(IInfo info) {
		if (info == null){
			MyLogger.logError("AbstractTraverser准备遍历当前信息体，但是信息体本身为null，"
					+ "不可读取空指针，遍历当前信息体失败。");
			return 0;
		}
		Object container = info.getContainer();
		if (container == null){
			MyLogger.logError("AbstractTraverser准备遍历当前信息体，"
					+ "但是读取出来的container为null，"
					+ "从container获取信息失败。");
			return -1;
		}
		return dealWithContainer(container);
	}
}
