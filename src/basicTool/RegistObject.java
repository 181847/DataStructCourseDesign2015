package basicTool;

import basicInterface.INameHolder;

/**
 * 本类继承于IndexHolder类型，并实现了INameHolder接口，
 * 内部用一个字符串来存储名字，
 * 这个类型的对象包含序号和名字，
 * 能够作为社团对象和学生对象的基本类型，
 * 可以注册到对应的集合体当中，
 * 因而本类被命名为RegistObject。
 */
public class RegistObject extends IndexHolder implements INameHolder {
	protected String name;
	
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
