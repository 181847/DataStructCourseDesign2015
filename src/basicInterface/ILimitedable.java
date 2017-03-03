package basicInterface;

/**
 * 一个用来表示限制的接口，
 * 目前这个几口可用于LimitedTraverser中来进行限制性的遍历，
 * DoubleLoopLinkedInfoSet和InfoSearchTree都有定义一个可限制的遍历方法。
 */
public interface ILimitedable {
	/**
	 * @return
	 * 		被限制 true；
	 * 		正常 false。
	 */
	public boolean isLimited();
	
	/**
	 * @return
	 * 		被限制 false；
	 * 		正常 true。
	 */
	public boolean isNotLimited();
}
