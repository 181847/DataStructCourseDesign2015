package unfinishedClass;

import java.io.PrintWriter;

import basicTool.MyLogger;
import collegeComponent.MyMember;
import infoInterface.IInfoTraverser;
import testVisual.SaveTraverser;

public class SaveForClub_MemberTraverser extends SaveTraverser implements IInfoTraverser {
	public String clubIndex;
	public SaveForClub_MemberTraverser(PrintWriter writer, String clubIndex) {
		super(writer);
		this.clubIndex = clubIndex;
	}

	@Override
	public int dealWithContainer(Object container) {
		if (container instanceof MyMember){
			MyMember myMember = (MyMember) container;
			writer.println(clubIndex
					+ "&" + myMember.getIndex()
					+ "&" + myMember.getPosition());
			
		} else {
			MyLogger.logError("SaveForClub_MemberTraverser遍历的对象不是MyMember类型。");
		}
		return 0;
	}

}
