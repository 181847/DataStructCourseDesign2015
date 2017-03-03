package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoSet.DoubleLoopLinkedInfoSet;

/**
 * 目前此类专门用于InfoSetSpecificByIndex类当中的registArea，
 * registArea保存的信息都是DoubleLinkedInfo类型，
 * 通过这个遍历者，将所有遍历到的container（DoubleLinkedInfo类型）内部的container
 * （container的container）放到成员变量containerSet中。
 */
public class InnerCopyTraverserForIInfo extends AbstractTraverser {
	DoubleLoopLinkedInfoSet containerSet;

	public InnerCopyTraverserForIInfo(){
		containerSet = new DoubleLoopLinkedInfoSet();
	}
	
	public InnerCopyTraverserForIInfo(DoubleLoopLinkedInfoSet containerSet){
		this.containerSet = containerSet;
	}
	
	public DoubleLoopLinkedInfoSet getcontainerSet(){
		return containerSet;
	}
	
	public void setcontainerSet(DoubleLoopLinkedInfoSet containerSet){
		this.containerSet = containerSet;
	}
	
	@Override
	public int dealWithContainer(Object container) {
		if (! (container instanceof IInfo)){
			MyLogger.log("错误：InnerCopyTraverserForDLInfo读取info的container的类型不是IInfo，无法读取信息。");
			return 0;
		}
		containerSet.insertInfo((IInfo) container);
		return 0;
	}

}
