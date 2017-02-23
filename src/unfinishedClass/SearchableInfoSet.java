package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.IndexGetter;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoSet.DoubleLoopLinkedInfoSet;
import infoSet.InfoSearchTree;

public class SearchableInfoSet extends InfoSetSpecificByIndex {
	public SearchLogForIndex[] searchLogs;
	
	/**
	 * @param gettersForSearchLog
	 * 		用于创建搜索目录的各个InfoGetter
	 */
	public SearchableInfoSet(IInfoGetter[] gettersForSearchLog){
		if ( checkGetters(gettersForSearchLog) ){
			searchLogs = new SearchLogForIndex[gettersForSearchLog.length];
			
			for (int i = gettersForSearchLog.length - 1; i >= 0; --i){
				searchLogs[i] = new SearchLogForIndex(gettersForSearchLog[i]);
			}
		} else {
			MyLogger.logError("searchableInfoSet初始化错误，"
					+ "请正确指定用于创建搜索目录的InfoGetter，"
					+ "现在默认创建一个序号搜索目录");
			searchLogs = new SearchLogForIndex[]{new SearchLogForIndex(new IndexGetter())};
		}
	}

	/**
	 * 检查用于创建搜索目录的InfoGetter数组是否正常。
	 * @param gettersForSearchLog
	 * 		被检查的Getter数组。
	 * @return
	 * 		如果参数为null，或者数组中存在空指针，
	 * 		返回false。
	 */
	private boolean checkGetters(IInfoGetter[] gettersForSearchLog) {
		if (gettersForSearchLog == null){
			MyLogger.logError("用于创建搜索目录的InfoGetter数组为null，无法创建搜索目录。");
			return false;
		}
		
		for (IInfoGetter getter:gettersForSearchLog){
			
			if (getter == null){
				MyLogger.logError("用于创建搜索目录的InfoGetter数组中存在空指针，无法正常创建搜索目录。");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 传入一个字符串，
	 * 将各个搜索目录中有关这个字符串的搜索树以数组的形式传递出来。
	 * @param searchInfo
	 * 		要搜索的信息。
	 * @return
	 * 		搜索得到的搜索树数组，
	 * 		数组当中可能有null，请注意分辨。
	 */
	public SearchResult search(String searchInfo){
		int treeNum = searchLogs.length;
		InfoSearchTree[] resultTreeArray = new InfoSearchTree[treeNum];
		for (--treeNum; treeNum >= 0; --treeNum){
			resultTreeArray[treeNum] = searchLogs[treeNum].searchInfo(searchInfo);
		}
		return new SearchResult(resultTreeArray);
	}
	
	@Override
	public int insertInfo(IInfo info){
		if (info == null){
			MyLogger.logError("SearchableInfoSet插入的元素为null，"
					+ "插入失败。");
			return 0;
		}
		
		Object container = info.getContainer();
		if (container == null){
			MyLogger.logError("SearchableInfoSet插入的元素中没有信息，"
					+ "插入失败。");
			return -1;
		}

		if (super.insertInfo(info) != 1){
			MyLogger.logError("SearchableInfoSet插入Info失败。");
			return -1;
		}
		for (SearchLog sl:searchLogs){
			if (sl.putInfo(info) != 1){
				MyLogger.logError("SearchableInfoSet为新Info创建搜索目录之一失败。");
			}
		}
		return 0;
	}
	
	@Override
	public DoubleLoopLinkedInfoSet deleteIndex(String index){
		//TODO
		return null;
	}
	
	@Override
	public DoubleLoopLinkedInfoSet delete(String searchInfo, IInfoGetter getter, IInfoFilter filter){
		//TODO
		return null;
	}
}
