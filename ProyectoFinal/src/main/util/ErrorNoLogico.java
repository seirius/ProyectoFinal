package main.util;

public class ErrorNoLogico extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ErrorNoLogico() {}
	
	public ErrorNoLogico(String msg) {
		super(msg);
	}

}
