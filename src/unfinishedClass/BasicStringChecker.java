package unfinishedClass;

public class BasicStringChecker {
	public static boolean check(String str){
		if (str == null || str.indexOf('&') != -1){
			return false;
		}
		return true;
	}

}
