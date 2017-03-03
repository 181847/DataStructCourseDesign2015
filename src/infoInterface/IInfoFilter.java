package infoInterface;

/**
 * 用于InfoSet的部分操作，
 * 对Info进行判断，返回真假，
 * InfoSet根据Filter的真假返回值来判断是否对这个Info进行某种操作。
 */
public interface IInfoFilter {
	/**
	 * 针对当前Info中的信息返回真假。
	 * @param info 信息体。
	 * @return 满足Filter中的要求返回真，
	 * 不满足返回假。
	 */
	public boolean check(IInfo info);
}
