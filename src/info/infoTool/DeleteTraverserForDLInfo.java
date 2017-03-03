package info.infoTool;

import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import infoSet.DoubleLoopLinkedInfoSet;

/**
 * 此类专门用于InfoSetSpecificByIndex类当中的registArea，
 * registArea保存的信息都是DoubleLinkedInfo类型，
 * 通过这个遍历者，
 * 将所有遍历到的内部的container（DoubleLinkedInfo）从
 * 包含这个节点（container）的双重链表中删除。
 */
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
