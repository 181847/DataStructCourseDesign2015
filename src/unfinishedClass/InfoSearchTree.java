package unfinishedClass;

import basicInterface.IInfo;
import basicInterface.IInfoSet;
import basicTool.AllTrueFilter;
import basicTool.MyLogger;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoInterface.IInfoTraverser;

public class InfoSearchTree implements IInfoSet{
	public InfoSearchTree lChild;
	public InfoSearchTree rChild;
	public InfoSearchTree nextTree;
	public DoubleLoopLinkedInfoSet infoSet;
	public char curChar;
	
	public InfoSearchTree(char curChar){
		this.curChar = curChar;
	}
	
	/**
	 * @return 左子树。
	 */
	public InfoSearchTree getlChild() {
		return lChild;
	}

	/**
	 * 设置左子树。
	 * @param lChild 要设置的左子树。
	 */
	public void setlChild(InfoSearchTree lChild) {
		this.lChild = lChild;
	}

	/**
	 * @return 右子树。
	 */
	public InfoSearchTree getrChild() {
		return rChild;
	}

	/**
	 * 设置右子树。
	 * @param rChild 要设置的右子树。
	 */
	public void setrChild(InfoSearchTree rChild) {
		this.rChild = rChild;
	}

	/**
	 * 获取下一层树，
	 * 下一层的树的字符与本层的字符是相关联的。
	 * @return 下一层的树。
	 */
	public InfoSearchTree getNextTree() {
		return nextTree;
	}

	/**
	 * 设置下一层树。
	 * @param nextTree 要设置的下一层树。
	 */
	public void setNextTree(InfoSearchTree nextTree) {
		this.nextTree = nextTree;
	}

	/**
	 * 获取当前树节点当中存储的InfoSet。
	 * @return infoSet
	 */
	public DoubleLoopLinkedInfoSet getInfoSet() {
		return infoSet;
	}

	/**
	 * 向当前这个树节点的infoSet中插入指定的Info。
	 * @param info 要插入的Info。
	 * @return info为null返回0，
	 * info.container为null返回-1，
	 * 插入成功返回1。
	 */
	public int insertInfoToThisNode(IInfo info) {
		if (infoSet == null){
			infoSet = new DoubleLoopLinkedInfoSet();
		}
		return infoSet.insertInfo(info);
	}

	/**
	 * 获取当前树节点的字符。
	 * @return 当前树节点的字符。
	 */
	public char getCurChar() {
		return curChar;
	}

	/**
	 * 设置当前树节点的字符。
	 * @param curChar 要设置的字符。
	 */
	public void setCurChar(char curChar) {
		this.curChar = curChar;
	}

	/**
	 * 收集所有符合的信息体，
	 * 这个方法先将所有的Info对象集中起来，
	 * 然后统一调用最终集合的DLLInfoSet的同名方法，
	 * @param searchInfo 主要用来检查的对象。
	 * 只有与这个searchInfo相等才算找到。
	 * @param getter 这个对象用来获取Info信息体中指定的信息，
	 * 比如某个InfoGetter用来获取Info中的名字信息，
	 * 有的InfoGetter用来获取Info中的学号信息。
	 * @param filter 过滤器，用于针对Info中的信息返回真假，
	 * 最终的InfoSet会过滤掉所有filter检查结果为false的Info，
	 * 比如某个filter规定如果StudentInfo的年级为大一，
	 * 那么这次搜寻结果就只会有大一的StudentInfo。
	 * @return 查找结果。
	 */
	@Override
	public IInfoSet search(String searchInfo, IInfoGetter getter, IInfoFilter filter) {
		DoubleLoopLinkedInfoSet resultSet = new DoubleLoopLinkedInfoSet();
		
		if (searchInfo == null || getter == null || filter == null){
			MyLogger.log("从InfoSearchTree中搜索Info的时候，参数存在null，删除Info失败。请检查："
					+ "String searchInfo == null: " + (searchInfo == null) 
					+ "IInfoGetter getter == null: " + (getter == null)
					+ "IInfoFilter filter == null: " + (filter == null));
			return resultSet;
		}
		CopyTraverser copyTraverser = new CopyTraverser(resultSet);
		traverseInfo(copyTraverser, new AllTrueFilter());
		
		return resultSet.search(searchInfo, getter, filter);
	}
	
	/**
	 * 递归调用，对树中的每一个元素调用traverser的方法。
	 * @param traverser 遍历的具体操作在这个IInfoTraverser的traverserInfo()方法中的的定义。
	 * @param filter 过滤器，所有不满足过滤器的Info都不会被遍历。
	 * @return 成功遍历返回1，参数存在null返回0。
	 */
	@Override
	public int traverseInfo(IInfoTraverser traverser, IInfoFilter filter) {
		if (traverser == null || filter == null){
			MyLogger.log("遍历InfoSearchTree的时候，参数存在null遍历并InfoSet失败。请检查："
					+ "IInfoTraverser traverser == null: " + (traverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		int[] resultNum = new int[]{1, 1, 1, 1};
		
		if (lChild != null){
			resultNum[0] = lChild.traverseInfo(traverser, filter);
		}
		if (rChild != null){
			resultNum[1] = rChild.traverseInfo(traverser, filter);
		}
		if (nextTree != null){
			resultNum[2] = nextTree.traverseInfo(traverser, filter);
		}
		if (infoSet != null){
			resultNum[3] = infoSet.traverseInfo(traverser, filter);
		}
		
		return resultNum[0]*resultNum[1]*resultNum[2]*resultNum[3];
	}
	
	/**
	 * 这个方法先将树中的所有Info对象集中在DLLInfoSet当中，
	 * 然后调用DLLInfoSet的同名方法。
	 * @return 包含所有信息体的数组。
	 */
	@Override
	public IInfo[] getInfos() {
		DoubleLoopLinkedInfoSet resultSet = new DoubleLoopLinkedInfoSet();
		CopyTraverser copyTraverser = new CopyTraverser(resultSet);
		traverseInfo(copyTraverser, new AllTrueFilter());
		return resultSet.getInfos();
	}
	
	/**
	 * 在当前这棵树的左右子树中搜索包含指定的字符的树。
	 * @param searchChar 要搜索的字符。
	 * @return 包含指定字符的树。
	 */
	public InfoSearchTree searchTreeWith(char searchChar){
		if (searchChar == curChar){
			return this;
		} else if (searchChar < curChar){
			if (lChild == null){
				return null;
			} else {
				return lChild.searchTreeWith(searchChar);
			}
		} else if (searchChar > curChar){
			if (rChild == null){
				return null;
			} else {
				return rChild.searchTreeWith(searchChar);
			}
		}
		
		//由于Java一直要求添加返回语句，
		//添加这样一个返回null的语句。
		return null;
	}
	
	/**
	 * 通过一个字符数组，
	 * 将这个info对象加入这个InfoSearchTree，
	 * 之后可以依靠这个字符数组再次找到这个Info对象。
	 * @param charBuffer 
	 * 		一个与这个Info对象有关的字符数组，
	 * 		一般来说可以是这个Info的某个特征字符串。
	 * @param index 
	 * 		如果是递归的入口的话，
	 * 		这个index应该为0，
	 * 		表示从字符数组的第0个字符开始在树中进行查找插入。
	 * @param info 
	 * 		要插入的Info对象。
	 * @return 
	 * 		如果charBuffer为null，返回0；
	 * 		如果index小于等于0，返回-1；
	 * 		如果index大于等于字符数组的长度，返回-2；
	 * 		如果info为null，返回-3；
	 * 		如果向树节点的infoSet中插入info对象时的结果不是1，
	 * 		返回-4。
	 * 		成功返回1。
	 */
	public int insertInfoAccordToCharArray(char[] charBuffer, int index, IInfo info){
		if (charBuffer == null){
			MyLogger.logError("InfoSearchTree调用insertInfoAccordToCharArray方法，"
					+ "但是参数charBuffer为null，"
					+ "不能根据空串插入Info对象。");
			return 0;
		}//if
		
		if (info == null){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数info为null，"
					+ "不能插入空指针对象。");
			return -3;
		}//if
		
		if (index < 0){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数index小于等于0，"
					+ "不能正常获取字符数组的字符。");
			return -1;
			
		} else if (index >= charBuffer.length){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数index大于等于字符数组的长度，"
					+ "不能正常获取字符数组的字符。");
			return -2;
			
		} else {
			
			if (charBuffer[index] < curChar){//向左子树插入
				//左子树为空，创建新树
				if (lChild == null){
					lChild = new InfoSearchTree(charBuffer[index]);
				}
				return lChild.insertInfoAccordToCharArray(charBuffer, index, info);
				
			}else if (charBuffer[index] > curChar){//向右子树插入
				//右子树为空，创建新树
				if (rChild == null){
					rChild = new InfoSearchTree(charBuffer[index]);
				}
				return rChild.insertInfoAccordToCharArray(charBuffer, index, info);
				
			} else {//向当前树节点插入
				if (index == charBuffer.length - 1){//序号已经到了字符数组的结尾
					//向树节点的infoSet中插入Info对象
					if (insertInfoToThisNode(info) != 1){//插入失败
						return -4;
					} else {//成功插入
						return 1;
					}
					
				} else {//序号还没有到字符数组的结尾
					if (nextTree == null){
						nextTree = new InfoSearchTree(charBuffer[index + 1]);
					}
					return nextTree.insertInfoAccordToCharArray(charBuffer, ++index, info);
				}//if
			}//if
		}//if
	}//insertInfoAccordToCharArray
	
	/**
	 * 利用getter获取info对象的一个字符串作为特征串，
	 * 依据此特征串来插入这个info对象。
	 * @param info 
	 * 		要插入的info对象。
	 * @param getter 
	 * 		获取特征串的工具对象。
	 * @return info
	 * 		如果info对象为null，就返回0；
	 * 		如果getter为null，返回-1；
	 * 		如果获取的特征串为null，返回-2；
	 * 		如果获取的特征串为空串，返回-3；
	 * 		如果调用insertInfoAccordToCharArray的结果不为1，返回-4；
	 * 		如果一切成功，返回1。
	 */
	public int insertInfo(IInfo info, IInfoGetter getter){
		if (info == null){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数info为null，"
					+ "不能插入空指针对象。");
			return 0;
		}
		
		if (getter == null){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数getter为null，"
					+ "不能正常获取特征字符串。");
			return -1;
		}
		String key = getter.pickInfo(info);
		
		if (key == null){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是从info当中获取的特征字符串为null，"
					+ "不能插入当前的Info对象。");
			return -2;
		} else if (key.isEmpty()){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是从info当中获取的特征字符串为空串，"
					+ "不能凭借空串插入当前的Info对象。");
			return -3;
		}
		
		if (insertInfoAccordToCharArray(key.toCharArray(), 0, info) == 1){
			return 1;
		}
		return -4;
	}
	
	public IInfoSet getInfoSetFromCharArray(char[] charBuffer, int index){
		if (charBuffer == null){
			MyLogger.logError("InfoSearchTree调用getInfoSetFromCharArray方法，"
					+ "但是参数charBuffer为null，"
					+ "不能根据空串插入Info对象。");
			return null;
		}//if
		
		if (index < 0){
			MyLogger.logError("InfoSearchTree调用getInfoSetFromCharArray方法，"
					+ "但是参数index小于等于0，"
					+ "不能正常获取字符数组的字符。");
			return null;
			
		} else if (index >= charBuffer.length){
			MyLogger.logError("InfoSearchTree调用getInfoSetFromCharArray方法，"
					+ "但是参数index大于等于字符数组的长度，"
					+ "不能正常获取字符数组的字符。");
			return null;
			
		} else {
			if (charBuffer[index] < curChar){//向左子树中查询
				if (lChild != null){
					return lChild.getInfoSetFromCharArray(charBuffer, index);
				}
				//左子树为空，查找失败
				return null;
				
			}else if (charBuffer[index] > curChar){//向右子树中查询
				if (rChild != null){
					return rChild.getInfoSetFromCharArray(charBuffer, index);
				}
				//右子树为空，查找失败
				return null;
				
			} else {//向当前树节点中查询
				if (index == charBuffer.length - 1){//检查序号已经到了字符数组的结尾，找到了这个节点
					return infoSet;
					
				} else {//序号还没有到字符数组的结尾
					if (nextTree != null){
						return nextTree.getInfoSetFromCharArray(charBuffer, index + 1);
					}
					return null;
				}//if
			}//if
		}//if
	}//getInfoSetFromCharArray
}
