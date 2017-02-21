package student;


public class Student{
	public String name;
	public String index;
	
	/**
	 * @param name 学生的姓名。
	 * @param index 学生的学号。
	 */
	public Student(String name, String index){
		this.name = name;
		this.index = index;
	}
	
	/**
	 * 返回学生对象的名字。
	 * @return 学生对象的名字。
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置学生对象的名字。
	 * @param name 学生对象的名字。
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 返回学生对象的学号。
	 * @return 学生对象的学号。
	 */
	public String getIndex() {
		return index;
	}
	
	/**
	 * 设置学生对象的学号。
	 * @param name 学生对象的学号。
	 */
	public void setIndex(String index) {
		this.index = index;
	}
}
