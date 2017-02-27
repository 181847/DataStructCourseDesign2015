package unfinishedClass;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.Club;

public class ClubModelTraverser extends ModelTraverser {
	
	public ClubModelTraverser(Object[] clubColumn){
		super(clubColumn);
	}

	public ClubModelTraverser(DefaultTableModel model) {
		super(model);
	}
	
	public ClubModelTraverser(){
		super(new DefaultTableModel());
	}

	@Override
	public int dealWithContainer(Object arg0) {
		if (arg0 instanceof Club){
			Club club = (Club) arg0;
			String index = club.getIndex();
			String name = club.getName();
			String date = club.getDate();
			model.addRow(new Object[]{index, name, date});
			return 1;
		} else {
			MyLogger.logError("ClubModelTraverser遍历的container不是Club类型"
					+ "无法获取社团信息。");
			return 0;
		}
	}

}
