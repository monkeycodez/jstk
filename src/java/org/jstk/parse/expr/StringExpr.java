package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.jlang.StrObj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;

public final class StringExpr implements Expr{

	private final StrObj str;

	private final int lineno;

	public StringExpr(String s, int lineno) {
		str = new StrObj(s);
		this.lineno = lineno;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return this.str;
	}

	@Override
	public int lineno(){
		return lineno;
	}

}
