package info.infoTool;

import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import infoInterface.IInfo;
import infoInterface.IInfoGetter;

/**
 * 目前此类专门用于InfoSetSpecificByIndex类当中的registArea，
 * registArea保存的信息都是DoubleLinkedInfo类型，
 * 通过这个Getter，可以针对container（DoubleLinkedInfo类型）的container
 * 进行信息提取，提取方法由构造方法的参数innerGetter决定。
 */
public class InnerGetterForInfo extends AbstractGetter {
	IInfoGetter innerGetter;
	
	public InnerGetterForInfo(IInfoGetter innerGetter){
		if (innerGetter == null){
			MyLogger.logError("InnerGetterForDLInfo初始化错误，"
					+ "参数为空，请指定一个DLInfo的getter为参数。");
		}
		this.innerGetter = innerGetter;
	}

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof IInfo){
			if (innerGetter == null){
				MyLogger.logError("InnerGetterForDLInfo内部成员为null，"
						+ "不能提取container（DLInfo类型）的内部信息。");
			}
			if (container instanceof DoubleLinkedInfo){
				return innerGetter.pickMessage((IInfo)container);
			}
		} else {
			MyLogger.logError("InnerGetterForDLInfo的container不是IInfo的子类，"
					+ "无法提取信息。");
		}
		return "";
	}

}
