package testSpace;

import collegeComponent.College;
import operator.CollegeReaderAndSaverOperator;

/**
 * 本测试用例的任务已经完成，
 * 由于CollegeFrame中已经包含一个CollegeReaderAndSaverOperator，
 * 而且CollegeFrame必须指定读取数据的路径和保存备份的路径，
 * 所以这个测试用例已经不再适用。
 */
public class TestCollegeReaderAndSaverOperator extends Test {
	public static void main(String[] arg){
		prepare();
		College newCollege = new College();
		CollegeReaderAndSaverOperator collegeOperator = 
				new CollegeReaderAndSaverOperator(newCollege);
		collegeOperator.read("save//data");
		
		//new CollegeFrame(newCollege).setVisible(true);
	}
}
