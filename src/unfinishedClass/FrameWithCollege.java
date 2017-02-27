package unfinishedClass;

import javax.swing.JFrame;

import collegeComponent.College;

public class FrameWithCollege extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877127668132791634L;
	protected College college;
	
	public FrameWithCollege(College college){
		this.college = college;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
}
