package operator;

import collegeComponent.Club;
import collegeComponent.College;

/**
 * 专门搜索社员对象的操作者，
 * 设置好college对象，
 * 通过本类的selectSearchLog(int)和deselectedSearchLog(int)来设置
 * 搜索要涉及的目录。
 * 【
 * 0 - 序号；
 * 1 - 名字；
 * 2 - 拼音名字；
 * 3 - 短拼音名字；
 * 4 - 性别；
 * 5 - 年级；
 * 6 - 专业；
 * 7 - 社团中的职位。
 * 】
 */
public class SearchOperatorForMyMembers extends SearchOperator {
	public Club club;
	
	/**
	 * 0 - 学号；
	 * 1 - 名字；
	 * 2 - 拼音名字；
	 * 3 - 短拼音名字；
	 * 4 - 性别；
	 * 5 - 年级；
	 * 6 - 专业；
	 * 7 - 社团中的职位。
	 * @param college
	 * 		学院对象。
	 * @param clubIndex
	 * 		社团的编号。
	 */
	public SearchOperatorForMyMembers(College college, String clubIndex) {
		super(college);
		club = college.getClub(clubIndex);
		selectedLog = new boolean[8];
	}
	
	/**
	 * 0 - 学号；
	 * 1 - 名字；
	 * 2 - 拼音名字；
	 * 3 - 短拼音名字；
	 * 4 - 性别；
	 * 5 - 年级；
	 * 6 - 专业；
	 * 7 - 社团中的职位。
	 * @param college
	 * 		学院对象。
	 * @param club
	 * 		社团对象。
	 */
	public SearchOperatorForMyMembers(College college, Club club){
		super(college);
		this.club = club;
		selectedLog = new boolean[8];
	}

	@Override
	public int operate() {
		searchResult = club.getMyMembers().search(searchInfo, selectedLog);
		return 1;
	}

}
