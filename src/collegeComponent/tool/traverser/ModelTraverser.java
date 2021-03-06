package collegeComponent.tool.traverser;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import info.infoTool.AbstractTraverser;

/**
 * 此抽象类用于遍历Info，并创建用于JTable显示model。
 */
public abstract class ModelTraverser extends AbstractTraverser {
	public DefaultTableModel model;
	
	public ModelTraverser(Object[] column){
		model = new DefaultTableModel(column, 0);
	}
	
	public ModelTraverser(DefaultTableModel model){
		this.model = model;
	}
	
	public ModelTraverser(){
		this.model = new DefaultTableModel();
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
