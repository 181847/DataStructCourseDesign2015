package basicInterface;

import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoInterface.IInfoTraverser;

/**
 * 所有信息集合体都必须要有的方法。
 */
public interface IInfoSet {
	/**
	 * 搜索整个信息集合。
	 * @param searchInfo
	 * 		搜索所依据的字符串，
	 * 		如果字符串为空串，表示搜索所有的信息体，
	 * 		是否将所有的信息体返回由filter决定。
	 * @param getter
	 * 		用于提取信息体中的信息（例如提取学生对象的名字 字符串）来和searchInfo做比较的对象，
	 * 		只有提取出来的信息和searchInfo相同，
	 * 		这个信息体才可能被选中。
	 * @param filter
	 * 		如果提取出来的信息和searchInfo相同，
	 * 		这个信息体需要再次和通过filter的一个方法来判断这个信息是否符合搜索的要求，
	 * 		例如某个filter可能会对所有的男学生返回false，
	 * 		那么如果在此次搜索过程中，
	 * 		男学生将不会被加入最终的搜索结果。
	 * @return
	 * 		搜索得到的信息体集合。
	 */
	public IInfoSet search(String searchInfo, IInfoGetter getter, IInfoFilter filter);
	
	/**
	 * 遍历信息体集合中所有的信息，
	 * 对filter返回false的对象都不会被遍历到。
	 * @param traverser
	 * 		traverser以一个IInfo对象为参数，
	 * 		针对IInfo中的信息来执行操作（比如说打印信息到控制台、复制信息......）。
	 * @param filter
	 * 		过滤对象，对filter返回false的信息将不会被遍历到。
	 * @return
	 * 		成功返回1。
	 */
	public int traverseInfo(IInfoTraverser traverser, IInfoFilter filter);
	
	/**
	 * 将信息体集合内部的信息以一个IInfo数组返回出来。
	 * @return
	 * 		内部的所有信息的数组。
	 */
	public IInfo[] toInfoArray();
}
