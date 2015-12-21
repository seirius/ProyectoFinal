package main.util;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class Fecha {
	
	/**
	 * 
	 * @param fecha String con formato de la mascara
	 * @param mascara String constante 
	 *                    "yyyy/MM/dd" 
	 *                    "dd/MM/yyyy"
	 *                    "yyyy-MM-dd" .... ect. Ó con tiempos
	 *                    "dd/MM/yyyy/hh/mm/ss" ... ect
	 * @return si la fecha es correcta o incorrecta
	 */
	public static boolean validarFecha(String fecha,String mascara){
		SimpleDateFormat formato = new SimpleDateFormat(mascara);
		formato.setLenient(false);
		try {
			 new Date(formato.parse(fecha).getTime());
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/**
	 * convierte un String de una fecha a un date java.sql.date
	 * 
	 * @param fecha  String con formato de la mascara
	 * @param mascara String constante separadores validos (solo / ó solo -)
	 *                    "yyyy/MM/dd"
	 *                    "dd/MM/yyyy"  
	 *                    "dd/MM/yyyy"
	 *                    "yyyy-MM-dd" .... ect. Ó con tiempos
	 *                    "dd/MM/yyyy/hh/mm/ss" ... ect
	 * @return   java.sql.Date.
	 * @throws ParseException
	 */
	public static java.sql.Date convertirADate(String fecha,String mascara) throws ParseException{
		if (fecha.compareTo("")==0 ||fecha ==null)
			return null;
		else {
			SimpleDateFormat formato = new SimpleDateFormat(mascara);
			formato.setLenient(false);
			return new Date(formato.parse(fecha).getTime());
		}	
	}
	
	
	/**
	 * Convierte un String de fecha con una mascara a Timestamp (fecha en milisegundos)
	 * @param fecha
	 * @param mascara
	 * @return TimeStamp 
	 */
	public static Timestamp convertirATimestamp(String fecha, String mascara) throws ParseException {
		if (fecha.compareTo("")==0 ||fecha ==null)
			return null;
		else {
			SimpleDateFormat formato = new SimpleDateFormat(mascara);
			formato.setLenient(false);
			return new Timestamp(formato.parse(fecha).getTime());
		}
	}
	
	
	/**
	 * convierte un  java.sql.date a un String con una mascara
	 * @param fecha  java.sql.Date
	 * @param mascara String constante separadores validos 
	 *               cualquier combinacion de  /,-,: blancos 
	 * @return 
	 */
	
	public static String convertirAString(java.sql.Date fecha,String mascara) {
		if (fecha==null )
			return null;
	    else{
	        SimpleDateFormat formato = new SimpleDateFormat(mascara);
	        formato.setLenient(false);
	        return formato.format(fecha);
	}
			
			
	}
	/**
	 *  devuelve la fecha actual ,se puede incluir en la mascara la hora...
	 * @param mascara String constante constante 
	 *                  separadores validos (solo / ó solo -)
	 *                  dd/MM/yyyy Ó yyyy-MM-dd
	 *                  dd/MM/yyyy/hh/mm/ss
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date fechaActual(String mascara) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat(mascara);
		formato.setLenient(false);
		String fechahoy= formato.format(new java.util.Date());
		return convertirADate(fechahoy,mascara);
			
	}
	
	public static String fechaActualJavaUtil(String mascara) throws ParseException {
		java.util.Date fechaactual=new java.util.Date();
		SimpleDateFormat formato = new SimpleDateFormat(mascara);
		String cadena1= formato.format(fechaactual);
		return cadena1;
			
	}
	
	
	/**
	 * 
	 * @param fecha1 
	 * @param fecha2
	 * @return 1,-1,0 segun fecha1 sea mayor,menor o igual que fecha2
	 */
	public static int compararFechas(java.sql.Date fecha1,
                                     java.sql.Date fecha2){
	
		int inicial= Integer.parseInt(Fecha.convertirAString(fecha1,"yyyy"))*372+
		 Integer.parseInt(Fecha.convertirAString(fecha1,"MM"))*31+
		Integer.parseInt(Fecha.convertirAString(fecha1,"dd"));
		
		int fin=Integer.parseInt(Fecha.convertirAString(fecha2,"yyyy"))*372+ 
		Integer.parseInt(Fecha.convertirAString(fecha2,"MM"))*31+
		Integer.parseInt(Fecha.convertirAString(fecha2,"dd"));
		if(inicial>fin )  
		return 1;  //Fecha1 mayor que fecha2
		else if(inicial<fin ) 
		return -1; //Fecha1 menor que fecha2
		else
		return 0; //Fecha1 igual que fecha2
	}
	
	/** @author Andriy
	 * Devuelve una fecha resultado de la suma de otra fecha mas 'dias'
	 * @param fechaOriginal 
	 * @param dias
	 * @return fechaResultado
	 */
	public static java.sql.Date sumarDiasFecha(java.sql.Date fechaOriginal, int dias) {
		long msOriginal = fechaOriginal.getTime();
		long msDias = TimeUnit.MILLISECONDS.convert(dias, TimeUnit.DAYS);
		java.sql.Date fechaResultado = new java.sql.Date(msOriginal + msDias);
		return fechaResultado;
	}
	/** @author Andriy
	 * Devuelve Timestamp resultado de la suma de otra fecha (en formato Timestamp) mas 'dias'
	 * @param fechaOriginal 
	 * @param dias
	 * @return fechaResultado
	 */
	public static Timestamp sumarDiasFecha(Timestamp fechaOriginal, int dias) {
		long msOriginal = fechaOriginal.getTime();
		long msDias = TimeUnit.MILLISECONDS.convert(dias, TimeUnit.DAYS);
		Timestamp fechaResultado = new Timestamp(msOriginal + msDias);
		return fechaResultado;
	}
}// fin de la clase
