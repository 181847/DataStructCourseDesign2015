package operator;

import basicTool.MyLogger;
import collegeComponent.College;

/**
 * 一个删除Club/Student类型的抽象父类，
 * 内部包含一个targetIndex来指示要删除的对象序号。
 */
public abstract class DeleteOperator extends CollegeOperator {
	public String targetIndex;

	public DeleteOperator(College college) {
		super(college);
	}

	@Override
	public abstract int operate();

	public String getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(String targetIndex) {
		this.targetIndex = targetIndex;
	}
	
	protected boolean check(){
		if (targetIndex == null){
			MyLogger.logError("DeleteOperator的删除对象的序号为null，"
					+ "请设置要删除的对象的序号");
			return false;
		} else if (targetIndex.isEmpty()){
			MyLogger.logError("DeleteOperator的删除对象的序号为空串，"
					+ "请设置要删除的对象的序号");
			return false;
		}
		return true;
	}
}
