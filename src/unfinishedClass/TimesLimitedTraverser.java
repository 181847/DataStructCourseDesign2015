package unfinishedClass;

import basicInterface.IInfo;
import basicTool.MyLogger;
import infoInterface.ILimitedTraverser;

/**
 * 这个可限制的遍历者用一个最大遍历次数来限制是否可以遍历信息体，
 * 只有info对象不为null，调
 * 用了dealWithInfo()方法的时候才将最大遍历次数减一。
 */
public abstract class TimesLimitedTraverser extends AbstractTraverser implements ILimitedTraverser{
	/**
	 * 这个遍历者还可以再使用的最大次数。
	 */
	public int maxTraverseTimes;
	
	public TimesLimitedTraverser(int maxTraverseTimes){
		this.maxTraverseTimes = maxTraverseTimes;
	}
	
	@Override
	public boolean isLimited() {
		return maxTraverseTimes == 0;
	}
	
	@Override
	public boolean isNotLimited(){
		return maxTraverseTimes != 0;
	}
	
	/**
	 * 获取这个遍历者的剩余的最大遍历次数。
	 * @return 剩余的最大遍历次数。
	 */
	public int getMaxTimes() {
		return maxTraverseTimes;
	}

	/**
	 * 设置这个遍历者的剩余的最大遍历次数。
	 * @param maxTraverseTimes
	 */
	public void setMaxTimes(int maxTraverseTimes) {
		this.maxTraverseTimes = maxTraverseTimes;
	}

	/**
	 * 在检查info对象非空之后，检查maxTraverseTimes是否为0，
	 * 如果不为0，就调用dealWithInfo()来处理info的信息，
	 * 如果maxTraverseTimes为0，就直接返回0。
	 */
	@Override
	public int traverse(IInfo info){
		if (info == null){
			MyLogger.logError("TimesLimitedTraverser准备遍历当前信息体，但是信息体本身为null，"
					+ "不可读取空指针，遍历当前信息体失败。");
			return 0;
		}
		if (isLimited()){
			return 0;
		}
		
		--maxTraverseTimes;
		return dealWithInfo(info);
	}
}
