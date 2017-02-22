package infoSet;

import basicInterface.IInfoSet;
import basicTool.MyLogger;
import info.infoTool.AllTrueFilter;
import info.infoTool.CopyTraverser;
import infoInterface.IInfo;
import infoInterface.IInfoFilter;
import infoInterface.IInfoGetter;
import infoInterface.IInfoTraverser;
import infoInterface.ILimitedTraverser;

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
		
		String key = getter.pickMessage(info);
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
	
	/**
	 * 查找指定字符串路径的树节点当中的存储DLLInfoSet对象，
	 * 这个方法会先用参数找到指定的InfoSearchTree，如果这个Tree存在，就返回这个Tree的成员infoSet，
	 * 如果这个Tree不存在就返回null。
	 * @param charBuffer 
	 * 		字符串路径。
	 * @param index 
	 * 		字符数组路径的起始检查点。
	 * @return
	 * 		规定的字符数组路径的树节点当中的成员变量DLLInfoSet，
	 * 		这个对象是DoubleLoopLinkedInfoSet类型，但是它也有可能为null，
	 * 		如果相关的字符路径的树节点并不存在的话也会返回null。
	 */
	public DoubleLoopLinkedInfoSet getSpecificInfoSet(char[] charBuffer, int index){
		InfoSearchTree findTree = getTree(charBuffer, index);
		if (findTree == null){
			return null;
		}
		return findTree.getInfoSet();
	}//getInfoSetFromCharArray
	
	/**
	 * 根据字符数组的路径，获取这个路径的InfoSearchTree。
	  * @param charBuffer 
	 * 		字符串路径。
	 * @param index 
	 * 		字符数组路径的起始检查点，
	 * 		这个方法会递归调用，
	 * 		如果是递归调用的入口的话，
	 * 		这个index应该设为0，
	 * 		表示从字符串的第0个字符开始，
	 * 		递归会持续到字符数组的结尾或者没有找到规定的字符数组路径。
	 * @return
	 * 		规定的字符数组路径的树节点，
	 * 		如果这个树节点不存在就返回null。
	 */
	public InfoSearchTree getTree(char[] charBuffer, int index){
		if (charBuffer == null){
			MyLogger.logError("InfoSearchTree调用getTreeFromCharArray方法，"
					+ "但是参数charBuffer为null，"
					+ "不能根据空串插入Info对象。");
			return null;
		}
		
		if (index < 0){
			MyLogger.logError("InfoSearchTree调用getTreeFromCharArray方法，"
					+ "但是参数index小于等于0，"
					+ "不能正常获取字符数组的字符。");
			return null;
			
		} else if (index >= charBuffer.length){
			MyLogger.logError("InfoSearchTree调用getTreeFromCharArray方法，"
					+ "但是参数index大于等于字符数组的长度，"
					+ "不能正常获取字符数组的字符。");
			return null;
			
		} else {
			
			if (charBuffer[index] < curChar){//向左子树中查询
				
				if (lChild != null){
					return lChild.getTree(charBuffer, index);
				}
				//左子树为空，查找失败
				return null;
				
			}else if (charBuffer[index] > curChar){//向右子树中查询
				
				if (rChild != null){
					return rChild.getTree(charBuffer, index);
				}
				//右子树为空，查找失败
				return null;
				
			} else {//向当前树节点中查询
				
				if (index == charBuffer.length - 1){//检查序号已经到了字符数组的结尾，找到了这个节点
					return this;
					
				} else {//序号还没有到字符数组的结尾
					
					if (nextTree != null){
						return nextTree.getTree(charBuffer, index + 1);
					}
					return null;
					
				}//if
			}//if
		}//if
	}//getTree
	
	/**
	 * 通过一个可限制的遍历者来遍历信息体，
	 * 如果循环遍历的过程中发现遍历者被限制了，
	 * 就立即返回0，结束遍历。
	 * @param limitedTraverser 
	 * 		可限制的遍历者。
	 * @param filter 
	 * 		过滤器，过滤掉不想要遍历的信息体。
	 * @return
	 * 		参数为null或者遍历者受到限制时都返回0，
	 * 		其他情况返回1。
	 */
	public int limitedTraverseInfo(ILimitedTraverser limitedTraverser, IInfoFilter filter){
		if (limitedTraverser == null || filter == null){
			MyLogger.log("遍历InfoSearchTree的时候，参数存在null遍历并InfoSet失败。请检查："
					+ "IInfoTraverser traverser == null: " + (limitedTraverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		int[] resultNum = new int[]{1, 1, 1, 1};
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (lChild != null){
			resultNum[0] = lChild.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (rChild != null){
			resultNum[1] = rChild.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (nextTree != null){
			resultNum[2] = nextTree.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (infoSet != null){
			resultNum[3] = infoSet.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		return resultNum[0]*resultNum[1]*resultNum[2]*resultNum[3];
	}//limitedTraverseInfo
	
	/**
	 * 递归调用，删除树内的所有的满足要求的信息体。
	 * @param searchInfo 
	 * 		主要用来检查的字符串信息，
	 * 		只有与这个searchInfo相等才算找到，
	 * 		如果这个字符串为空串的话表示所有信息体都符合，
	 * 		如果这些信息体同时通过了filter的筛选的话，才会删除。
	 * @param getter 
	 * 		这个对象用来获取Info信息体中指定的信息，
	 * 		比如某个InfoGetter用来获取Info中的名字信息，
	 * 		有的InfoGetter用来获取Info中的学号信息。
	 * @param filter 
	 * 		过滤器，用于针对Info中的信息返回真假，
	 * 		最终的InfoSet会过滤掉所有filter检查结果为false的Info，
	 * 		比如某个filter规定如果StudentInfo的年级为大一，
	 * 		那么这次删除只会删除大一的StudentInfo。
	 * @return
	 * 		如果参数中存在null，返回0；
	 * 		如果当前节点的infoSet被删空了，而且nextTree为null，返回-1；
	 * 		否则返回1。
	 */
	public int delete(String searchInfo, IInfoGetter getter, IInfoFilter filter){
		if (searchInfo == null || getter == null || filter == null){
			MyLogger.log("调用InfoSearchTree的delete()的时候，参数存在null，删除Info失败。请检查："
					+ "String searchInfo == null: " + (searchInfo == null) 
					+ "IInfoGetter getter == null: " + (getter == null)
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		
		int result;
		if (lChild != null){
			result = lChild.delete(searchInfo, getter, filter);
			if (result == -1){
				lChild = deleteTree(lChild);
			}
		}//if
		
		if (rChild != null){
			result = rChild.delete(searchInfo, getter, filter);
			if (result == -1){
				rChild = deleteTree(rChild);
			}
		}//if
		
		if (nextTree != null){
			result = nextTree.delete(searchInfo, getter, filter);
			if (result == -1){
				nextTree = deleteTree(nextTree);
			}
		}//if
		
		if ( ! (infoSet == null || infoSet.isEmpty()) ){
			infoSet.delete(searchInfo, getter, filter);
		}
		
		//没有下个树节点，当前树节点中也没有存储任何信息体
		//则这个树就没有用了，返回-1通知上层调用者将自己删除。
		if (nextTree == null && 
				(infoSet == null || infoSet.isEmpty()) ){
			return -1;
		}
		return 1;
	}//delete
	
	/**
	 * 按照字符数组的路径删除指定的树节点中的信息体。
	 * @param charBuffer 
	 * 		一个与这个Info对象有关的字符数组，
	 * 		一般来说可以是这个Info的某个特征字符串。
	 * @param index 
	 * 		如果是递归的入口的话，
	 * 		这个index应该为0，
	 * 		表示从字符数组的第0个字符开始在树中进行查找插入。
	  * @param searchInfo 
	 * 		主要用来检查的字符串信息，
	 * 		只有与这个searchInfo相等才算找到，
	 * 		如果这个字符串为空串的话表示所有信息体都符合，
	 * 		如果这些信息体同时通过了filter的筛选的话，才会删除。
	 * @param getter 
	 * 		这个对象用来获取Info信息体中指定的信息，
	 * 		比如某个InfoGetter用来获取Info中的名字信息，
	 * 		有的InfoGetter用来获取Info中的学号信息。
	 * @param filter 
	 * 		过滤器，用于针对Info中的信息返回真假，
	 * 		最终的InfoSet会过滤掉所有filter检查结果为false的Info，
	 * 		比如某个filter规定如果StudentInfo的年级为大一，
	 * 		那么这次删除只会删除大一的StudentInfo。
	 * @return
	 * 		如果参数中存在null或者index小于0或超出字符数组长度，返回0；
	 * 		如果没有找到指定的路径，返回0；
	 * 		如果要求上层调用者将自己删除的话，返回-1；
	 * 		如果返回-2，表示已经在指定的路径尾部的树节点的infoSet执行了delete操作。
	 */
	public int deleteSpecific(char[] charBuffer, int index, 
			String searchInfo, IInfoGetter getter, IInfoFilter filter){
		if (charBuffer == null){
			MyLogger.logError("InfoSearchTree调用deleteSpecific()方法时，"
					+ "但是参数charBuffer为null，"
					+ "不能根据空串插入Info对象。");
			return 0;
		}
		
		if (searchInfo == null || getter == null || filter == null){
			MyLogger.log("InfoSearchTree调用deleteSpecific()方法，参数存在null，删除Info失败。请检查："
					+ "String searchInfo == null: " + (searchInfo == null) 
					+ "IInfoGetter getter == null: " + (getter == null)
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		
		if (index < 0){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数index小于等于0，"
					+ "不能正常获取字符数组的字符。");
			return 0;
			
		} else if (index >= charBuffer.length){
			MyLogger.logError("InfoSearchTree调用insertInfo方法，"
					+ "但是参数index大于等于字符数组的长度，"
					+ "不能正常获取字符数组的字符。");
			return 0;
			
		} else {
			
			if (charBuffer[index] < curChar){//向左子树删
				if (lChild != null){
					
					switch(lChild.deleteSpecific(charBuffer, index, 
							searchInfo, getter, filter)){
					case -2:
						return -2;
					case -1:
						lChild = deleteTree(lChild);
						//告知上层，已经完成删除操作
						return -2;
					default:
						return 0;
					}//switch
					
				}
				//左子树为空，无法继续查找，返回0表示查找失败
				return 0;
				
			}else if (charBuffer[index] > curChar){//向右子树删
				if (rChild != null){
					
					switch(rChild.deleteSpecific(charBuffer, index, 
							searchInfo, getter, filter)){
					case -1:
						rChild = deleteTree(rChild);
						//告知上层，已经完成删除操作
					case -2:
						return -2;
					default:
						return 0;
					}//switch
				}
				return 0;
				
			} else {//找到指定的字符
				
				if (index == charBuffer.length - 1){//序号已经到了字符数组的结尾
					if (infoSet != null){
						infoSet.delete(searchInfo, getter, filter);
					}
					if ( (infoSet == null || infoSet.isEmpty()) &&
							nextTree == null){
						//这个节点已经空了，这个节点已经不能在作为路径的导航了，
						//请求上层将自己删除，删除按照二叉排序树的删除方法。
						return -1;
					}//if
					return -2;
					
				} else {//序号还没有到字符数组的结尾
					if (nextTree != null){
						switch(nextTree.deleteSpecific(charBuffer, index + 1, 
								searchInfo, getter, filter)){
						case -1:
							nextTree = deleteTree(nextTree);
							if ( (infoSet == null || infoSet.isEmpty()) &&
									nextTree == null){
								//这个节点已经空了，这个节点已经不能在作为路径的导航了，
								//请求上层将自己删除，删除按照二叉排序树的删除方法。
								return -1;
							}//if
						case -2:
							return -2;
						default:
							return 0;
						}//switch
					}//if
					return 0;
					
				}//if
			}//if
		}//if
	}//deleteSpecific
	
	/**
	 * 输入一个此树中的节点，
	 * 删除这棵树，返回用来代替这棵树的树节点。
	 * @return
	 * 		用于代替这棵树的节点，
	 * 		这个用来代替的树节点应该就是这棵树的左右子树中的某一棵。
	 */
	private InfoSearchTree deleteTree(InfoSearchTree treeToDelete){
		if (treeToDelete.rChild == null){
			return lChild;
		}
		if (treeToDelete.lChild == null){
			return rChild;
		}
		
		InfoSearchTree prec = treeToDelete;
		InfoSearchTree next = treeToDelete.rChild;
		
		while(next.lChild != null){
			prec = next;
			next = next.lChild;
		}
		
		if (prec == treeToDelete){
			next.lChild = treeToDelete.lChild;
			return next;
		} else {
			prec.lChild = next.rChild;
			next.lChild = treeToDelete.lChild;
			next.rChild = treeToDelete.rChild;
			return next;
		}//if
	}//deleteTree
	
	/**
	 * 单独遍历，这个方法只遍历当前树节点内部的infoSet和nextTree的所有，
	 * 即调用infoSet.traverseInfo()和nextTree.traverseInfo()。
	 * @param traverser 遍历的具体操作在这个IInfoTraverser的traverserInfo()方法中的的定义。
	 * @param filter 过滤器，所有不满足过滤器的Info都不会被遍历。
	 * @return 成功遍历返回1，参数存在null返回0。
	 */
	public int soloTraverseInfo(IInfoTraverser traverser, IInfoFilter filter) {
		if (traverser == null || filter == null){
			MyLogger.log("soloTraverseInfo()方法遍历InfoSearchTree的时候，"
					+ "参数存在null遍历并InfoSet失败。请检查："
					+ "IInfoTraverser traverser == null: " + (traverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		int[] resultNum = new int[]{1, 1};
		
		if (infoSet != null){
			resultNum[0] = infoSet.traverseInfo(traverser, filter);
		}
		
		if (nextTree != null){
			resultNum[1] = nextTree.traverseInfo(traverser, filter);
		}
		
		
		return resultNum[0]*resultNum[1];
	}
	
	/**
	 * 通过一个可限制的遍历者来遍历信息体，
	 * 只遍历当前树节点和nextTree中的信息，
	 * 不遍历当前这棵树的左右孩子。
	 * 如果循环遍历的过程中发现遍历者被限制了，
	 * 就立即返回0，结束遍历。
	 * @param limitedTraverser 
	 * 		可限制的遍历者。
	 * @param filter 
	 * 		过滤器，过滤掉不想要遍历的信息体。
	 * @return
	 * 		参数为null或者遍历者受到限制时都返回0，
	 * 		其他情况返回1。
	 */
	public int soloLimitedTraverseInfo(ILimitedTraverser limitedTraverser, IInfoFilter filter){
		if (limitedTraverser == null || filter == null){
			MyLogger.log("遍历InfoSearchTree的时候，参数存在null遍历并InfoSet失败。请检查："
					+ "IInfoTraverser traverser == null: " + (limitedTraverser == null) 
					+ "IInfoFilter filter == null: " + (filter == null));
			return 0;
		}
		int[] resultNum = new int[]{1, 1};
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (infoSet != null){
			resultNum[0] = infoSet.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		if (limitedTraverser.isLimited()){
			return 0;
		}
		if (nextTree != null){
			resultNum[1] = nextTree.limitedTraverseInfo(limitedTraverser, filter);
		}
		
		return resultNum[0]*resultNum[1];
	}//limitedTraverseInfo
}
