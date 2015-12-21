package main.util;

public class UtilidadesBBDD {

	//Comprueba si una cadena es nulla o vacia, en el caso de que lo sean devuelve una cadena nula, en el caso contrario
	//la devuelve sin cambios
	public static String comprobarStringVacio(String cadena) {
		if (cadena == null || cadena == "") {
			return null;
		} 
		return cadena;
	}
	
	public static boolean comprobarStringVacionNulo(String cadena) {
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
