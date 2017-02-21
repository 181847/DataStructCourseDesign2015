package basicInterface;

public interface IInfo {
	/**
	 * 获取当前信息体中的信息。
	 * @return 当前信息体中的信息。 
	 */
	public Object getContainer();
	
	/**
	 * 设置当前信息体当中的信息。
	 * @param container 将要放进当前信息体中的具体信息对象。
	 */
	public void setContainer(Object container);
}
