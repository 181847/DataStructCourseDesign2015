package operator;

import collegeComponent.College;

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
