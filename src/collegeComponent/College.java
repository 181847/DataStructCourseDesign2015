package collegeComponent;

import basicInterface.IInfoSet;
import basicTool.MyLogger;
import collegeComponent.tool.getter.DateGetter;
import collegeComponent.tool.getter.GenderGetterForStudent;
import collegeComponent.tool.getter.GradeGetterForStudent;
import collegeComponent.tool.getter.MainCourseGetterForStudent;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.getter.PinyinGetter;
import collegeComponent.tool.getter.ShortPinyinGetter;
import collegeComponent.tool.traverser.StudentTraverser;
import info.InfoWithContainer;
import info.infoTool.AllTrueFilter;
import info.infoTool.IndexGetter;
import infoInterface.IInfo;
import infoInterface.IInfoGetter;
import infoSet.SearchableInfoSet;

public class College {
	/**
	 * 学生信息集合。
	 */
	public SearchableInfoSet studentInfoSet;
	
	/**
	 * 社团信息集合。
	 */
	public SearchableInfoSet clubInfoSet;

	public College(){
		studentInfoSet = new SearchableInfoSet(new IInfoGetter[]{
				new IndexGetter(),
				new NameGetter(),
				new PinyinGetter(),
				new ShortPinyinGetter(),
				new GenderGetterForStudent(),
				new GradeGetterForStudent(),
				new MainCourseGetterForStudent(),});
		
		clubInfoSet = new SearchableInfoSet(new IInfoGetter[]{
				new IndexGetter(),
				new NameGetter(),
				new PinyinGetter(),
				new ShortPinyinGetter(),
				new DateGetter()});
	}

	/**
	 * @return
	 * 		学生目录。
	 */
	public SearchableInfoSet getStudentInfoSet() {
		return studentInfoSet;
	}

	/**
	 * @return
	 * 		社团目录。
	 */
	public SearchableInfoSet getClubInfoSet() {
		return clubInfoSet;
	}
	
	/**
	 * 获取指定编号的社团对象。
	 * @param clubIndex
	 * 		社团的编号。
	 * @return
	 * 		指定编号的社团对象，
	 * 		如果指定编号的社团对象不存在，就返回null。
	 */
	public Club getClub(String clubIndex){
		IInfo[] infoArray = clubInfoSet.getIndex(clubIndex).toInfoArray();
		
		if (infoArray.length == 0){
			return null;
		} else if (infoArray.length > 1){
			MyLogger.logError("在College中通过社团编号获取社团对象时，"
					+ "获得了多个相同编号的社团，"
					+ "现在只返回第一个社团。");
		}
		return (Club) ( infoArray[0].getContainer() );
	}
	
	/**
	 * 获取指定学号的学生对象。
	 * @param studentIndex
	 * 		学生学号。
	 * @return
	 * 		指定学号的学生对象，
	 * 		如果指定编号的社团对象不存在，就返回null。
	 */
	public Student getStudent(String studentIndex){
		IInfoSet searchResult = studentInfoSet.getIndex(studentIndex);
		IInfo[] infoArray = searchResult.toInfoArray();
		
		if (infoArray.length == 0){		
			return null;
		} else if (infoArray.length > 1){
			MyLogger.logError("在College中通过学号获取学生对象时，"
					+ "获得了多个相同学号的学生，"
					+ "现在只返回第一个社团。请检查这些相同序号的学生对象");
			searchResult.traverseInfo(new StudentTraverser(), new AllTrueFilter());
		}
		return (Student) ( infoArray[0].getContainer() );
	}
	
	/**
	 * 添加一个社团对象。
	 * @param club
	 * 		要添加的社团对象。
	 * @return
	 * 		参数为null返回0，
	 * 		插入失败返回-1，
	 * 		插入成功返回1。
	 */
	public int addClub(Club club){
		if (club == null){
			MyLogger.logError("College要添加的社团对象为null，无法添加社团。");
			return 0;
		}
		if (1 == clubInfoSet.insertInfo(new InfoWithContainer(club))){
			return 1;
		}
		return -1;
	}
	
	/**
	 * 添加一个学生对象。
	 * @param student
	 * 		要添加的学生对象。
	 * @return
	 * 		参数为null返回0，
	 * 		已存在相同学号的学生返回-1。
	 */
	public int addStudent(Student student){
		if (student == null){
			MyLogger.logError("College要添加的学生对象为null，无法添加学生。");
			return 0;
		}
		if (1 == studentInfoSet.insertInfo(new InfoWithContainer(student))){
			return 1;
		}
		return -1;
	}
	
	/**
	 * 删除指定序号的学生。
	 * @param sutdentIndex
	 * 		要删除的学生的序号。
	 * @return
	 * 		成功返回1，
	 * 		失败返回0。
	 */
	public int deleteStudent(String sutdentIndex){
		studentInfoSet.deleteIndex(sutdentIndex);
		return 0;
	}
	
	/**
	 * 删除指定编号的社团。
	 * @param clubIndex
	 * 		要删除的社团的编号。
	 * @return
	 * 		成功返回1，
	 * 		失败返回0。
	 */
	public int deleteClub(String clubIndex){
		clubInfoSet.deleteIndex(clubIndex);
		return 0;
	}
}
