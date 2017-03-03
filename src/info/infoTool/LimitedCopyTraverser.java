package info.infoTool;

import info.InfoWithContainer;
import infoSet.DoubleLoopLinkedInfoSet;

/**
 * 可限制次数的复制遍历者。
 */
public class LimitedCopyTraverser extends TimesLimitedTraverser {
	DoubleLoopLinkedInfoSet containerSet;

	public LimitedCopyTraverser(int maxTraverseTimes, DoubleLoopLinkedInfoSet containerSet) {
		super(maxTraverseTimes);
		this.containerSet = containerSet;
	}

	@Override
	public int dealWithContainer(Object container) {
		containerSet.insertInfo(new InfoWithContainer(container));
		return 1;
	}

}
