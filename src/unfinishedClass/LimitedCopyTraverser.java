package unfinishedClass;

import info.InfoWithContainer;
import info.infoTool.TimesLimitedTraverser;
import infoSet.DoubleLoopLinkedInfoSet;

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
