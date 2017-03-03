package infoSet;

import basicTool.MyLogger;
import collegeComponent.tool.getter.IndexGetter;
import info.infoTool.AllTrueFilter;
import infoInterface.IInfo;
import infoInterface.IInfoGetter;

/**
 * 这个搜索目录专门为信息体包含的container是IIndexHolder的子类，
 * 添加一个删除索引的方法，
 * 通过传入一个基本信息没有改变的Info对象，
 * 将这个Info对象的索引从本搜索目录中删除。
 */
public class SearchLogForIndex extends SearchLog {

	/**
	 * 创建目录时要用到信息体的那一部分信息作为字符路径。
	 * @param keyGetter
	 * 		获取作为字符路径的Getter。
	 */
	public SearchLogForIndex(IInfoGetter keyGetter) {
		super(keyGetter);
	}
	
	/**
	 * 将在指定路径结尾中和参数的Index相同的信息体删除。
	 * @param searchInfo
	 * 		字符串路径。
	 * @return
	 * 		参数字符串为null或者空串时返回0，
	 * 		如果删除时发生任何错误也返回0，
	 * 		删除成功返回1。
	 */
	public int deleteInfo(IInfo infoWithIndex){
		if (infoWithIndex == null){
			MyLogger.logError("SearchLogForIndex调用deleteInfo(IInfo)时发生错误，参数为null");
			return 0;
		}
		String index = new IndexGetter().pickMessage(infoWithIndex);
		String searchInfo = keyGetter.pickMessage(infoWithIndex);
		if (searchInfo.isEmpty()){
			MyLogger.logError("SearchLogForIndex调用deleteInfo(IInfo)时发生错误，"
					+ "获取的字符路径为空。");
			return -1;
		}
		if (soloDelete(searchInfo.toCharArray(), 0, 
				index, new IndexGetter(), new AllTrueFilter()) 
				== 1){
			return 1;
		}
		return 0;
	}
}
