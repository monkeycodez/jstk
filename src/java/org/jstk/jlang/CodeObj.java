package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class CodeObj extends Obj{
	
	private final Expr code;

	public CodeObj(final Expr code) {
		super();
		this.code = code;
	}
	
	@Override
	public String type(){
		return "CodeObj";
	}
	
	public Expr getCode(){
		return code;
	}
	
	public static Obj exec_if_can(final Obj o, final ObjStack o_, 
			final ExeEnv env){
		if(o instanceof CodeObj){
			CodeObj c = (CodeObj) o;
			return c.getCode().eval(o_, env);
		}
		return o;
	}

}
