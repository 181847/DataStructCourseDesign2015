package infoInterface;

/**
 * 用于对Info执行某种操作，
 * InfoSet有专门的方法将这个对象的方法对每个Info都执行一次操作。
 */
public interface IInfoTraverser {
	public int traverse(IInfo info);
}
