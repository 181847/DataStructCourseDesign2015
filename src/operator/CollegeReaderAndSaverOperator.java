package operator;


import java.io.*;

import basicTool.MyLogger;
import collegeComponent.Club;
import collegeComponent.College;
import collegeComponent.Student;
import collegeComponent.tool.getter.DateGetter;
import collegeComponent.tool.getter.GenderGetterForStudent;
import collegeComponent.tool.getter.GradeGetterForStudent;
import collegeComponent.tool.getter.IndexGetter;
import collegeComponent.tool.getter.MainCourseGetterForStudent;
import collegeComponent.tool.getter.NameGetter;
import collegeComponent.tool.getter.PinyinGetter;
import collegeComponent.tool.getter.ShortPinyinGetter;
import collegeComponent.tool.traverser.SaveForClubTraverser;
import collegeComponent.tool.traverser.SaveForClub_StudentTraverser;
import collegeComponent.tool.traverser.SaveForStudentTraverser;
import info.infoTool.AllTrueFilter;
import infoInterface.IInfoGetter;
import infoSet.SearchableInfoSet;

/**
 * 本操作者用来以一个外部传入的college为载体，
 * 读取文件和保存文件，
 * 读取和保存需要的文件路径都必须是一个文件夹的路径，
 * 读取时的文件夹下要求必须有一下三个名字的文件：
 * 【
 * Club.dat；
 * Student.dat；
 * Club_Student.dat；
 * 】
 * 保存时会自动为指定的文件夹路径下创建以上三个文件,
 * 本类型的operator()没有任何作用。
 */
public class CollegeReaderAndSaverOperator extends CollegeOperator {
	public RegisterOperator registerOperator;
	
	
	public CollegeReaderAndSaverOperator(College college) {
		super(college);
		registerOperator = new RegisterOperator(college);
	}

	/**
	 * 空方法，没有任何功能。
	 */
	@Override
	public int operate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 先检查文件夹路径是否正确，比如包含三个文件，
	 * Club.dat/Student.dat/Club_Student.dat文件，
	 * 如果不包含就立刻返回。
	 * 如果包含就会将college中的所有Club和Student清空，
	 * 重新创建新的ClubInfoSet和StudentInfoSet，
	 * 然后从该文件路径下面读取三个文件，
	 * 对college进行从新规划。
	 * @param folderPath
	 * 		包含Club.dat/Student.dat/Club_Student.dat的文件夹路径。
	 * @return
	 * 		如果文件夹不符合规范就返回0；
	 * 		如果符合就重新设置college对象，并且返回1；
	 */
	public int read(String folderPath){
		if (checkReadFolderPath(folderPath)){
			college.setClubInfoSet(new SearchableInfoSet(new IInfoGetter[]{
					new IndexGetter(),
					new NameGetter(),
					new PinyinGetter(),
					new ShortPinyinGetter(),
					new DateGetter()}) );
			
			college.setStudentInfoSet(new SearchableInfoSet(new IInfoGetter[]{
					new IndexGetter(),
					new NameGetter(),
					new PinyinGetter(),
					new ShortPinyinGetter(),
					new GenderGetterForStudent(),
					new GradeGetterForStudent(),
					new MainCourseGetterForStudent(),}));
			
			readStudentFile(folderPath + "\\Student.dat");
			readClubFile(folderPath + "\\Club.dat");
			readClub_StudentFile(folderPath + "\\Club_Student.dat");

			return 1;
		}
		return 0;
	}


	/**
	 * 读取指定文件当中的社员注册信息。
	 * @param club_StudentFilePath
	 * 		包含社员注册信息的文件路径。
	 */
	private void readClub_StudentFile(String club_StudentFilePath) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(club_StudentFilePath));
		} catch (FileNotFoundException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator读取社员注册文件错误。");
			MyLogger.logException(e);
			return;
		}
		
		try {
			String club_StudentInfo;
			while ((club_StudentInfo = br.readLine()) != null){
				registClubMemberWithString(club_StudentInfo);
			}
		} catch (IOException e1) {
			MyLogger.logError("CollegeReaderAndSaverOperator遍历社员注册信息时错误。");
			MyLogger.logException(e1);
		}
		
		try {
			br.close();
		} catch (IOException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator关闭社员注册文件错误。");
			MyLogger.logException(e);
		}
	}

	

	/**
	 * 读取指定文件当中的学生信息，
	 * 并添加到college对象当中。
	 * @param club_StudentFilePath
	 * 		包含学生信息的文件路径。
	 */
	private void readStudentFile(String studentFilePath) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(studentFilePath));
		} catch (FileNotFoundException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator读取学生文件错误。");
			MyLogger.logException(e);
			return;
		}
		
		
		String studentInfo;
		try {
			while ((studentInfo = br.readLine()) != null){
				college.addStudent(makeStudentWithString(studentInfo));
			}
		} catch (IOException e1) {
			MyLogger.logError("CollegeReaderAndSaverOperator遍历学生信息时错误。");
			MyLogger.logException(e1);
		}
		
		try {
			br.close();
		} catch (IOException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator关闭学生文件错误。");
			MyLogger.logException(e);
		}
	}

	/**
	 * 通过一个包含所有Student必要信息的字符串，
	 * 来创建一个Student对象，
	 * 如果字符串不符合要求，
	 * 就返回null。
	 * @param studentInfo
	 * 		包含Student必要信息的字符串，
	 * 		不包括参加的社团信息。
	 * 		标准实例：“20020010&杨小鹃&1&大一&文化与法制”。
	 * @return
	 * 		如果成功就返回一个Student对象，
	 * 		如果字符串不符合规范，就返回null。
	 */
	private Student makeStudentWithString(String studentInfo) {
		String[] studentInfos = studentInfo.split("&");
		if (studentInfos == null || studentInfos.length != 5){
			MyLogger.logError("CollegeReaderAndSaverOperator调用makeStudentWithString()方法错误"
					+ "格式不规范，返回null。");
			return null;
		}
		
		int gender;
		if (studentInfos[2].equals("0")){
			gender = 0;
		} else if (studentInfos[2].equals("1")){
			gender = 1;
		} else {
			gender = 3;
		}
		
		return new Student(
				studentInfos[0],
				studentInfos[1], 
				gender, 
				studentInfos[3], 
				studentInfos[4]);
	}
	
	/**
	 * 通过一个包含所有Club必要信息的字符串，
	 * 来创建一个Club对象，
	 * 如果字符串不符合要求，
	 * 就返回null。
	 * @param clubInfo
	 * 		包含Club必要信息的字符串，
	 * 		不包括参加的社团信息。
	 * 		标准实例：“123456&足球俱乐部&2010-9-17”。
	 * @return
	 * 		如果成功就返回一个Club对象，
	 * 		如果字符串不符合规范，就返回null。
	 */
	private Club makeClubWithString(String clubInfo) {
		String[] clubInfos = clubInfo.split("&");
		if (clubInfos == null || clubInfos.length != 3){
			MyLogger.logError("CollegeReaderAndSaverOperator调用makeClubWithString()方法错误"
					+ "格式不规范，返回null。");
			return null;
		}
		
		return new Club(
				clubInfos[0],
				clubInfos[1], 
				clubInfos[2]);
	}
	
	/**
	 * 根据字符串信息来为学生注册社团。
	 * @param club_StudentInfo
	 * 		注册社团所要的基本信息。
	 * 		标准实例：“123456&20020010&会长”。
	 */
	private void registClubMemberWithString(String club_StudentInfo) {
		String[] registInfos = club_StudentInfo.split("&");
		if (registInfos == null || registInfos.length != 3){
			MyLogger.logError("CollegeReaderAndSaverOperator调用registClubMemberWithString()方法错误"
					+ "格式不规范。");
			return;
		}
		registerOperator.setClubIndex(registInfos[0]);
		registerOperator.setStudentIndex(registInfos[1]);
		registerOperator.setPosition(registInfos[2]);
		registerOperator.operate();
	}

	private void readClubFile(String clubFilePath){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(clubFilePath));
		} catch (FileNotFoundException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator读取社团文件错误。");
			MyLogger.logException(e);
			return;
		}
		
		
		String clubInfo;
		try {
			while ((clubInfo = br.readLine()) != null){
				college.addClub(makeClubWithString(clubInfo));
			}
		} catch (IOException e1) {
			MyLogger.logError("CollegeReaderAndSaverOperator遍历社团信息时错误。");
			MyLogger.logException(e1);
		}
		
		try {
			br.close();
		} catch (IOException e) {
			MyLogger.logError("CollegeReaderAndSaverOperator关闭社团文件错误。");
			MyLogger.logException(e);
		}
	}

	

	private boolean checkReadFolderPath(String folderPath) {
		File sourceFile = new File(folderPath);
		if (sourceFile.isFile()){
			return false;
		}

		boolean checkClubFile = false;
		boolean checkStudentFile = false;
		boolean checkClub_StudentFile = false;
		for (File subFile : sourceFile.listFiles()){
			if (subFile.getName().equals("Club.dat")){
				if (checkClubFile){
					MyLogger.logError("CollegeReaderAndSaverOperator读取文件信息时发现当前目录下有同名的“Club.dat”文件，"
							+ "读取文件失败，请检查文件路径：" + folderPath);
					return false;
				}
				checkClubFile = true;
			}
			if (subFile.getName().equals("Student.dat")){
				if (checkStudentFile){
					MyLogger.logError("CollegeReaderAndSaverOperator读取文件信息时发现当前目录下有同名的“Student.dat”文件，"
							+ "读取文件失败，请检查文件路径：" + folderPath);
					return false;
				}
				checkStudentFile = true;
			}
			if (subFile.getName().equals("Club_Student.dat")){
				if (checkClub_StudentFile){
					MyLogger.logError("CollegeReaderAndSaverOperator读取文件信息时发现当前目录下有同名的“Club_Student.dat”文件，"
							+ "读取文件失败，请检查文件路径：" + folderPath);
					return false;
				}
				checkClub_StudentFile = true;
			}
		}
		return checkClubFile 
				&& checkStudentFile 
				&& checkClub_StudentFile;
	}

	
	/**
	 * 先检查当前文件夹中是否有相应的三个文件，
	 * 如果没有就创建一下这几个，
	 * Club.dat/Student.dat/Club_Student.dat的文件夹路径。
	 * @param folderPath
	 * 		要保存的文件夹路径。
	 * @return
	 * 		一直返回1。
	 */
	public int save(String folderPath){
		if (checkSaveFolderPath(folderPath)){
			
			
			saveStudentFile(folderPath + "\\Student.dat");
			saveClubFile(folderPath + "\\Club.dat");
			saveClub_StudentFile(folderPath + "\\Club_Student.dat");

			return 1;
		}
		return 0;
	}

	private void saveClub_StudentFile(String club_StudentFilePath) {
		PrintWriter club_StudentWriter;
		try {
			club_StudentWriter = new PrintWriter(new BufferedWriter(new FileWriter(club_StudentFilePath)));
			college.getClubInfoSet().traverseInfo(new SaveForClub_StudentTraverser(club_StudentWriter), new AllTrueFilter());
			club_StudentWriter.close();
		} catch (IOException e) {
			MyLogger.logError("存储社员注册信息出错！");
			MyLogger.logException(e);
		} 
	}

	private void saveClubFile(String clubFilePath) {
		PrintWriter clubWriter;
		try {
			clubWriter = new PrintWriter(new BufferedWriter(new FileWriter(clubFilePath)));
			college.getClubInfoSet().traverseInfo(new SaveForClubTraverser(clubWriter), new AllTrueFilter());
			clubWriter.close();
		} catch (IOException e) {
			MyLogger.logError("存储社团文件出错！");
			MyLogger.logException(e);
		} 
	}

	private void saveStudentFile(String studentFilePath) {
		PrintWriter studentWriter;
		try {
			studentWriter = new PrintWriter(new BufferedWriter(new FileWriter(studentFilePath)));
			college.getStudentInfoSet().traverseInfo(new SaveForStudentTraverser(studentWriter), new AllTrueFilter());
			studentWriter.close();
		} catch (IOException e) {
			MyLogger.logError("存储学生文件出错！");
			MyLogger.logException(e);
		}
	}

	private boolean checkSaveFolderPath(String folderPath) {
		File sourceFile = new File(folderPath);
		if (sourceFile.isFile()){
			return false;
		}

		boolean checkClubFile = false;
		boolean checkStudentFile = false;
		boolean checkClub_StudentFile = false;
		for (File subFile : sourceFile.listFiles()){
			if (subFile.getName().equals("Club.dat")){
				checkClubFile = true;
			}
			if (subFile.getName().equals("Student.dat")){
				checkStudentFile = true;
			}
			if (subFile.getName().equals("Club_Student.dat")){
				checkClub_StudentFile = true;
			}
		}
		
		if ( ! checkClubFile){
			MyLogger.log("保存College，但是目录“" + folderPath + "”下不存在文件“Club.dat”，"
					+ "现在重新创建这个文件。");
			try {
				checkClubFile = new File(folderPath + "\\Club.dat").createNewFile();
			} catch (IOException e) {
				MyLogger.logError("Club.dat文件创建失败");
				MyLogger.logException(e);
				checkClubFile = false;
			}
		}
		
		if ( ! checkStudentFile){
			MyLogger.log("保存College，但是目录“" + folderPath + "”下不存在文件“Student.dat”，"
					+ "现在重新创建这个文件。");
			try {
				checkStudentFile = new File(folderPath + "\\Student.dat").createNewFile();
			} catch (IOException e) {
				MyLogger.logError("Student.dat文件创建失败");
				MyLogger.logException(e);
				checkStudentFile = false;
			}
		}
		
		if ( ! checkClub_StudentFile){
			MyLogger.log("保存College，但是目录“" + folderPath + "”下不存在文件“Club_Student.dat”，"
					+ "现在重新创建这个文件。");
			try {
				checkClub_StudentFile = new File(folderPath + "\\Club_Student.dat").createNewFile();
			} catch (IOException e) {
				MyLogger.logError("Club_Student.dat文件创建失败");
				MyLogger.logException(e);
				checkClub_StudentFile = false;
			}
		}
		return checkClubFile 
				&& checkStudentFile 
				&& checkClub_StudentFile;
	}
}
