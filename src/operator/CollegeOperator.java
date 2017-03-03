package operator;

import basicInterface.IOperator;
import collegeComponent.College;

/**
 * 所有基于college对象进行操作的Operator的抽象父类，
 * 内部包含一个college对象。
 * @author 75309
 *
 */
public abstract class CollegeOperator implements IOperator {
	public  College college;
	
	/**
	 * @param college
	 * 		操作员的操作对象。
	 */
	public CollegeOperator(College college){
		this.college = college;
	}

	@Override
	public abstract int operate();

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}
