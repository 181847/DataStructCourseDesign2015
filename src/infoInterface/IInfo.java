package infoInterface;

/**
 * Info对象类型是一种句柄的概念，
 * 在本程序中，真正的信息是一个实例对象，比如Student类，Club类，
 * Student/Club的实例中真正保存着直接的信息，
 * 而Info类型则是一个重载Student/Club类型的操作句柄，
 * Info类型的对象中一般都用一个Object指针指向Student/Club类型，
 * 所以我们可以创建非常多的Info对象来把握同一个Student/Club对象，
 * 我们可以创建一个Info来将Student放在循环链表中做纯粹的存储，
 * 并且创建另一个Info来指向这同一个Student，
 * 并带着这个Student到搜索目录中成为一个索引，
 * 提高了可扩展性，今天我们是存一个Student，
 * 只需要一点简单的Getter/Traverser/Filter的增加，
 * 明天我们就可以存任何我们想要放进入InfoSet中的类，
 * 甚至是不同类型的对象也可以放在一个InfoSet中。
 */
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
