package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoTraverser;

/**
 * 在这里规定了如果遍历的info对象为null的话，
 * 就直接返回0，
 * 如果info不为null就交由dealWithInfo()方法来处理。
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
