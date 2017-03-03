package collegeComponent.tool.traverser;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.Club;
import testVisual.SaveTraverser;

/**
 * 遍历College.clubInfoSet，
 * 用来存储所有社团的基本信息。
 */
public class SaveForClubTraverser extends SaveTraverser {
	
	public SaveForClubTraverser(PrintWriter clubWriter){
		super(clubWriter);
	}
	
	@Override
	public int dealWithContainer(Object container) {
		if (container instanceof Club){
			Club club = (Club) container;
			writer.println(club.getIndex()
					+ "&" + club.getName()
					+ "&" + club.getDate());
			return 1;
		} else {
			MyLogger.logError("SaveForClubTraverser遍历的对象不是Club类型。");
		}
		return 0;
	}
}
