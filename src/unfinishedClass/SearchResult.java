package unfinishedClass;

import basicInterface.IInfoSet;
import basicTool.MyLogger;
import info.infoTool.AllTrueFilter;
import info.infoTool.CopyTraverser;
import infoInterface.IInfoTraverser;
import infoInterface.ILimitedTraverser;
import infoSet.InfoSearchTree;

public class SearchResult {
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
	public IInfoSet getLimitedResult(int num){
		InfoSetSpecificByIndex resultSet = new InfoSetSpecificByIndex();
		if (num >= 0){
			ILimitedTraverser traverser = new LimitedCopyTraverser(num, resultSet);
			for(InfoSearchTree tree: searchTree){
				if (tree != null){
					tree.specialLimitedTraverseInfo(traverser, new AllTrueFilter());
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
	public IInfoSet getAllResult(){
		InfoSetSpecificByIndex resultSet = new InfoSetSpecificByIndex();
		IInfoTraverser traverser = new CopyTraverser(resultSet);
		for(InfoSearchTree tree: searchTree){
			if (tree != null){
				tree.specialTraverseInfo(traverser, new AllTrueFilter());
			}
		}
		return resultSet;
	}
}
