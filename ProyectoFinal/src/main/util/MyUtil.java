package main.util;

public class MyUtil {

	public static boolean isNull(String cadena) {
		if (cadena == null || cadena == "") {
			return true;
		}
		
		return false;
	}
	
	public static String setMinLen(String string, int minLen, char addChar) {
		int curLen = string.length();
		minLen -= curLen;
		
		for (int i = 0; i < minLen; i++) {
			string += addChar;
		}
		
		return string;
	}
}
