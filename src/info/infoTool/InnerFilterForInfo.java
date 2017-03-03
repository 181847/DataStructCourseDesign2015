package info.infoTool;

import basicTool.MyLogger;
import info.DoubleLinkedInfo;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;

/**
 * 目前此类专门用于InfoSetSpecificByIndex类当中的registArea，
 * registArea保存的信息都是DoubleLinkedInfo类型，
 * 通过这个过滤者，可以针对container（DoubleLinkedInfo类型）的container
 * 进行过滤，过滤规则由构造方法的参数innerFilter决定。
 */
public class InnerFilterForInfo extends AbstractFilter {
	IInfoFilter innerFilter;
	
	public InnerFilterForInfo(IInfoFilter innerFilter){
		if (innerFilter == null){
			MyLogger.logError("InnerGetterForDLInfo初始化错误，"
					+ "参数为空，请指定一个Info的filter为参数。");
		}
		this.innerFilter = innerFilter;
	}

	@Override
	public boolean dealWithContainer(Object container) {
		if (container instanceof IInfo){
			if (innerFilter == null){
				MyLogger.logError("InnerFilterForInfo内部成员为null，"
						+ "不能正常过滤信息体。");
			}
			if (container instanceof DoubleLinkedInfo){
				return innerFilter.check((IInfo)container);
			}
		} else {
			MyLogger.logError("InnerFilterForInfo的container不是IInfo的子类，"
					+ "无法过滤信息体。");
		}
		return false;
	}

}
