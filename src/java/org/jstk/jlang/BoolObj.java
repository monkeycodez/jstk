package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ObjStack;


public final class BoolObj extends Obj{
	
	public static final BoolObj TRUE= new BoolObj("true"), 
			FALSE = new BoolObj("false");

	private String t;
	private BoolObj(final String s) {t = s;}
	
	public String toString(){
		return t;
	}
	
	public static BoolObj toObj(final boolean b){
		return b ? TRUE : FALSE;
	}
	
	public static boolean toBoolean(final Obj b){
		return TRUE == b ? true : false;
	}
	
	public static boolean istrue(final Obj o,
			final ObjStack o_, final ExeEnv env){
		if(o == BoolObj.TRUE) { return true; }
		if(o == BoolObj.FALSE) { return false; }
		if(o == NullObj.nul) { return false; }
		if(o instanceof CodeObj){
	//		env.__print_frame();
			return istrue(((CodeObj)o).getCode()
					.eval(o_, env), 
					o_, env);
		}
	//	System.err.println(o);
		return false;
	}

}
