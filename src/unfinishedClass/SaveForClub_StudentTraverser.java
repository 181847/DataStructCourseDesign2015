package unfinishedClass;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.Club;
import info.infoTool.AllTrueFilter;
import infoInterface.IInfoTraverser;
import testVisual.SaveTraverser;

public class SaveForClub_StudentTraverser extends SaveTraverser implements IInfoTraverser {

	public SaveForClub_StudentTraverser(PrintWriter clubWriter) {
		super(clubWriter);
	}

	@Override
	public int dealWithContainer(Object container) {
		if (container instanceof Club){
			Club club = (Club) container;
			club
				.getMyMembers()
				.traverseInfo(new SaveForClub_MemberTraverser(writer, club.getIndex()),
						new AllTrueFilter());
			
		} else {
			MyLogger.logError("SaveForClub_StudentTraverser遍历的对象不是Club类型。");
		}
		return 0;
	}

}
