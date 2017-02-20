package unfinishedClass;

import basicInterface.INameHolder;

public class NameHolder implements INameHolder {
	public String name;
	
	/**
	 * @param name 对象的名字。
	 */
	public NameHolder(String name){
		this.name = name;
	}
	
	/**
	 * 默认构造方法，名字设置为“unknowName”。
	 */
	public NameHolder(){
		this.name = "unknowName";
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
