package org.jstk.jlang;


public class BoolObj extends Obj{
	
	public static final BoolObj TRUE= new BoolObj("true"), 
			FALSE = new BoolObj("false");

	private String t;
	private BoolObj(String s) {t = s;}
	
	public String toString(){
		return t;
	}
	
	public static BoolObj toObj(boolean b){
		return b ? TRUE : FALSE;
	}

}
