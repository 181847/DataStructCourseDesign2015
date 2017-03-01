package unfinishedClass;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.Club;
import testVisual.SaveTraverser;

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
