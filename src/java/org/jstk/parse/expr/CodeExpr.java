package org.jstk.parse.expr;

import org.jstk.jlang.CodeObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class CodeExpr implements Expr{
	
	private CodeObj code;
	
	public CodeExpr(Expr e){
		code = new CodeObj(e);
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return code;
	}

	
	
}
