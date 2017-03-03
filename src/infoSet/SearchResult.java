package infoSet;

import basicTool.MyLogger;
import info.infoTool.CopyTraverser;
import info.infoTool.LimitedCopyTraverser;
import infoInterface.IInfoFilter;
import infoInterface.IInfoTraverser;
import infoInterface.ILimitedTraverser;

/**
 * 本类用于作为SearchableInfoSet的搜索结果，
 * SearchableInfoSet的搜索是在各个搜索目录（InfoSearchTree）中进行的，
 * 得到的结果也是一个InfoSearchTree数组，
 * 代表在各个目录中的搜索结果，
 * SearchResult收集这些InfoSearchTree，
 * 并且提供一系列的方法来将这些InfoSearchTree转换为一个不包含重复序号的InfoSet类型，
 * 即InfoSetSpecificByIndex类型。
 * @author 75309
 *
 */
public class SearchResult{
	InfoSearchTree[] searchTree;
	
	/**
	 * 用一个InfoSearchTree表示所有的搜索结果，
	 * 如果参数为null的话，就会默认搜索结果为空。
	 * @param searchTree
	 * 		包含搜索结果的搜索树数组。
	 */
	public SearchResult(InfoSearchTree[] searchTree){
		if (searchTree == null){
			MyLogger.logError("SearchResult初始化失败，"
					+ "searchTree数组为null，"
					+ "无法判断搜索结果，默认分配一个长度为1的空搜索结果");
			this.searchTree = new InfoSearchTree[1];
		} else {
			this.searchTree = searchTree;
		}
	}
	
	/**
	 * 从搜素结果中获取最多num个信息体，
	 * 这些信息体必须包含序号信息，
	 * 而且最终返回的IInfoSet中不会出现重复序号的信息体。
	 * @param num
	 * 		要获取的信息体的极限数量。
	 * @return
	 * 		返回最多包含num个信息体的IInfoSet，
	 * 		最终也可能一个信息体都没有，但是返回值一定不为null。
	 */
	public InfoSetSpecificByIndex getLimitedResult(int num, IInfoFilter filter){
		InfoSetSpecificByIndex resultSet = new InfoSetSpecificByIndex();
		if (num >= 0){
			ILimitedTraverser traverser = new LimitedCopyTraverser(num, resultSet);
			for(InfoSearchTree tree: searchTree){
				if (tree.getCurChar() == '\0'){
					tree.traverseInfo(traverser, filter);
				} else {
					tree.specialTraverseInfo(traverser, filter);
				}
				if (traverser.isLimited()){
					break;
				}
			}
		}
		return resultSet;
	}
	
	/**
	 * 获取搜索得到的所有信息。
	 * @return
	 * 		包含搜索得到的所有信息体的IInfoSet。
	 */
	public InfoSetSpecificByIndex getAllResult(IInfoFilter filter){
		InfoSetSpecificByIndex resultSet = new InfoSetSpecificByIndex();
		IInfoTraverser traverser = new CopyTraverser(resultSet);
		for(InfoSearchTree tree: searchTree){
			if (tree != null){
				if (tree.getCurChar() == '\0'){
					tree.traverseInfo(traverser, filter);
				} else {
					tree.specialTraverseInfo(traverser, filter);
				}
			}
		}
		return resultSet;
	}
}
