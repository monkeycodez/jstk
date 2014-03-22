package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public class CodeObj extends Obj{
	
	private final Expr code;

	public CodeObj(Expr code) {
		super();
		this.code = code;
	}
	
	public Expr getCode(){
		return code;
	}
	
	public static Obj exec_if_can(Obj o, ObjStack o_, 
			ExprStream e, ExeEnv env){
		if(o instanceof CodeObj){
			CodeObj c = (CodeObj) o;
			return c.getCode().eval(o_, env, e);
		}
		return o;
	}

}
