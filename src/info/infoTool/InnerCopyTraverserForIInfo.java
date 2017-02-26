package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoSet.DoubleLoopLinkedInfoSet;

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
