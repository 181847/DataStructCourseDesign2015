package operator;

import collegeComponent.College;

/**
 * 更新操作者的抽象类，
 * 内部增加一个originalIndex来存储修改之前的Student/Club对象的序号。
 */
public abstract class UpdateOperator extends CollegeOperator {
	public String originalIndex;

	public UpdateOperator(College college) {
		super(college);
	}

	@Override
	public abstract int operate();

	public String getOriginalIndex() {
		return originalIndex;
	}
}
