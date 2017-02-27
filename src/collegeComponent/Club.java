package collegeComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import basicInterface.IDateHolder;
import basicTool.MyLogger;
import basicTool.RegistObjectWithPinyin;
import collegeComponent.tool.getter.GenderGetterForStudent;
import collegeComponent.tool.getter.GradeGetterForStudent;
import collegeComponent.tool.getter.MainCourseGetterForStudent;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.getter.PositionGetter;
import info.infoTool.IndexGetter;
import infoInterface.IInfoGetter;
import infoSet.SearchableInfoSet;

public class Club extends RegistObjectWithPinyin implements IDateHolder{
	public Date date;
	public SearchableInfoSet myMembersInfoSet;
	public SimpleDateFormat dateFormate;

	/**
	 * @param clubIndex
	 * 		社团的编号。
	 * @param clubName
	 * 		社团的名字。
	 * @param dateString
	 * 		社团的创建日期，
	 * 		格式必须是“yyyy-mm-dd”。
	 */
	public Club(String clubIndex, String clubName, String dateString) {
		super(clubIndex, clubName);
		dateFormate = new SimpleDateFormat("yyyy-mm-dd");
		setDate(dateString);
		myMembersInfoSet = new SearchableInfoSet(new IInfoGetter[]{
										new IndexGetter(),
										new NameGetter(),
										new GenderGetterForStudent(),
										new GradeGetterForStudent(),
										new MainCourseGetterForStudent(),
										new PositionGetter()
												});
	}
	
	/**
	 * 返回社团的创建日期。
	 * @return
	 * 		社团的创建日期。
	 */
	public String getDate(){
		if (date == null){
			return "";
		}
		return dateFormate.format(date);
	}
	
	/**
	 * 设置社团的创建时间。
	 * @param dateString
	 * 		社团创建事件的字符串，
	 * 		形式“yyyy-mm-dd”。
	 */
	public void setDate(String dateString){
		try {
			this.date = dateFormate.parse(dateString);
		} catch (ParseException e) {
			MyLogger.logError("社团初始化日期时发生异常，"
					+ "请检查日期字符串是否符合要求："
					+ dateString);
			MyLogger.logException(e);
			date = null;
		}
	}
	
	/**
	 * 返回社团成员集合。
	 * @return
	 * 		社团成员集合。
	 */
	public SearchableInfoSet getMyMembers(){
		return myMembersInfoSet;
	}
}
