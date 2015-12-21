package main.util;

public class ErrorLogico extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ErrorLogico() {}
	
	public ErrorLogico(String msg) {
		super(msg);
	}

}
