package org.jstk.parse;

import org.jstk.jlang.CodeObj;
import org.jstk.jlang.Obj;


public class CodeExpr implements Expr{
	
	private CodeObj code;
	
	public CodeExpr(Expr e){
		code = new CodeObj(e);
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		return code;
	}

	
	
}
