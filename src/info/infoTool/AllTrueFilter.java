package info.infoTool;

import basicTool.MyLogger;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;

/**
 * 这个过滤器对于任何非null的Info对象都返回true
 * @author 75309
 *
 */
public class AllTrueFilter implements IInfoFilter {
	@Override
	public boolean check(IInfo info) {
		if (info == null){
			MyLogger.log("AllTrueFilter检查的Info对象是空指针，默认返回false。");
			return false;
		}
		return true;
	}

}
