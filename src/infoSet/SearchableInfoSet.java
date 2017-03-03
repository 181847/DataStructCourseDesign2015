package infoSet;

import basicTool.MyLogger;
import collegeComponent.tool.getter.IndexGetter;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;

/**
 * 本类继承于InfoSetSpecificByIndex，
 * 内部根据构造方法中的传入的Getter来生成相应的搜索目录，
 * 并且添加搜索方法，简化搜索过程，将各个搜索目录中的搜索结果
 * （搜索结果都是InfoSearchTree类型）
 * 集合起来，生成一个SearchResult对象传递出来作为搜索结果，
 * SearchResult内部定义了针对多个InfoSearchTree来获取指定信息集合的方法。
 */
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
	
	/**
	 * 由参数来决定对那些搜索目录进行搜索。
	 * @param searchInfo
	 * 		搜索的主要依据。
	 * @param selectedTree
	 * 		boolean数组，用来表明那些搜索目录能够被搜索。
	 * @return
	 * 		如果selectedTree和搜索目录的数量不一致，
	 * 		返回一个空的SearchResult。
	 */
	public SearchResult search(String searchInfo, boolean[] selectedTree){
		if (selectedTree.length != searchLogs.length){
			MyLogger.logError("在SearchableInfoSet中进行搜索目录的选择性搜索时出错，"
					+ "参数boolean[] selectedTree数组的长度和搜索目录的数量不一致。");
			return new SearchResult(new InfoSearchTree[0]);
		}
		
		int treeNum = searchLogs.length;
		InfoSearchTree[] resultTreeArray = new InfoSearchTree[treeNum];
		for (--treeNum; treeNum >= 0; --treeNum){
			if (selectedTree[treeNum]){
				resultTreeArray[treeNum] = searchLogs[treeNum].searchInfo(searchInfo);
			}
		}
		return new SearchResult(resultTreeArray);
	}
	
	/**
	 * 插入信息体，同时为每个信息体都要建立相应的搜索目录信息。
	 * @return
	 * 		参数为null返回0；
	 * 		插入失败返回-1；
	 * 		插入成功返回1。
	 */
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
		return 1;
	}
	
	/**
	 * 删除指定序号的信息体，
	 * 并且在所有搜索目录中将这个信息体的去掉。
	 * @param index
	 * 		要删除的信息体的序号。
	 * @return
	 * 		包含删除的信息体的信息集合。
	 */
	@Override
	public DoubleLoopLinkedInfoSet deleteIndex(String index){
		DoubleLoopLinkedInfoSet deleteSet = super.deleteIndex(index);
		IInfo[] deleteInfos = deleteSet.toInfoArray();
		if (deleteInfos.length == 1){
			for (SearchLogForIndex sl: searchLogs){
				sl.deleteInfo(deleteInfos[0]);
			}
		}
		return deleteSet;
	}
	
	@Override
	public DoubleLoopLinkedInfoSet delete(String searchInfo, IInfoGetter getter, IInfoFilter filter){
		DoubleLoopLinkedInfoSet deleteSet = super.delete(searchInfo, getter, filter);
		IInfo[] deleteInfos = deleteSet.toInfoArray();
		for (IInfo info: deleteInfos){
			for (SearchLogForIndex sl: searchLogs){
				sl.deleteInfo(info);
			}
		}
		return deleteSet;
	}
}
