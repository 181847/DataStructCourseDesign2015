package basicInterface;

public interface IPinyinNameHolder{
	/**
	 * 获取名字的拼音。
	 * @return 名字的拼音，不包含声调，
	 * 将汉字全部转换为小写英文字符。
	 */
	public String getPinyinOfName();
}
