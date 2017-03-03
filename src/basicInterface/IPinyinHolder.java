package basicInterface;

/**
 * 定义了获取和设置全拼音和短拼音的接口方法，
 * 举例：“你好新世界！@”；
 * 全拼音：“nihaoxinshijie！@”；
 * 短拼音：“nhxsj！@”。
 */
public interface IPinyinHolder{
	/**
	 * 获取全拼音。
	 * @return 
	 * 		全拼音，不包含声调，
	 * 		将汉字全部转换为小写英文字符，
	 * 		例如“你好新世界！@” -> “nihaoxinshijie！@”。
	 */
	public String getPinyin();
	
	/**
	 * 设置拼音。
	 * @param pinyinName 
	 * 		要设置的拼音。
	 */
	public void setPinyin(String pinyin);
	
	/**
	 * 获取短拼音。
	 * @return
	 * 		短拼音，不包含声调，
	 * 		将汉字全部转换为对应拼音的头字符的小写英文字符，
	 * 		例如“你好新世界！@” -> “nhxsj！@”。
	 */
	public String getShortPinyin();
	
	/**
	 * 设置短拼音。
	 * @param
	 * 		要设置的短拼音。
	 */
	public void setShortPinyin(String shortPinyin);
}
