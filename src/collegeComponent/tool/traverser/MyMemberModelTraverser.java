package collegeComponent.tool.traverser;

import javax.swing.table.DefaultTableModel;

import basicTool.MyLogger;
import collegeComponent.MyMember;

/**
 * 遍历Club对象内部的MyMembersInfoSet，
 * 创建用于JTable显示的model。
 */
public class MyMemberModelTraverser extends ModelTraverser {

	public MyMemberModelTraverser(Object[] myMemberColumn){
		super(myMemberColumn);
	}

	public MyMemberModelTraverser(DefaultTableModel model) {
		super(model);
	}
	
	public MyMemberModelTraverser(){
		super(new DefaultTableModel());
	}
	
	@Override
	public int dealWithContainer(Object arg0) {
		if (arg0 instanceof MyMember){
			MyMember myMember = (MyMember) arg0;
			String index = myMember.getIndex();
			String name = myMember.getName();
			String position = myMember.getPosition();
			model.addRow(new Object[]{index, name, position});
			return 1;
		} else {
			MyLogger.logError("ClubModelTraverser遍历的container不是MyMember类型"
					+ "无法获取社团信息。");
			return 0;
		}
	}

}
