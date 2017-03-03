package info;

import infoInterface.IInfo;

/**
 * 一个双向链接的Info类型，
 * 用于DoubleLoopLinkedInfoSet中作为双重循环链表的一个节点。
 */
public class DoubleLinkedInfo extends InfoWithContainer implements IInfo {
	/**
	 * 前一个信息体。
	 */
	public DoubleLinkedInfo prec;
	
	/**
	 * 下一个信息体。
	 */
	public DoubleLinkedInfo next;
	
	/**
	 * 创建一个双重连接的节点Info对象。
	 * @param container 
	 * 				这个Info对象当中的具体信息。
	 */
	public DoubleLinkedInfo(Object container){
		super(container);
	}
	
	/**
	 * 默认的构造方法创建一个container为null的双重链接的节点Info。
	 */
	public DoubleLinkedInfo(){
		super(null);
	}
	
	/**
	 * 获取前一个Info信息体。
	 * @return 前一个Info信息体。
	 */
	public DoubleLinkedInfo getPrec() {
		return prec;
	}
	
	/**
	 * 设置前一个Info信息体。
	 * @param prec 要设置的前一个信息体。
	 */
	public void setPrec(DoubleLinkedInfo prec) {
		this.prec = prec;
	}
	
	/**
	 * 获取下一个信息体。
	 * @return 下一个信息体。
	 */
	public DoubleLinkedInfo getNext() {
		return next;
	}
	
	/**
	 * 设置下一个信息体。
	 * @param next 下一个信息体。
	 */
	public void setNext(DoubleLinkedInfo next) {
		this.next = next;
	}
}
