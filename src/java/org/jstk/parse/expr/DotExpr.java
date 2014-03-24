package org.jstk.parse.expr;

import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;

public class DotExpr implements Expr {

	private final String name;
	private final int lineno;

	public DotExpr(final String name, final int lineno) {
		super();
		this.name = name;
		this.lineno = lineno;
	}

	public String getName() {
		return name;
	}

	@Override
	public Obj eval(final ObjStack stk, final ExeEnv env) {
		final Obj obj = stk.pop();
		final Obj val = obj.__getattr__(name);
		return Func.exec_if_can(val, stk, env);
	}

	public Obj set(final ObjStack stk, final ExeEnv env) {
		final Obj o = stk.pop();
		final Obj val = stk.pop();
		o.__setattr__(name, val);
		return val;
	}

	@Override
	public int lineno() {
		return lineno;
	}

}
