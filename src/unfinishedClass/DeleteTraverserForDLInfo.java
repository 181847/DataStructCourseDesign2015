package unfinishedClass;

import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import info.infoTool.AbstractTraverser;
import infoSet.DoubleLoopLinkedInfoSet;

public class DeleteTraverserForDLInfo extends AbstractTraverser {
	DoubleLoopLinkedInfoSet deleteResultSet;
	
	public DeleteTraverserForDLInfo(){
		deleteResultSet = new DoubleLoopLinkedInfoSet();
	}

	@Override
	public int dealWithContainer(Object container) {
		if (container instanceof DoubleLinkedInfo){
			DoubleLinkedInfo infoToDelete = (DoubleLinkedInfo) container;
			DoubleLinkedInfo prec = infoToDelete.getPrec();
			DoubleLinkedInfo next = infoToDelete.getNext();
			
			if (prec != null && next != null){
				prec.setNext(next);
				next.setPrec(prec);
				deleteResultSet.insertInfo(infoToDelete);
				return 1;
				
			} else {
				MyLogger.logError("DeleteTraverserForDLInfo将要删除的DLInfo并没有正确连接到一个循环列表，"
					+ "无法将其从DoubleLoopLinkedInfoSet中删除。");
				return 0;
				
			}
			
		} else {
			MyLogger.logError("DeleteTraverserForDLInfo遍历到的container不是DoubleLinkedInfo类型，"
					+ "无法将其从DoubleLoopLinkedInfoSet中删除。");
		}
		return 0;
	}
	
	public DoubleLoopLinkedInfoSet getDLLInfoSet(){
		return deleteResultSet;
	}

}
