package basicInterface;

import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoInterface.IInfoTraverser;

public interface IInfoSet {
	public IInfoSet search(String searchInfo, IInfoGetter getter, IInfoFilter filter);
	public int traverseInfo(IInfoTraverser traverser, IInfoFilter filter);
	public IInfo[] toInfoArray();
}
