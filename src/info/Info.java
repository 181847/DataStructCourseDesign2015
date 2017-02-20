package info;

public class Info {
	/**
	 * 前一个信息体。
	 */
	public Info prec;
	
	/**
	 * 下一个信息体。
	 */
	public Info next;
	
	/**
	 * 存储信息的对象，
	 * 之所以创建这么一个对象是为了使得多个Info对象能够操作同一个信息
	 */
	public Object container;
	
	/**
	 * @param container 信息的承载体，可以是任何对象。
	 */
	public Info(Object container){
		this.container = container;
	}
	
	/**
	 * 默认构造方法，
	 * 内部信息全部为null。
	 */
	public Info(){
		//Empty Body
	}
	
	/**
	 * 获取当前Info对象中的信息承载体。
	 * @return 包含信息的Object对象。
	 */
	public Object getContainer() {
		return container;
	}

	/**
	 * 设置当前Info对象中的信息承载体。
	 * @param container 包含信息的Object对象。
	 */
	public void setContainer(Object container) {
		this.container = container;
	}

	/**
	 * 获取前一个Info信息体。
	 * @return 前一个Info信息体。
	 */
	public Info getPrec() {
		return prec;
	}
	
	/**
	 * 设置前一个Info信息体。
	 * @param prec 要设置的前一个信息体。
	 */
	public void setPrec(Info prec) {
		this.prec = prec;
	}
	
	/**
	 * 获取下一个信息体。
	 * @return 下一个信息体。
	 */
	public Info getNext() {
		return next;
	}
	
	/**
	 * 设置下一个信息体。
	 * @param next 下一个信息体。
	 */
	public void setNext(Info next) {
		this.next = next;
	}
}
