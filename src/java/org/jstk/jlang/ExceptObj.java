package org.jstk.jlang;


public class ExceptObj extends Obj{
	
	private final Throwable exception;
	
	public ExceptObj(Throwable except){
		exception = except;
	}
	
	public Throwable getException(){
		return exception;
	}

}
