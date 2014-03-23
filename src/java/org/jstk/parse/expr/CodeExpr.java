package org.jstk.parse.expr;

import org.jstk.jlang.CodeObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public final class CodeExpr implements Expr{
	
	private final CodeObj code;
	private final int lineno;
	
	public CodeExpr(Expr e, int line){
		code = new CodeObj(e);
		lineno = line;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return code;
	}

	@Override
	public int lineno(){
		return lineno;
	}
	
	

	
	
}
