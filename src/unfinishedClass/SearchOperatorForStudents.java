package unfinishedClass;


public class SearchOperatorForStudents extends SearchOperator{
	/**
	 * 0 - 序号；
	 * 1 - 名字；
	 * 2 - 拼音名字；
	 * 3 - 短拼音名字；
	 * 4 - 性别；
	 * 5 - 年级；
	 * 6 - 专业。
	 */
	public SearchOperatorForStudents(College college) {
		super(college);
		selectedLog = new boolean[7];
	}

	/**
	 * 执行搜索college中学生的操作，
	 * 搜索的结果存在searchResult中，
	 * 可以通过getResult()方法获取搜索结果。
	 * @return
	 * 		1。
	 */
	@Override
	public int operate() {
		searchResult = college.getStudentInfoSet().search(searchInfo, selectedLog);
		return 1;
	}
	
	
	
}
