package basicInterface;

/**
 * 定义了获取和设置名字的接口方法。
 */
public interface INameHolder {
	/**
	 * 设置名字。
	 * @param 要设置的名字。
	 */
	public void setName(String name);
	
	/**
	 * 获取名字。
	 * @return 名字。
	 */
	public String getName();
}
