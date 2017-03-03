package basicInterface;

/**
 * 所有操作器的接口方法，
 * 不同的操作器针对不同的功能进行定制，
 * 统一通过这个operate()方法来执行功能，
 * 操作器这个概念是用来组合InfoSet的一些功能，
 * 将一些功能简化，能够方便快捷的调用，
 * 而不用去修改InfoSet本身的方法。
 */
public interface IOperator {
	public int operate();
}
