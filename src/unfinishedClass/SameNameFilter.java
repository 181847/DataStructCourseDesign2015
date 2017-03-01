package unfinishedClass;

import basicInterface.INameHolder;
import basicTool.MyLogger;
import info.infoTool.AbstractFilter;

public class SameNameFilter extends AbstractFilter {
	public String name;
	
	public SameNameFilter(String name){
		this.name = name;
	}

	@Override
	public boolean dealWithContainer(Object container) {
		if (container instanceof INameHolder){
			return name.equals(((INameHolder) container).getName());
		} else {
			MyLogger.logError("SameNameFilter遍历的对象不是INameHolder的子类，"
					+ "无法比较名字。");
		}
		return false;
	}

}
