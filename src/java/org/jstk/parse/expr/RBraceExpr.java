package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public final class RBraceExpr implements Expr{

	private final int lineno;
	
	public RBraceExpr(int lineno) {
		this.lineno = lineno;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		env.pop_frame();
		return stk.pop();
	}

	@Override
	public int lineno(){
		return lineno;
	}

}
