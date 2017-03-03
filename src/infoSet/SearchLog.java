package infoSet;

import basicTool.MyLogger;
import collegeComponent.tool.getter.NameGetter;
import info.infoTool.AllTrueFilter;
import infoInterface.IInfo;
import infoInterface.IInfoGetter;

/**
 * 本类继承于InfoSearchTree，
 * 利用InfoSearchTree的字符串存储路径，
 * 来实现类似字符串的前端匹配搜素，
 * 内部包含一个Getter，
 * 每个插入的Info通过这个统一的Getter来获取相关的插入路径，
 * 从而使得本SearchLog成为某个信息的搜索目录，
 * 例如设置keyGetter为NameGetter，
 * 就可以将本类改为一个根据名字进行搜索的搜索目录，
 * 如果设置keyGetter为PinyinGetter，
 * 就可以将本类改为一个根据拼音进行搜索的搜索目录。
 */
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
	 * 		如果没找到，或者参数为null，就返回null；
	 * 		如果参数为空串，就返回当前这棵树。
	 */
	public InfoSearchTree searchInfo(String searchInfo){
		if (searchInfo == null){
			MyLogger.logError("SearchLog搜索失败，字符串参数为null。");
			return null;
		}
		if (searchInfo.isEmpty()){
			return this;
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
