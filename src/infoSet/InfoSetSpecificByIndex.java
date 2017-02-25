package infoSet;

import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.DeleteTraverserForDLInfo;
import info.infoTool.IndexGetter;
import info.infoTool.InnerFilterForInfo;
import info.infoTool.InnerGetterForInfo;
import info.infoTool.NameGetter;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import unfinishedClass.InnerCopyTraverserForIInfo;

/**
 * 本类并不能依靠Index参数获取相关信息体，
 * 本类只是利用Index的数据确保这个集合体内部的信息体的序号是唯一的。
 * @author 75309
 *
 */
public class InfoSetSpecificByIndex extends DoubleLoopLinkedInfoSet{
	/**
	 * 这个搜索树中存储的Info对象的Container是
	 * DoubleLinedInfo对象，也就是说对iSTForIndex的Info对象进行一些操作的时候不能直接利用普通的Getter/Traverser/Filter，
	 * 而是要使用专门用DLInfo的对应的Getter/Traverser/Filter。
	 */
	InfoSearchTree registArea;
	
	public InfoSetSpecificByIndex(){
		registArea = new InfoSearchTree('\0');
	}
	
	/**
	 * 插入带序号的信息体，如果已经存在相同序号的信息体的话，插入会失败。
	 * @param newInfo
	 * 		要插入的信息体。
	 * @return
	 * 		info参数为null，返回0；
	 * 		info参数的container为null，返回-1；
	 * 		info参数中的container不是IIndexHolder类型的子类，返回-2；
	 * 		存在同序号对象，返回-3。
	 */
	@Override
	public int insertInfo(IInfo newInfo){
		if (newInfo == null){
			MyLogger.logError("InfoSetSpecificByIndex插入的元素为null，"
					+ "插入失败。");
			return 0;
		}
		
		Object container = newInfo.getContainer();
		if (container == null){
			MyLogger.logError("InfoSetSpecificByIndex插入的元素中没有信息，"
					+ "插入失败。");
			return -1;
		}
		
		String index = new IndexGetter().pickMessage(newInfo);
		if (index.isEmpty()){
			MyLogger.logError("InfoSetSpecificByIndex插入的元素中的container不是IIndexHolder的子类，"
					+ "插入失败。");
			return -2;
		}
		
		//准备搜素序号信息体
		char[] indexArray = index.toCharArray();
		
		if (! haveIndex(indexArray)){
			DoubleLinkedInfo newDLInfo = new DoubleLinkedInfo(newInfo.getContainer());
			newDLInfo.next = head.next;
			newDLInfo.prec = head;
			head.next = newDLInfo;
			newDLInfo.next.prec = newDLInfo;
			++num;
			
			registArea.insertInfoAccordToCharArray(index.toCharArray(), 0, new InfoWithContainer(newDLInfo));
			return 1;
			
		} else {
			MyLogger.logError("InfoSetSpecificByIndex中已经包含同序号的信息，"
					+ "插入失败。"
					+ "插入元素的序号：" + index);
			return -3;
		}
	}//insertInfo
	
	/**
	 * 检查序号是否已经存在。
	 * @param indexArray 
	 * 		序号数组。
	 * @return 
	 * 		序号已存在返回真，
	 * 		序号不存在返回假。
	 */
	public boolean haveIndex(char[] indexArray){
		DoubleLoopLinkedInfoSet searchResult = 
				registArea.getSoloInfoSet(indexArray, 0);
		return ! (searchResult == null || searchResult.isEmpty());
	}
	
	/**
	 * 删除这个InfoSet中的指定要求的对象，
	 * 将所有被删除的对象用一个DLLInfoSet返回。
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
	@Override
	public DoubleLoopLinkedInfoSet delete(String searchInfo, IInfoGetter getter, IInfoFilter filter){
		DoubleLoopLinkedInfoSet deletedInfoSet = super.delete(searchInfo, getter, filter);
		if ( ! deletedInfoSet.isEmpty() ){
			registArea.delete(searchInfo, new InnerGetterForInfo(getter), new InnerFilterForInfo(filter));
		}
		return deletedInfoSet;
	}
	
	/**
	 * 传入一个指定的序号，
	 * 将指定序号的信息体从对象内删除，
	 * 将这个对象通过一个DLLInfoSet返回，精确删除。
	 * @param index
	 * @return
	 */
	public DoubleLoopLinkedInfoSet deleteIndex(String index){
		char[] indexArray = index.toCharArray();
		DoubleLoopLinkedInfoSet infoSet = registArea.getSoloInfoSet(indexArray, 0);
		DeleteTraverserForDLInfo deleteTraverser = new DeleteTraverserForDLInfo();
		
		if ( ! (infoSet == null || infoSet.isEmpty()) ){
			infoSet.traverseInfo(deleteTraverser, new AllTrueFilter());
			registArea.soloDelete(indexArray, 0, "", new InnerGetterForInfo(new NameGetter()), new AllTrueFilter());
			infoSet = deleteTraverser.getDLLInfoSet();
			num -= infoSet.getNum();
		}
		return deleteTraverser.getDLLInfoSet();
	}
	
	/**
	 * 获取指定序号的对象。
	 * @param index
	 * 		要获取的对象的序号。
	 * @return
	 * 		包含指定序号的信息体的集合。
	 */
	public DoubleLoopLinkedInfoSet getIndex(String index){
		DoubleLoopLinkedInfoSet searchResult = registArea.getSoloInfoSet(index.toCharArray(), 0);
		if (searchResult == null){
			return new DoubleLoopLinkedInfoSet();
		}
		InnerCopyTraverserForIInfo innerCopier = new InnerCopyTraverserForIInfo();
		
		searchResult.traverseInfo(innerCopier, new AllTrueFilter());
		return innerCopier.getcontainerSet();
	}
}
