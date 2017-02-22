package unfinishedClass;

import basicInterface.IInfo;
import basicTool.MyLogger;
import infoInterface.IInfoTraverser;

/**
 * 在这里规定了如果遍历的info对象为null的话，
 * 就直接返回0，
 * 如果info不为null就交由dealWithInfo()方法来处理。
 */
public abstract class AbstractTraverser implements IInfoTraverser {
	
	public abstract int dealWithInfo(IInfo info);

	/**
	 * 读取info对象并调用dealWithInfo来处理信息，
	 * 如果info为null的话，记录错误信息，并直接返回0。
	 */
	@Override
	public int traverse(IInfo info) {
		if (info == null){
			MyLogger.logError("AbstractTraverser准备遍历当前信息体，但是信息体本身为null，"
					+ "不可读取空指针，遍历当前信息体失败。");
			return 0;
		}
		return dealWithInfo(info);
	}
}
