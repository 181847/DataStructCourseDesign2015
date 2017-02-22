package unfinishedClass;

import basicTool.MyLogger;
import info.infoTool.IndexGetter;
import infoInterface.IInfo;
import infoSet.DoubleLoopLinkedInfoSet;
import infoSet.InfoSearchTree;

public class InfoSetSpecificByIndex extends DoubleLoopLinkedInfoSet{
	InfoSearchTree iSTForIndex;
	
	public InfoSetSpecificByIndex(){
		iSTForIndex = new InfoSearchTree('\0');
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
		
		DoubleLoopLinkedInfoSet searchResult = 
				iSTForIndex.getSpecificInfoSet(index.toCharArray(), 0);
		if (searchResult == null || searchResult.isEmpty()){
			super.insertInfo(newInfo);
			this.iSTForIndex.insertInfo(newInfo, new IndexGetter());
			return 1;
		} else {
			MyLogger.logError("InfoSetSpecificByIndex中已经包含同序号的信息，"
					+ "插入失败。"
					+ "插入元素的序号：" + index);
			return -3;
		}
	}
}
