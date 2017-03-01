package collegeComponent.tool.traverser;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.MyClub;

public class MyClubModelTraverser extends ModelTraverser {

	public MyClubModelTraverser(DefaultTableModel model) {
		super(model);
	}
	
	public MyClubModelTraverser(Object[] column){
		super(column);
	}
	
	public MyClubModelTraverser(){
		super();
	}

	@Override
	public int dealWithContainer(Object arg0) {
		if (arg0 instanceof MyClub){
			MyClub myClub = (MyClub) arg0;
			String index = myClub.getIndex();
			String name = myClub.getName();
			String position = myClub.getPosition();
			model.addRow(new Object[]{index, name, position});
			return 1;
		} else {
			MyLogger.logError("ClubModelTraverser遍历的container不是MyMember类型"
					+ "无法获取社团信息。");
			return 0;
		}
	}
}
