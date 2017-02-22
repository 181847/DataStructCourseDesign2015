package basicTool;

public class MyLogger {
	/**
	 * 日志记录，向控制台输出信息。
	 * @param message 信息。
	 */
	public static void log(String message){
		System.out.println("Log Message: " + message);
	}
	
	/**
	 * 记录错误信息。
	 * @param errorMessage 要记录的错误信息。
	 */
	public static void logError(String errorMessage){
		System.out.println("Log ERROR! " + errorMessage);
	}
	
	/**
	 * 记录异常对象，向控制台打印异常对象的StackTrace。
	 * @param e 异常对象。
	 */
	public static void logException(Exception e){
		System.out.println("Logger Message: ");
		e.printStackTrace();
	}
	
	public static void seperate(){
		System.out.println("************seperateLine*********************");
	}
}
