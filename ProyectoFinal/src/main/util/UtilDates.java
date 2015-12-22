package main.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDates {

	public static String msToString(long ms, String mask) {
		Date date = new Date(ms);
		SimpleDateFormat format = new SimpleDateFormat(mask);
		return format.format(date);
	}
	
	public static String timestampToString(Timestamp ts, String mask) {
		Date date = new Date(ts.getTime());
		SimpleDateFormat format = new SimpleDateFormat(mask);
		return format.format(date);
	}
}
