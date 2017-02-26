package unfinishedClass;

import basicInterface.IOperator;

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
