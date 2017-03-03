package aboutVisual;

import javax.swing.JFrame;

import collegeComponent.College;

/**
 * 此类是所有包含College对象的窗口类的父类，
 * 基本上本工程中要用到的窗口中的一些功能都必须与college对象进行数据的交换，
 * 所以这个类基本适用于本工程的所有窗口类。
 * @author 75309
 *
 */
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
