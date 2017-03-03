package basicInterface;

/**
 * 定义了获取和设置日期的接口方法。
 */
public interface IDateHolder {
	/**
	 * 设置日期。
	 * @param dateString
	 * 		用来初始化日期的字符串。
	 */
	public void setDate(String dateString);
	
	/**
	 * 获取日期。
	 * @return
	 * 		日期的字符串。
	 */
	public String getDate();
}
