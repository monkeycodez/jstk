package org.jstk.parse.expr;

import org.jstk.jlang.LObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;

public final class NumExpr implements Expr{

	private final LObj num;
	private final int lineno;

	public NumExpr(String s, int line) {
		num = new LObj(Long.parseLong(s));
		lineno = line;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return num;
	}

	@Override
	public int lineno(){
		return lineno;
	}
}
