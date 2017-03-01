package testVisual;

import java.io.PrintWriter;

import info.infoTool.AbstractTraverser;

public abstract class SaveTraverser extends AbstractTraverser {
	protected PrintWriter writer;
	
	public SaveTraverser(PrintWriter writer){
		this.writer = writer;
	}

	@Override
	public abstract int dealWithContainer(Object container);
}
