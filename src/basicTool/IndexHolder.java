package basicTool;

import basicInterface.IIndexHolder;

/**
 * 本类实现IIndexHolder接口，
 * 定义了一个包含序号的对象类型，
 * 内部用一个字符串来保存序号。
 */
public class IndexHolder implements IIndexHolder {
	/**
	 * 以字符串形式保存的编号。
	 */
	protected String index;
	
	/**
	 * 设置对象的序号。
	 * @param index
	 * 		要设置的序号。
	 */
	public IndexHolder(String index){
		this.index = index;
	}
	
	/**
	 * 获取编号。
	 * @return 
	 * 		编号。
	 */
	@Override
	public String getIndex() {
		return index;
	}

	/**
	 * 设置编号。
	 * @param
	 * 		要设置的编号字符串。
	 */
	@Override
	public void setIndex(String index) {
		this.index = index;
	}

}
