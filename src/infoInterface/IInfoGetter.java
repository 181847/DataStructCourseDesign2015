package infoInterface;

public interface IInfoGetter {
	/**
	 * 获取Info对象当中指定的信息。
	 * @param info 信息体。
	 * @return 信息体中的字符串信息。
	 */
	public String pickMessage(IInfo info);
}
