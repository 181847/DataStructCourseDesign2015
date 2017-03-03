package collegeComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import basicInterface.IDateHolder;
import basicTool.MyLogger;
import basicTool.RegistObjectWithPinyin;
import collegeComponent.tool.getter.GenderGetterForStudent;
import collegeComponent.tool.getter.GradeGetterForStudent;
import collegeComponent.tool.getter.IndexGetter;
import collegeComponent.tool.getter.MainCourseGetterForStudent;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.getter.PinyinGetter;
import collegeComponent.tool.getter.PositionGetter;
import collegeComponent.tool.getter.ShortPinyinGetter;
import infoInterface.IInfoGetter;
import infoSet.SearchableInfoSet;

/**
 * 社团对象类型，
 * 内部用一个Date类型成员来存储日期，
 * 一个SimpleDateFormate类型来存储格式化日期的对象（因为这个日期格式化对象的创建比较占资源，
 * 所以每次创建Club对象的时候都为每个对象创建一个dateFormat，方便后期修改社团时间），
 * 一个SearchableInfoSet类型的信息集合来存储参加这个社团的所有成员信息，
 * 这个信息集合内部有8个搜索目录
 * 『
 * 0.学号，
 * 1.名字，
 * 2.名字拼音，
 * 3.名字短拼音，
 * 4.性别（男性对应“M”，女性对应“F”，其他对应“?”），
 * 5.年级，
 * 6.专业，
 * 7.在本社团中的职位
 * 』
 */
public class Club extends RegistObjectWithPinyin implements IDateHolder{
	/**
	 * 社团的日期。
	 */
	protected Date date;
	
	/**
	 * 社团信息集合。
	 */
	protected SearchableInfoSet myMembersInfoSet;
	
	/**
	 * 格式化日期的对象。
	 */
	protected SimpleDateFormat dateFormate;

	public SimpleDateFormat getDateFormate() {
		return dateFormate;
	}

	public void setDateFormate(SimpleDateFormat dateFormate) {
		this.dateFormate = dateFormate;
	}

	/**
	 * @param clubIndex
	 * 		社团的编号。
	 * @param clubName
	 * 		社团的名字。
	 * @param dateString
	 * 		社团的创建日期，
	 * 		格式必须是“yyyy-MM-dd”。
	 */
	public Club(String clubIndex, String clubName, String dateString) {
		super(clubIndex, clubName);
		dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		setDate(dateString);
		myMembersInfoSet = new SearchableInfoSet(new IInfoGetter[]{
										new IndexGetter(),
										new NameGetter(),
										new PinyinGetter(),
										new ShortPinyinGetter(),
										new GenderGetterForStudent(),
										new GradeGetterForStudent(),
										new MainCourseGetterForStudent(),
										new PositionGetter()
												});
	}
	
	/**
	 * 返回社团的创建日期。
	 * @return
	 * 		社团的创建日期（形如“2015-5-7”），
	 * 		如果先前初始化日期时出错，导致日期对象为null，
	 * 		那么返回的将是一个空串。
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
	 * 		形式“yyyy-MM-dd”，如果格式不规范（例如月份为45，字符串包含汉字……），
	 * 		日期对象会被设置为null，
	 * 		这时候获取日期字符串的时候返回的是一个空串。
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
