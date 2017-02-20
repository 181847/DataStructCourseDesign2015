package basicTool;

public class MyLogger {
	/**
	 * 日志记录，向控制台输出信息。
	 * @param message 信息。
	 */
	public static void log(String message){
		System.out.println(message);
	}
	
	/**
	 * 记录异常对象，向控制台打印异常对象的StackTrace。
	 * @param e 异常对象。
	 */
	public static void log(Exception e){
		e.printStackTrace();
	}
}
