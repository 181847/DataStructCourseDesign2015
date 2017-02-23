package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.AllTrueFilter;
import info.infoTool.NameGetter;
import infoInterface.IInfo;
import infoInterface.IInfoGetter;
import infoSet.DoubleLoopLinkedInfoSet;
import infoSet.InfoSearchTree;


public class SearchLog extends InfoSearchTree {
	IInfoGetter keyGetter;

	public SearchLog(IInfoGetter keyGetter) {
		super('\0');
		this.keyGetter = keyGetter;
	}
	
	/**
	 * 向当前搜索目录中添加一个信息，
	 * 这个信息会根据keyGetter获取的字符串来将它插入这个搜索树中。
	 * @param info 
	 * 		要插入的信息。
	 * @return
	 * 		如果成功返回1；
	 * 		info为null，或者插入失败返回0。
	 */
	public int putInfo(IInfo info){
		//TODO
		if (info == null){
			MyLogger.logError("SearchLog调用addSearchInfo()，"
					+ "但是参数Info为null，"
					+ "无法添加搜索目录。");
			return 0;
		}
		if (insertInfo(info, keyGetter) != 1){
			MyLogger.logError("SearchLog插入信息失败。");
			return 0;
		}
		return 1;
	}
	
	/**
	 * 根据传入的字符串,
	 * 找到相应的字符串路径上的树.
	 * @param searchInfo
	 * 		要搜索的字符串路径。
	 * @return
	 * 		指定字符串路径的树节点，
	 * 		如果没找到，或者参数为null或者空，就返回null。
	 */
	public InfoSearchTree searchInfo(String searchInfo){
		if (searchInfo == null){
			MyLogger.logError("SearchLog搜索失败，字符串参数为null。");
			return null;
		}
		if (searchInfo.isEmpty()){
			MyLogger.logError("SearchLog搜索失败，字符串参数为空串。");
			return null;
		}
		return getTree(searchInfo.toCharArray(), 0);
	}
	
	/**
	 * 将在指定路径结尾的信息体全部删除。
	 * @param searchInfo
	 * 		字符串路径。
	 * @return
	 * 		参数字符串为null或者空串时返回0，
	 * 		如果删除时发生任何多无也返回0，
	 * 		删除成功返回1。
	 */
	public int deleteInfo(String searchInfo){
		if (searchInfo == null){
			MyLogger.logError("SearchLog删除失败，字符串参数为null。");
			return 0;
		}
		if (searchInfo.isEmpty()){
			MyLogger.logError("SearchLog删除失败，字符串参数为空串。");
			return 0;
		}
		return soloDelete(searchInfo.toCharArray(), 0, "", new NameGetter(), new AllTrueFilter());
	}
}
