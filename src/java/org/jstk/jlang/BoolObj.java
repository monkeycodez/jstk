package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


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
	
	public static boolean toBoolean(Obj b){
		return TRUE == b ? true : false;
	}
	
	public static boolean istrue(Obj o,
			ObjStack o_, ExprStream e, ExeEnv env){
		if(o == BoolObj.TRUE) return true;
		if(o == BoolObj.FALSE) return false;
		if(o == NullObj.nul) return false;
		if(o instanceof CodeObj){
			return istrue(((CodeObj)o).getCode()
					.eval(o_, env, e), 
					o_, e, env);
		}
	//	System.err.println(o);
		return false;
	}

}
