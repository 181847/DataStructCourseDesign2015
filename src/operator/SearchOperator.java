package operator;

import basicTool.MyLogger;
import collegeComponent.College;
import infoSet.InfoSearchTree;
import infoSet.SearchResult;

public abstract class SearchOperator extends CollegeOperator {
	/**
	 * 搜索需要的字符串。
	 */
	public String searchInfo;
	
	/**
	 * 搜索涉及的搜素目录，
	 * 第几号boolean为真，
	 * 相应序号的搜索目录就会被搜索。
	 */
	public boolean[] selectedLog;
	
	/**
	 * 上次搜索得到的结果会在这里存储。
	 */
	public SearchResult searchResult;
	
	public SearchOperator(College college) {
		super(college);
		 //一开始就将搜索结果设为一个空结果。
		searchResult  = new SearchResult(new InfoSearchTree[0]);
	}
	
	@Override
	public abstract int operate();
	
	/**
	 * 选择搜索涉及的搜索目录。
	 * @param select
	 * 		要选择的目录序号，
	 * 		-1表示全选。
	 * @return
	 * 		如果序号不符合规范就返回0。
	 */
	public int selectSearchLog(int select){
		if (select == -1){
			for (int i = selectedLog.length - 1; i >= 0; --i){
				selectedLog[i] = true;
			}
		} else if (select >= 0 && select < selectedLog.length){
			selectedLog[select] = true;
		} else {
			MyLogger.logError("SearchOperatorForStudents选择搜索目录的时候参数不合规范，"
					+ "请检查参数 select：" + select
					+ "selectedLog.length：" + selectedLog.length);
			return 0;
		}
		
		return 1;
	}
	
	/**
	 * 取消搜索设置的搜索目录。
	 * @param select
	 * 		要选择的目录序号，
	 * 		-1表示全选。
	 * @return
	 * 		如果序号不符合规范就返回0。
	 */
	public int deselectSearchLog(int deselect){
		if (deselect == -1){
			for (int i = selectedLog.length; i >= 0; --i){
				selectedLog[i] = false;
			}
		} else if (deselect >= 0 && deselect < selectedLog.length){
			selectedLog[deselect] = false;
		} else {
			MyLogger.logError("SearchOperatorForStudents选择搜索目录的时候参数不合规范，"
					+ "请检查参数 select：" + deselect
					+ "selectedLog.length：" + selectedLog.length);
			return 0;
		}
		
		return 1;
	}

	/**
	 * 获取搜索结果。
	 * @return
	 */
	public SearchResult getResult(){
		return searchResult;
	}

	/**
	 * @return
	 * 		获取上次搜索用到的搜索信息。
	 */
	public String getSearchInfo() {
		return searchInfo;
	}

	/**
	 * 设置搜索要用到的信息。
	 * @param
	 * 		搜索要用到的信息。
	 */
	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
}
