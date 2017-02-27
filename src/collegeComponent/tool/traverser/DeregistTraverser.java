package collegeComponent.tool.traverser;

import collegeComponent.College;
import info.infoTool.AbstractTraverser;
import operator.DeregisterOperator;

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
