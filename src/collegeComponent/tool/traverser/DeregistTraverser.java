package collegeComponent.tool.traverser;

import collegeComponent.College;
import info.infoTool.AbstractTraverser;
import operator.DeregisterOperator;

/**
 * 注销遍历者的抽象类，内部包含一个DeregisterOperator，
 * 具体的注销方法由子类定义。
 */
public abstract class DeregistTraverser extends AbstractTraverser {
	public DeregisterOperator dro;
	
	public DeregistTraverser(College college){
		dro = new DeregisterOperator(college);
	}
	
	@Override
	public abstract int dealWithContainer(Object container);
	
	public void setStudentIndex(String studentIndex){
		dro.setStudentIndex(studentIndex);
	}
	
	public void setClubIndex(String clubIndex){
		dro.setClubIndex(clubIndex);
	}
}
