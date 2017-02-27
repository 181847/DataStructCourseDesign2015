package unfinishedClass;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import info.infoTool.AbstractTraverser;

public abstract class ModelTraverser extends AbstractTraverser {
	public DefaultTableModel model;
	
	public ModelTraverser(Object[] column){
		model = new DefaultTableModel(column, 0);
	}
	
	public ModelTraverser(DefaultTableModel model){
		this.model = model;
	}

	@Override
	public abstract int dealWithContainer(Object arg0);
	
	public DefaultTableModel getModel(){
		return model;
	}

	public void setModel(DefaultTableModel model) {
		if (model == null){
			MyLogger.logError("ModelTraverser设置的model为null，设置失败");
			return;
		}
		this.model = model;
	}

}
