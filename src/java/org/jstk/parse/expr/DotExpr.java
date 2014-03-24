package org.jstk.parse.expr;

import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class DotExpr implements Expr{
	
	private final String name;
	private final int lineno;

	public DotExpr(String name, int lineno) {
		super();
		this.name = name;
		this.lineno = lineno;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		Obj obj = stk.pop();
		Obj val = obj.__getattr__(name);
		return Func.exec_if_can(val, stk, env);
	}

	@Override
	public int lineno(){
		return lineno;
	}

}
