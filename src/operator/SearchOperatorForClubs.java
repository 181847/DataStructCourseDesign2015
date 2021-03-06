package operator;

import collegeComponent.College;

/**
 * 专门搜索社团对象的操作者，
 * 设置好college对象，
 * 通过本类的selectSearchLog(int)和deselectedSearchLog(int)来设置
 * 搜索要涉及的目录。
 * 【
 * 0 - 序号；
 * 1 - 名字；
 * 2 - 拼音名字；
 * 3 - 短拼音名字；
 * 4 - 社团创建时间
 * 】
 */
public class SearchOperatorForClubs extends SearchOperator {

	/**
	 * 0 - 序号；
	 * 1 - 名字；
	 * 2 - 拼音名字；
	 * 3 - 短拼音名字；
	 * 4 - 社团创建时间。
	 */
	public SearchOperatorForClubs(College college) {
		super(college);
		selectedLog = new boolean[5];
	}

	/**
	 * 执行搜索college中社团的操作，
	 * 搜索的结果存在searchResult中，
	 * 可以通过getResult()方法获取搜索结果。
	 * @return
	 * 		1。
	 */
	@Override
	public int operate() {
		searchResult = college.getClubInfoSet().search(searchInfo, selectedLog);
		return 1;
	}

}
