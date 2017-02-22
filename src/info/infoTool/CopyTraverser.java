package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoTraverser;
import infoSet.DoubleLoopLinkedInfoSet;

/**
 * 这个遍历装置能够将遍历到的Info加入内部存储的DoubleLoopLinkedInfoSet中。
 * @author 75309
 *
 */
public class CopyTraverser implements IInfoTraverser {
	DoubleLoopLinkedInfoSet containerSet;

	public CopyTraverser(DoubleLoopLinkedInfoSet containerSet){
		this.containerSet = containerSet;
	}
	
	public DoubleLoopLinkedInfoSet getcontainerSet(){
		return containerSet;
	}
	
	public void setcontainerSet(DoubleLoopLinkedInfoSet containerSet){
		this.containerSet = containerSet;
	}
	
	@Override
	public int traverse(IInfo info) {
		if (info == null){
			MyLogger.logError("CopyTraverser准备复制一个info对象，"
					+ "但是参数info为null，无法复制。");
			return 0;
		}
		if (info.getContainer() == null){
			MyLogger.logError("CopyTraverser准备复制一个info对象，"
					+ "但是info内部的container为null，没有信息，复制失败。");
			return 0;
		}
		containerSet.insertInfo(info);
		return 1;
	}

}
