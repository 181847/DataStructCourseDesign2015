package info;

import infoInterface.IInfo;

public class InfoWithContainer implements IInfo{
	/**
	 * 存储信息的对象，
	 * 之所以创建这么一个对象是为了使得多个Info对象能够操作同一个信息
	 */
	public Object container;
	
	/**
	 * @param container 信息的承载体，可以是任何对象。
	 */
	public InfoWithContainer(Object container){
		this.container = container;
	}
	
	/**
	 * 默认构造方法，
	 * 内部信息全部为null。
	 */
	public InfoWithContainer(){
		//Empty Body
	}
	
	/**
	 * 获取当前Info对象中的信息承载体。
	 * @return 包含信息的Object对象。
	 */
	@Override
	public Object getContainer() {
		return container;
	}

	/**
	 * 设置当前Info对象中的信息承载体。
	 * @param container 包含信息的Object对象。
	 */
	@Override
	public void setContainer(Object container) {
		this.container = container;
	}

	
}
