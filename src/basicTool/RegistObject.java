package basicTool;

import basicInterface.INameHolder;

public class RegistObject extends IndexHolder implements INameHolder {
	public String name;
	
	/**
	 * @param name 对象的名字。
	 */
	public RegistObject(String index, String name){
		super(index);
		this.name = name;
	}

	/**
	 * 设置名字
	 * @param 
	 * 		要设置的名字。
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回名字
	 * @return 
	 * 		名字。
	 */
	@Override
	public String getName() {
		return name;
	}
}
