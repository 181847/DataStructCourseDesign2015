package basicInterface;

/**
 * 定义了有关序号的获取和设置。
 */
public interface IIndexHolder {
	/**
	 * 获取序号。
	 * @return 序号。
	 */
	public String getIndex();
	
	/**
	 * 设置序号。
	 * @param index 要设置的序号。
	 */
	public void setIndex(String index);
}
