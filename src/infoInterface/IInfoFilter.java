package infoInterface;
import basicInterface.IInfo;

public interface IInfoFilter {
	/**
	 * 针对当前Info中的信息返回真假。
	 * @param info 信息体。
	 * @return 满足Filter中的要求返回真，
	 * 不满足返回假。
	 */
	public boolean check(IInfo info);
}
