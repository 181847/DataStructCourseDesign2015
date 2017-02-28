package collegeComponent.tool.getter;

import basicTool.MyLogger;
import collegeComponent.MyMember;
import collegeComponent.Student;
import info.infoTool.AbstractGetter;

public class GenderGetterForStudent extends AbstractGetter {

	@Override
	public String dealWithContainer(Object container) {
		if (container instanceof Student){
			switch( ((Student) container).getGender() ){
				case 0:
					return "M";
				case 1:
					return "F";
				default:
					return "?";
			}
		} else if (container instanceof MyMember){
			switch( ((MyMember) container).getStudent().getGender() ){
			case 0:
				return "M";
			case 1:
				return "F";
			default:
				return "?";
			}
		} else {
			MyLogger.logError("GenderGetterForStudent准备读取Info对象内存储的信息，"
					+ "但是读取的container不是Student或MyMember的子类，"
					+ "无法获取信息。");
			return "";
		}
	}
}
