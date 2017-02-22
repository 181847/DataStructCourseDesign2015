package basicInterface;

public interface IPinyinHolder{
	/**
	 * 获取名字的拼音。
	 * @return 
	 * 		名字的拼音，不包含声调，
	 * 		将汉字全部转换为小写英文字符。
	 */
	public String getPinyin();
	
	/**
	 * 设置拼音名字。
	 * @param pinyinName 
	 * 		要设置的拼音名字。
	 */
	public void setPinyin(String pinyin);
	
	/**
	 * 获取拼音首字母组成的字符串，
	 * 只有汉字变成了拼音首字母，
	 * 其他非汉字保持不变。
	 * @return
	 */
	public String getShortPinyin();
	
	/**
	 * 设置拼音首字母字符串。
	 * @param
	 * 		要设置的短拼音字符串。
	 */
	public void setShortPinyin(String shortPinyin);
}
