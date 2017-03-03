package infoSet;

import basicInterface.IInfoSet;
import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import info.InfoWithContainer;
import info.infoTool.CopyTraverser;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoInterface.IInfoTraverser;
import infoInterface.ILimitedTraverser;

/**
 * 以双重循环链表的方式来存储Info的集合。
 */
public class DoubleLoopLinkedInfoSet implements IInfoSet{
	/**
	 * 默认的头部节点。
	 */
	public DoubleLinkedInfo head;
	
	/**
	 * 元素的数量。
	 */
	public int num;
	
	/**
	 * 默认构造方法，
	 * 创建一个Info实例付给head，
	 * head头尾相连。
	 */
	public DoubleLoopLinkedInfoSet(){
		head = new DoubleLinkedInfo(new Object());
		head.setNext(head);
		head.setPrec(head);
	}
	
	/**
	 * 返回这个InfoSet是否为空，
	 * 由于InfoSet是一个双重循环链表，
	 * 判断空的依据是头节点的下一个节点是不是头节点自己。
	 * @return 空，返回真；
	 * 不空，返回假。
	 */
	public boolean isEmpty(){
		return head.next == head;
	}
	
	/**
	 * @return InfoSet的头节点。
	 */
	public DoubleLinkedInfo getHead(){
		return head;
	}
	
	/**
	 * 向InfoSet的头节点的后面插入Info。
	 * @param newInfo 待插入的节点信息体。
	 * @return 如果插入的元素的为null的话，返回0；
	 * 如果插入的newInfo中的container为null的话，返回-1；
	 * 成功插入返回1。
	 */
	public int insertInfo(IInfo newInfo){
		if (newInfo == null){
			MyLogger.log("DLLInfoSet插入的元素为null，"
					+ "插入失败。");
			return 0;
		} else if (newInfo.getContainer() == null){
			MyLogger.log("DLLInfoSet插入的元素中的container为null，"
					+ "插入失败。");
			return -1;
		}
		DoubleLinkedInfo newDLInfo = new DoubleLinkedInfo(newInfo.getContainer());
		
		newDLInfo.next = head.next;
		newDLInfo.prec = head;
		head.next = newDLInfo;
		newDLInfo.next.prec = newDLInfo;
		
		++num;
		return 1;
	}
	
	/**
	 * 删除这个双重循环链表中的一个节点。
	 * @param checkPointer 一个属于这个链表的节点。
	 */
	private int deleteInfo(DoubleLinkedInfo checkPointer) {
		if (checkPointer == null){
			MyLogger.log("调用InfoSet.int delete(Info checkPointer) 时 方法参数为null。删除节点失败。");
			return 0;
		}
		checkPointer.prec.next = checkPointer.next;
		checkPointer.next.prec = checkPointer.prec;
		--num;
		return 1;
	}
	
	/**
	 * 获取这个InfoSet当中指定的信息体，用另一个InfoSet返回
	 * @param searchInfo 
	 * 		主要用来检查的对象，
	 * 		只有与这个searchInfo相等才算找到。
	 * @param getter 
	 * 		这个对象用来获取Info信息体中指定的信息，
	 * 		比如某个InfoGetter用来获取Info中的名字信息，
	 * 		有的InfoGetter用来获取Info中的学号信息。
	 * @param filter 
	 * 		过滤器，用于针对Info中的信息返回真假，
	 * 		最终的InfoSet会过滤掉所有filter检查结果为false的Info，
	 * 		比如某个filter规定如果StudentInfo的年级为大一，
	 * 		那么这次搜寻结果就只会有大一的StudentInfo。
	 * @return 
	 * 		查找结果。
	 */
	public IInfoSet search(String searchInfo, IInfoGetter getter, IInfoFilter filter){
		DoubleLoopLinkedInfoSet resultSet = new DoubleLoopLinkedInfoSet();
		if (searchInfo == null || getter == null || filter == null){
			MyLogger.log("在DLLInfoSet中搜索Info的时候，参数存在null，删除Info失败。请检查："
					+ "String searchInfo == null: " + (searchInfo == null) 
					+ "IInfoGetter getter == null: " + (getter == null)
					+ "IInfoFilter filter == null: " + (filter == null));
			return resultSet;
		}
		
		DoubleLinkedInfo checkPointer = head.next;
		
		while(checkPointer != head){
			if (( searchInfo.isEmpty() || 
					getter.pickMessage(checkPointer).hashCode() == searchInfo.hashCode()
					) && filter.check(checkPointer)){
				
				resultSet.insertInfo(new DoubleLinkedInfo(checkPointer.getContainer()));
			}
			checkPointer = checkPointer.next;
		}
		
		return resultSet;
	}

	/**
	 * 删除这个InfoSet当中指定的信息体，用另一个InfoSet返回删除的所有Info对象。
	 * @param searchInfo 
	 * 		主要用来检查的字符串信息，
	 * 		只有与这个searchInfo相等才算找到，
	 * 		如果这个字符串为空串的话表示所有信息体都符合，
	 * 		如果这些信息体同时通过了filter的筛选的话，才会删除。
	 * @param getter 
	 * 		这个对象用来获取Info信息体中指定的信息，
	 * 		比如某个InfoGetter用来获取Info中的名字信息，
	 * 		有的InfoGetter用来获取Info中的学号信息。
	 * @param filter 
	 * 		过滤器，用于针对Info中的信息返回真假，
	 * 		最终的InfoSet会过滤掉所有filter检查结果为false的Info，
	 * 		比如某个filter规定如果StudentInfo的年级为大一，
	 * 		那么这次删除只会删除大一的StudentInfo。
	 * @return 
	 * 		删除的所有Info组成的InfoSet。
	 */
	public DoubleLoopLinkedInfoSet delete(String searchInfo, IInfoGetter getter, IInfoFilter filter) {
		DoubleLoopLinkedInfoSet resultSet = new DoubleLoopLinkedInfoSet();
		if (searchInfo == null || getter == null || filter == null){
			MyLogger.log("从InfoSet中删除Info的时候，参数存在null，删除Info失败。请检查："
					+ "String searchInfo == null: " + (searchInfo == null) 
					+ "IInfoGetter getter == null: " + (getter == null)
					+ "IInfoFilter filter == null: " + (filter == null));
			return resultSet;
		}
		
		DoubleLinkedInfo checkPointer = head.next;
		while(checkPointer != head){
			if (( searchInfo.isEmpty() || 
					getter.pickMessage(checkPointer).hashCode() == searchInfo.hashCode()
					) && filter.check(checkPointer)){
				
				deleteInfo(checkPointer);
				resultSet.insertInfo(checkPointer);
			}
			checkPointer = checkPointer.next;
		}
		
		return resultSet;
	}

	/**
	 * 将本InfoSet中的Info和参数InfoSet中的信息全部复制和添加到新的InfoSet中，
	 * 并返回，
	 * 这个操作不会破坏参数中的数据。
	 * @param infoSet 要加入的InfoSet
	 * @param filter 过滤器，不符合这个过滤器的Info不能加入当前这个InfoSet。
	 * @return 合并之后的InfoSet，
	 * 如果发生错误的话就返回一个空的InfoSet。
	 */
	public DoubleLoopLinkedInfoSet merge(DoubleLoopLinkedInfoSet infoSet, IInfoFilter filter) {
		DoubleLoopLinkedInfoSet resultSet = new DoubleLoopLinkedInfoSet();
		 if (infoSet == null || filter == null){
			MyLogger.log("合并InfoSet的时候，参数存在null，合并InfoSet失败。请检查："
					+ "InfoSet infoSet == null: " + (infoSet == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return resultSet;
		}
		
		 CopyTraverser copyTraverser = new CopyTraverser(resultSet);
		
		traverseInfo(copyTraverser, filter);
		infoSet.traverseInfo(copyTraverser, filter);
		
		return resultSet;
	}
	
	/**
	 * 遍历InfoSet中的所有Info对象。
	 * @param traverser 遍历的具体操作在这个IInfoTraverser的traverserInfo()方法中的的定义。
	 * @param filter 过滤器，所有不满足过滤器的Info都不会被遍历。
	 * @return 成功遍历返回1，参数存在null返回0。
	 */
	@Override
	public int traverseInfo(IInfoTraverser traverser, IInfoFilter filter){
		if (traverser == null || filter == null){
			MyLogger.log("遍历DLLInfoSet的时候，参数存在null遍历并InfoSet失败。请检查："
					+ "IInfoTraverser traverser == null: " + (traverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		
		DoubleLinkedInfo checkPointer = head.next;
		while(checkPointer != head){
			if (filter.check(checkPointer)){
				traverser.traverse(checkPointer);
			}
			checkPointer = checkPointer.next;
		}
		return 1;
	}
	
	/**
	 * 通过一个可限制的遍历者来遍历信息体，
	 * 如果循环遍历的过程中发现遍历者被限制了，
	 * 就立即返回0，结束遍历。
	 * @param limitedTraverser 
	 * 		可限制的遍历者。
	 * @param filter 
	 * 		过滤器，过滤掉不想要遍历的信息体。
	 * @return
	 * 		参数为null或者遍历者受到限制时都返回0，
	 * 		其他情况返回1。
	 */
	public int limitedTraverseInfo(ILimitedTraverser limitedTraverser, IInfoFilter filter){
		if (limitedTraverser == null || filter == null){
			MyLogger.log("遍历DLLInfoSet的时候，参数存在null遍历并InfoSet失败。请检查："
					+ "ILimitedTraverser limitedTraverser == null: " + (limitedTraverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		
		DoubleLinkedInfo checkPointer = head.next;
		while(checkPointer != head){
			if (filter.check(checkPointer)){
				if (limitedTraverser.isNotLimited()){
					limitedTraverser.traverse(checkPointer);
				} else {
					//遍历者已经被限制，直接返回结束方法。
					return 0;
				}
			}
			checkPointer = checkPointer.next;
		}
		return 1;
	}

	/**
	 * 将信息集合中的信息体用数组返回出来，
	 * @return 
	 * 		返回的类型是InfoWithContainer类型的数组，
	 * 		如果这个集合中没有信息体即num== 0，
	 * 		返回的数组长度为0，可以通过数组的length成员判断。
	 */
	@Override
	public IInfo[] toInfoArray() {
		if (num == 0){
			return new InfoWithContainer[0];
		}
		InfoWithContainer[] infoArray = new InfoWithContainer[num];
		DoubleLinkedInfo checkPointer = head.next;
		for(int i = 0; checkPointer != head; ++i, checkPointer = checkPointer.next){
			infoArray[i] = new InfoWithContainer(checkPointer.getContainer());
		}
		return infoArray;
	}
	
	/**
	 * 返回当前集合中的元素数量。
	 * @return
	 */
	public int getNum(){
		return num;
	}
}
